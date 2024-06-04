package com.example.filmesemserie.models

import com.example.filmesemserie.ui.screens.itemManipulate.EditItemUiState
import com.google.gson.annotations.SerializedName


data class ItemModel(
    val id: Int,
    var nomeItem: String,
    var nota: Float,
    var status: StatusItem,
    var tipos: Tipo,
    var idUser: Int,
    var imagem: String?=null,
)

data class IdAndType(
    @SerializedName("idUsuario") val userId: Int,
    @SerializedName("tipo") val type:String
)


enum class StatusItem{
    INTERESSADO, VENDO, TERMINADO, LARGADO
}

enum class Tipo{
    FILME, SERIE, DESENHO, ANIME
}
data class Imagem(
    val id: Int?=null,
    val imagemBase64: String? = null,
    val bytes: ByteArray? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Imagem

        return id == other.id
    }

    override fun hashCode(): Int {
        return id ?: 0
    }
}

data class ResponseItemModel(
    val id: Int,
    @SerializedName("nomeItem") var nameItem: String,
    @SerializedName("nota") var score: Float,
    var status: StatusItem,
    @SerializedName("tipos") var type: Tipo,
    var idUser: Int,
    @SerializedName("imagem") var image: String?=null,
    @SerializedName("valor") override val isCorrect:Boolean
):Response

data class RequestToPostItemModel(
    @SerializedName("nomeItem") val nameItem:String?,
    @SerializedName("tipo")val type: Tipo,
    @SerializedName("status")val state: StatusItem,
    @SerializedName("nota") val score:Float,
    @SerializedName("idUsuario") val idUser:Int,
){
    constructor(editItemUiState: EditItemUiState, idUser: Int):this(
        nameItem= editItemUiState.nome,
        type= editItemUiState.type,
        state= editItemUiState.state,
        score= editItemUiState.nota.toFloatOrNull()?:0F,
        idUser = idUser
    )
}

data class UpdateItem(
    val id:Int,
    @SerializedName("nomeItem") val nomeItem:String,
    @SerializedName("nota") val score:Float,
    @SerializedName("status")val state: StatusItem,
)