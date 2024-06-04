package com.example.filmesemserie.models

import com.google.gson.annotations.SerializedName


data class UserModel(
    val id: Int? = null,

    @SerializedName("nomeUsuario")
    val userName: String,
    @SerializedName("senha")
    val passWord: String,

    val itens: List<ItemModel> = ArrayList()
)


data class UserRequest(
    @SerializedName("nome")
    val userName: String,
    @SerializedName("senha")
    val passWord: String
)


data class UserResponse(
    val id: Int,
    @SerializedName("nome") val name: String,
    @SerializedName("valor") override val isCorrect: Boolean
) : Response

data class MessageResponse(
    @SerializedName("Message") val message: String,
    @SerializedName("valor") override val isCorrect: Boolean
) : Response