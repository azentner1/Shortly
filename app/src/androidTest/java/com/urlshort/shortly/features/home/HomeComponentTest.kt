package com.urlshort.shortly.features.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import com.urlshort.shortly.base.database.RealmDB
import com.urlshort.shortly.base.test.TestTags
import com.urlshort.shortly.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.time.ExperimentalTime

@InternalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalTime
@HiltAndroidTest
class HomeComponentTest {

    private lateinit var latch: CountDownLatch

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeRule = createAndroidComposeRule(MainActivity::class.java)

    private val waitingTime: Long = 2
    private val waitingTimeUnit: TimeUnit = TimeUnit.SECONDS

    @Before
    fun setup() {
        RealmDB.init(InstrumentationRegistry.getInstrumentation().targetContext)
        hiltTestRule.inject()
    }

    // tests apps cold start and saving data
    @Test
    fun homeColdStart() {
        latch = CountDownLatch(7)
        latch.await(waitingTime, waitingTimeUnit)
        val emptyHomeComponent =
            composeRule.onNode(hasTestTag(TestTags.TEST_TAGS_EMPTY_COMPONENT), true)
        emptyHomeComponent.assertIsDisplayed()
        latch.countDown()
        latch.await(waitingTime, waitingTimeUnit)
        val homeInputAction = composeRule.onNode(hasTestTag(TestTags.TEST_TAGS_URL_INPUT))
        homeInputAction.performClick()
        homeInputAction.performTextInput("www.google.com")
        latch.countDown()
        latch.await(waitingTime, waitingTimeUnit)
        val homeButtonAction = composeRule.onNode(hasTestTag(TestTags.TEST_TAGS_URL_ACTION))
        homeButtonAction.performClick()
        latch.countDown()
        latch.await(waitingTime, waitingTimeUnit)
        latch.countDown()
        latch.await(waitingTime, waitingTimeUnit)
        latch.countDown()
        latch.countDown()
    }

    // tests the history is populated
    @Test
    fun homeHotStart() {
        latch = CountDownLatch(1)
        val historyList = composeRule.onNode(hasTestTag(TestTags.TEST_TAGS_HISTORY_LIST))
        historyList.assertIsDisplayed()
        latch.countDown()
    }
}