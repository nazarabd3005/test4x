package com.nazar.test4x

import com.nazar.test4x.ui.main.MainViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.annotation.Config
import javax.inject.Inject

@RunWith(JUnit4::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
class CharacterViewModelUnitTest {

    @get:Rule val hiltRUle = HiltAndroidRule(this)

    @Inject lateinit var viewmodel: MainViewModel


    @Test
    fun testInjectViewModel(){
        Assert.assertNull(viewmodel)
        hiltRUle.inject()
        Assert.assertNotNull(viewmodel)
    }

}