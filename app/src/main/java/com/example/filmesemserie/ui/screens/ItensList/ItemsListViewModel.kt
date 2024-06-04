package com.example.filmesemserie.ui.screens.ItensList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import com.example.filmesemserie.FilmesEmSeireApplication
import com.example.filmesemserie.data.NetworFilmesEmSeireRepository
import com.example.filmesemserie.models.IdAndType
import com.example.filmesemserie.models.Tipo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ItemsListViewModel(
    private val filmesEmSeireRepository: NetworFilmesEmSeireRepository,
    private val userId:Int,
    private val  title: Tipo,
):ViewModel() {

    private val _uiState = MutableStateFlow(ItemsListUiState(page = null))

    val uiState: StateFlow<ItemsListUiState> get() = _uiState
    val teg = "ItemsListViewModel"
    init {
        findItemsForType()
    }



    fun findItemsForType(){
        viewModelScope.launch {

            val response= filmesEmSeireRepository.findItemsForType(idAndType = IdAndType(userId, title.name))
            Log.d(teg, response.toString())
            _uiState.value = _uiState.value.copy(response)
        }
    }

    companion object{
        fun provideFactory(
            userId: Int,
            title: Tipo
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FilmesEmSeireApplication)
                val filmesEmSeireRepository = application.conteiner.filmesEmSeireRepository
                ItemsListViewModel(
                    filmesEmSeireRepository = filmesEmSeireRepository,
                    userId=userId,
                    title= title,
                    )
            }
        }
    }
}