package com.gunder.film.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.gunder.film.R
import com.gunder.film.utils.DataDummy
import com.gunder.film.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {
    private val dummyMovie = DataDummy.generateDataDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTvShow()

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadTabButton() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.view_pager)).perform(swipeRight())
        onView(withText(R.string.tv_show)).perform(click())
        onView(withText(R.string.movies)).perform(click())
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_items)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_items)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withText("Movies")).perform(click())
        onView(withId(R.id.rv_items)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail)).check(matches(withText(dummyMovie[0].title)))

        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(withText(dummyMovie[0].overview)))

        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(withText(dummyMovie[0].release_date)))
        onView(withId(R.id.images_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_photo_detail)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShowData() {
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rv_items)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_items)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            )
        )
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rv_items)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail)).check(matches(withText(dummyTvShow[0].title)))
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(withText(dummyTvShow[0].overview)))
        onView(withId(R.id.images_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_photo_detail)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavorite() {
        onView(withId(R.id.rv_items)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.favorited)).perform(click())
        onView(withId(R.id.rv_favorited)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorited)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(withText(dummyMovie[0].overview)))
        onView(withId(R.id.images_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(isRoot()).perform(pressBack())
    }

}