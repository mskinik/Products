package com.mskinik.products.data.repository

import com.mskinik.products.data.model.local.CheckoutEntity
import com.mskinik.products.data.model.local.FavoriteEntity
import com.mskinik.products.data.model.local.toCheckout
import com.mskinik.products.data.model.local.toFavorite
import com.mskinik.products.data.model.remote.ProductResponse
import com.mskinik.products.data.model.remote.toProduct
import com.mskinik.products.data.network.Resource
import com.mskinik.products.domain.datasource.ProductLocalDataSource
import com.mskinik.products.domain.datasource.ProductRemoteDataSource
import com.mskinik.products.domain.model.Checkout
import com.mskinik.products.domain.model.Favorite
import com.mskinik.products.domain.model.Product
import com.mskinik.products.domain.repository.ProductRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val productLocalDataSource: ProductLocalDataSource
) : ProductRepository {
    override suspend fun getAllProduct(): Resource<List<Product>?> {
        val response = productRemoteDataSource.getAllProduct()
        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                return Resource.Success(data = it.productList?.map { it.toProduct() })
            } ?: run {
                return Resource.Fail(data = null)
            }
        } else {
            return Resource.Error(null)
        }
    }

    override suspend fun searchProducts(query: String?): Response<ProductResponse> {
        return productRemoteDataSource.searchProducts(query)
    }

    override fun getProductById(id: Int): Single<Resource<Product?>> {
        return productRemoteDataSource.getProductById(id)
            .subscribeOn(Schedulers.io())
            .map { response ->
                if (response.isSuccessful) {
                    Resource.Success(response.body()?.toProduct())
                } else {
                    Resource.Error()
                }
            }
    }

    override suspend fun setFavorite(item: FavoriteEntity): Long =
        productLocalDataSource.addToFavorite(item)

    override suspend fun deleteFavorite(id: Int): Int {
        return productLocalDataSource.deleteFavorite(id)
    }

    override suspend fun isFavorite(id: Int): Flow<Boolean> {
        return productLocalDataSource.isFavorite(id)
    }

    override suspend fun getFavorites(): Flow<List<Favorite>> {
        return productLocalDataSource.getFavorites().map { favorites ->
            favorites.map { it.toFavorite() }
        }
    }

    override suspend fun getCheckouts(): Flow<List<Checkout>> {
        return productLocalDataSource.getCheckouts().map { checkouts ->
            checkouts.map { it.toCheckout() }
        }
    }

    override suspend fun addCheckout(productDetail: CheckoutEntity) {
        productLocalDataSource.addCheckout(productDetail)
    }

    override suspend fun deleteCheckout(id: Int) {
        productLocalDataSource.deleteCheckout(id)
    }

    override suspend fun increaseQuantity(id: Int) {
        productLocalDataSource.increaseQuantity(id)
    }

    override suspend fun decreaseQuantity(id: Int) {
        productLocalDataSource.decreaseQuantity(id)
    }

    override suspend fun getTotalQuantity(): Flow<Int?> {
        return productLocalDataSource.getTotalQuantity()
    }

    override suspend fun deleteAllCheckouts(): Flow<Int> =
        flowOf(productLocalDataSource.deleteAllCheckouts())

    override suspend fun getCheckoutById(id: Int): Flow<Checkout?> {
        return productLocalDataSource.getCheckoutById(id).map { it?.toCheckout() }
    }
}