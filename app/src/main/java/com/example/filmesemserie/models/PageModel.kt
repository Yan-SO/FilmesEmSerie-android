package com.example.filmesemserie.models

import com.google.gson.annotations.SerializedName

data class ApiPageResponse<T>(
    @SerializedName("content") val content: List<T>,
    @SerializedName("pageable") val pageable: Pageable,
    @SerializedName("last") val last: Boolean,
    @SerializedName("totalElements") val totalElements: Long,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("size") val size: Int,
    @SerializedName("number") val number: Int,
    @SerializedName("sort") val sort: Sort,
    @SerializedName("first") val first: Boolean,
    @SerializedName("numberOfElements") val numberOfElements: Int,
    @SerializedName("empty") val empty: Boolean
)

data class DadosRetornoItem(
    @SerializedName("id") val id: Int,
    @SerializedName("nomeItem") val nomeItem: String,
    @SerializedName("nota") val nota: Float,
    @SerializedName("status") val status: StatusItem,
    @SerializedName("tipos") val tipos: Tipo,
    @SerializedName("imagem") val imagem: ByteArray,
    @SerializedName("idUser") val idUser: Int,
    @SerializedName("valor") val valor: Boolean
)

data class Pageable(
    @SerializedName("pageNumber") val pageNumber: Int,
    @SerializedName("pageSize") val pageSize: Int,
    @SerializedName("sort") val sort: Sort,
    @SerializedName("offset") val offset: Long,
    @SerializedName("paged") val paged: Boolean,
    @SerializedName("unpaged") val unpaged: Boolean
)

data class Sort(
    @SerializedName("empty") val empty: Boolean,
    @SerializedName("unsorted") val unsorted: Boolean,
    @SerializedName("sorted") val sorted: Boolean
)