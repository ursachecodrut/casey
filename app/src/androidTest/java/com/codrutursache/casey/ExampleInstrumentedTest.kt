
package com.codrutursache.casey

import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.codrutursache.casey.presentation.MainActivity
import com.codrutursache.casey.presentation.components.BottomBar
import com.codrutursache.casey.presentation.navigation.Route
import com.codrutursache.casey.presentation.theme.CaseyTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalAnimationApi::class)
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun bottomBarIsVisible() {
        composeTestRule.activity.setContent {
            CaseyTheme {
                BottomBar(
                    navigateTo = {},
                    currentRoute = Route.RecipesRoute.route
                )
            }
        }

        composeTestRule.onNodeWithText("Recipes").assertIsDisplayed()
    }

    @Test
    fun bottomBarIsNotVisible() {

        composeTestRule.activity.setContent {
            CaseyTheme {
                BottomBar(
                    navigateTo = {},
                    currentRoute = Route.AuthRoute.route
                )
            }
        }

        composeTestRule.onNodeWithText("Recipes").assertDoesNotExist()
    }

    @Test
    fun bottomBarNavigation() {
        composeTestRule.activity.setContent {
            CaseyTheme {
                BottomBar(
                    navigateTo = {},
                    currentRoute = Route.RecipesRoute.route
                )
            }
        }

        composeTestRule.onNodeWithText("Profile").performClick()
        composeTestRule.onNodeWithText("Profile").assertIsSelected()
    }
}