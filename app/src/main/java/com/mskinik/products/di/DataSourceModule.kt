package com.mskinik.products.di

import com.mskinik.products.data.datasource.ProductRemoteDataSourceImpl
import com.mskinik.products.domain.datasource.ProductRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindProductRemoteDataSource(impl: ProductRemoteDataSourceImpl): ProductRemoteDataSource

}