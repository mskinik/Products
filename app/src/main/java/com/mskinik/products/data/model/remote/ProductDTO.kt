package com.mskinik.products.data.model.remote

import com.google.gson.annotations.SerializedName
import com.mskinik.products.domain.model.Product
import com.mskinik.products.domain.model.ProductDetail

data class ProductDTO(
    val availabilityStatus: String?,
    val brand: String?,
    val category: String?,
    val description: String?,
    @SerializedName("dimensions") val dimensionList: DimensionsDTO?,
    val discountPercentage: Double?,
    val id: Int?,
    val images: List<String>?,
    val meta: MetaDTO?,
    val minimumOrderQuantity: Int?,
    val price: Double?,
    val rating: Double?,
    val returnPolicy: String?,
    @SerializedName("reviews") val reviewList: List<ReviewDTO>?,
    val shippingInformation: String?,
    val sku: String?,
    val stock: Int?,
    val tags: List<String>?,
    val thumbnail: String?,
    val title: String?,
    val warrantyInformation: String?,
    val weight: Int?
)

fun ProductDTO.toProduct(): Product = Product(
    availabilityStatus = availabilityStatus,
    brand = brand,
    category = category,
    description = description,
    discountPercentage = discountPercentage,
    id = id,
    images = images,
    previewImage = images?.firstOrNull(),
    meta = meta?.toMeta(),
    minimumOrderQuantity = minimumOrderQuantity,
    price = price,
    rating = rating,
    reviewList = reviewList?.map { it.toReview() },
    stock = stock,
    tags = tags,
    thumbnail = thumbnail,
    title = title,
)

fun ProductDTO.toProductDetail(): ProductDetail = ProductDetail(
    id = id.toString(),
    title = title,
    desc = description,
    price = price,
    image = thumbnail,
    stock = stock,
    quantity = null,
    brand = brand,
    category = category,
)