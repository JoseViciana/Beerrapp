package com.jviciana.beerrapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jviciana.beerrapp.data.model.BeerFactory
import com.jviciana.beerrapp.data.model.BeerModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.assertEquals
import org.mockito.ArgumentMatchers.any

@ExperimentalCoroutinesApi
class BeerViewModelTest{

    @RelaxedMockK
    private lateinit var beerFactory: BeerFactory
    @RelaxedMockK
    private lateinit var beerViewModel: BeerViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        beerViewModel = BeerViewModel(beerFactory)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `loadItems loads empty beer list when factory returns empty`() = runTest {
        //Given
        coEvery { beerFactory.create("").invoke() } returns emptyList()
        //When
        beerViewModel.loadItems("")
        //Then
        assertEquals(emptyList<BeerModel>(), beerViewModel.items.value)
        assertEquals(false, beerViewModel.isLoading.value)
    }

    @Test
    fun `loadItems loads non-empty beer list`() = runTest {
        val fakeName = "IPA"
        val fakeBeerList = listOf(BeerModel(1, "IPA", "Desc", "",5.0))
        //Given
        coEvery { beerFactory.create(fakeName).invoke() } returns fakeBeerList
        //When
        beerViewModel.loadItems(fakeName)
        //Then
        assertEquals(fakeBeerList, beerViewModel.items.value)
        assertEquals(false, beerViewModel.isLoading.value)
    }

}