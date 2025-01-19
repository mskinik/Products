package com.mskinik.products.data.model.remote

import com.mskinik.products.domain.model.Meta

data class MetaDTO(
    val barcode: String?,
    val createdAt: String?,
    val qrCode: String?,
    val updatedAt: String?
)

fun MetaDTO.toMeta(): Meta = Meta(
    barcode = barcode,
    createdAt = createdAt,
    qrCode = qrCode,
    updatedAt = updatedAt
)