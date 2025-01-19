package com.mskinik.products.data.model

import com.mskinik.products.domain.model.Review

data class ReviewDTO(
    val comment: String?,
    val date: String?,
    val rating: Int?,
    val reviewerEmail: String?,
    val reviewerName: String?
)

fun ReviewDTO.toReview() = Review(
    comment = comment,
    date = date,
    rating = rating,
    reviewerEmail = reviewerEmail,
    reviewerName = reviewerName
)