package com.mahmoudelshamy.nytimes.core

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
abstract class BaseAndroidTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule(order = 2)
    val testCoroutineRule = TestCoroutineRule()

    lateinit var context: Context

    @Before
    open fun setUp() {
        hiltRule.inject()
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }
}