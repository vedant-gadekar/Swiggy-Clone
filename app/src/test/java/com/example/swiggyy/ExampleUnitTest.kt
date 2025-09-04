package com.example.swiggyy

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.swiggyy.feature_bottomNavBar.BottomNavViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private lateinit var viewModel: BottomNavViewModel

    @Before
    fun setup(){
        viewModel= BottomNavViewModel()
    }

    @Test
    fun initial_home_selected(){
        val state=viewModel.state.value
        assertEquals(state.selectedRoute,"home")
        assertEquals(state.items.size,3)
    }
}