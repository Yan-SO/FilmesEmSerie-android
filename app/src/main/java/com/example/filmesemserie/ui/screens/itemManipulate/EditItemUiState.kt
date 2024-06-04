package com.example.filmesemserie.ui.screens.itemManipulate

import com.example.filmesemserie.models.ItemModel
import com.example.filmesemserie.models.StatusItem
import com.example.filmesemserie.models.Tipo

data class EditItemUiState(
    var id: Int?,
    var type: Tipo,
    var nome: String,
    var nota: String,
    var state: StatusItem,

    var isNullName:Boolean=false,
    var isNullNota:Boolean=false,
    var showPopup:Boolean=false
){
    constructor(item: ItemModel?, type: Tipo): this(
        id = item?.id,
        type = type,
        nome = item?.nomeItem ?: "",
        nota = item?.nota?.toString()?: "",
        state = item?.status ?: StatusItem.INTERESSADO
    )
}
