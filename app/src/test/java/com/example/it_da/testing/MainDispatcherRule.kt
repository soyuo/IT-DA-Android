package com.example.it_da.testing

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    val testDispatcher: TestDispatcher = StandardTestDispatcher()
) : TestWatcher() {
    // Replaces Dispatchers.Main so ViewModel coroutines can run in local unit tests.
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    // Restores Dispatchers.Main after each test to avoid leaking dispatcher state.
    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
