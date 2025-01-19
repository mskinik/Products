package com.mskinik.products.data.repository

import com.mskinik.products.data.model.ProductDTO
import com.mskinik.products.data.model.ProductResponse
import com.mskinik.products.data.model.toProduct
import com.mskinik.products.data.network.Resource
import com.mskinik.products.domain.datasource.ProductRemoteDataSource
import com.mskinik.products.domain.model.Product
import com.mskinik.products.domain.repository.ProductRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productRemoteDataSource: ProductRemoteDataSource) :
    ProductRepository {
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
}