package com.dario.myapplication

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dario.myapplication.domain.usecase.GetContactsUseCase
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
class GetContactsUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var getContactsUseCase: GetContactsUseCase

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
    fun getContactsNumberOfResultsTest(): Unit = runTest {
        val response1 = getContactsUseCase.invoke(
            GetContactsUseCase.Params(
            page = 0,
            numberOfResults = 10
        )).getOrThrow()
        assertEquals(response1.count(), 10)
        val response2 = getContactsUseCase.invoke(
            GetContactsUseCase.Params(
            page = 0,
            numberOfResults = 3
        )).getOrThrow()
        assertEquals(response2.count(), 3)
    }

}