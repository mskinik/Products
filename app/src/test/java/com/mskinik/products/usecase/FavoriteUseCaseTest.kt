package com.mskinik.products.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mskinik.products.data.TestCheckoutUseCase
import com.mskinik.products.data.TestFavoriteUseCase
import com.mskinik.products.domain.repository.ProductRepository
import com.mskinik.products.domain.usecase.FavoriteUseCase
import com.mskinik.products.util.CoroutineTestRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FavoriteUseCaseTest {

    private val testFavoriteUseCase = TestFavoriteUseCase()

    private val testCheckoutUseCase = TestCheckoutUseCase()
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    lateinit var productRepository: ProductRepository

    private lateinit var favoriteUseCase: FavoriteUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        favoriteUseCase = FavoriteUseCase(productRepository)
    }

    @Test
    fun `addFavorite be success when setFavorite success`() = runTest {
        coEvery { productRepository.setFavorite(testFavoriteUseCase.favoriteEntity) } returns 1L
        val result = favoriteUseCase.addFavorite(testFavoriteUseCase.favoriteEntity)
        assertEquals(1L, result)
    }

    @Test
    fun `isFavorite should true when flow productRepository isFavorite return true value`() = runTest {
        coEvery { productRepository.isFavorite(1) } returns flowOf(true)
        val result = favoriteUseCase.isFavorite(1).first()
        assertEquals(true, result)
    }

    @Test
    fun `quantity should 2 when combined favorites and checkouts are over`() = runTest {
        coEvery { productRepository.getFavorites() } returns flowOf(testFavoriteUseCase.favoriteList)
        coEvery { productRepository.getCheckouts() } returns flowOf(testCheckoutUseCase.testCheckoutList)
        val result = favoriteUseCase.getMyProducts().first()
        assertEquals(2, result.first().quantity)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}
