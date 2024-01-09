package com.dario.myapplication

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dario.myapplication.domain.usecase.MapContactUseCase
import com.dario.myapplication.testmockdata.fakeRandomUserApiResponse
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotSame
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
class MapContactsUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var mapContactUseCase: MapContactUseCase

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
    fun correctMappingTest(): Unit = runTest {
        val mappedData = mapContactUseCase.invoke(fakeRandomUserApiResponse).getOrThrow()
        assertEquals(mappedData.id, fakeRandomUserApiResponse.id?.value)
        assertEquals(mappedData.email, fakeRandomUserApiResponse.email)
        assertEquals(mappedData.phone, fakeRandomUserApiResponse.phone)
    }

    @Test
    fun incorrectMappingTest(): Unit = runTest {
        val mappedData = mapContactUseCase.invoke(fakeRandomUserApiResponse).getOrThrow()
        assertNotSame(mappedData.gender.toString(), "female")
        assertNotSame(mappedData.id, fakeRandomUserApiResponse.id?.name)
    }

}