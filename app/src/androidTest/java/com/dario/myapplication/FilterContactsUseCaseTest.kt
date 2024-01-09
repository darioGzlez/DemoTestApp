package com.dario.myapplication

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dario.myapplication.domain.model.Contact
import com.dario.myapplication.domain.usecase.FilterContactsUseCase
import com.dario.myapplication.domain.usecase.MapContactUseCase
import com.dario.myapplication.testmockdata.fakeRandomUserApiResponse
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class FilterContactsUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var filterContactsUseCase: FilterContactsUseCase

    @Inject
    lateinit var mapContactUseCase: MapContactUseCase

    private var contactList: List<Contact> = emptyList()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        hiltRule.inject()
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun filterTestNameOk(): Unit = runTest {
        contactList = listOf(mapContactUseCase.invoke(fakeRandomUserApiResponse).getOrThrow())
        var filteredResult = filterContactsUseCase.invoke(
            params = FilterContactsUseCase.Params(
                input = "John",
                contactList
            )
        ).getOrThrow()
        assertEquals(filteredResult.count(), 1)
        filteredResult = filterContactsUseCase.invoke(
            params = FilterContactsUseCase.Params(
                input = "Jo",
                contactList
            )
        ).getOrThrow()
        assertEquals(filteredResult.count(), 1)
        filteredResult = filterContactsUseCase.invoke(
            params = FilterContactsUseCase.Params(
                input = "HN",
                contactList
            )
        ).getOrThrow()
        assertEquals(filteredResult.count(), 1)
    }

    @Test
    fun filterTestNameKo(): Unit = runTest {
        contactList = listOf(mapContactUseCase.invoke(fakeRandomUserApiResponse).getOrThrow())
        val filteredResult = filterContactsUseCase.invoke(
            params = FilterContactsUseCase.Params(
                input = "Paul",
                contactList
            )
        ).getOrThrow()

        assertEquals(filteredResult.count(), 0)
    }

    @Test
    fun filterTestMailOk(): Unit = runTest {
        contactList = listOf(mapContactUseCase.invoke(fakeRandomUserApiResponse).getOrThrow())
        val filteredResult = filterContactsUseCase.invoke(
            params = FilterContactsUseCase.Params(
                input = "@",
                contactList
            )
        ).getOrThrow()

        assertEquals(filteredResult.count(), 1)
    }

    @Test
    fun filterTestMailKo(): Unit = runTest {
        contactList = listOf(mapContactUseCase.invoke(fakeRandomUserApiResponse).getOrThrow())
        val filteredResult = filterContactsUseCase.invoke(
            params = FilterContactsUseCase.Params(
                input = "gmail",
                contactList
            )
        ).getOrThrow()

        assertEquals(filteredResult.count(), 0)
    }

}