package com.jviciana.beerrapp.domain

import com.jviciana.beerrapp.data.model.BeerModel
import com.jviciana.beerrapp.data.repository.BeerRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.AssertionError
import javax.annotation.meta.When

class GetBeersByNameTest{

   @RelaxedMockK
    private lateinit var beerRepository: BeerRepository

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
    }

    @Test
    fun `invoke calls repository with correct name and returns beers`() = runBlocking {
        val fakeName = "Corona"
        val fakeBeers = listOf(BeerModel(100, "Corona", "Fake beer","",6.0))
        //Given
        coEvery { beerRepository.getBeersByName(fakeName) } returns fakeBeers
        //When
        val result = GetBeersByName(fakeName, beerRepository).invoke()
        //Then
        assertEquals(fakeBeers, result)
        coVerify(exactly = 1) { beerRepository.getBeersByName(fakeName) }
    }

    @Test
    fun `invoke calls repository with incorrect name and returns empty list`() = runBlocking {
        //Given
        coEvery { beerRepository.getBeersByName("") } returns emptyList()
        //When
        val result = GetBeersByName("", beerRepository).invoke()
        //Then
        assertEquals(emptyList<BeerModel>(), result)
        coVerify(exactly = 1) { beerRepository.getBeersByName("") }
    }

}