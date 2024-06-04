package com.example.filmesemserie.ui.screens.ItensList

import com.example.filmesemserie.models.ApiPageResponse
import com.example.filmesemserie.models.ItemModel

data class ItemsListUiState(
    val page: ApiPageResponse<ItemModel>?
)
