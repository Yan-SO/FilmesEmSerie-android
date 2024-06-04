package com.example.filmesemserie.ui.screens.itemManipulate

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.filmesemserie.FilmesEmSeireApplication
import com.example.filmesemserie.data.NetworFilmesEmSeireRepository
import com.example.filmesemserie.models.ItemModel
import com.example.filmesemserie.models.RequestToPostItemModel
import com.example.filmesemserie.models.StatusItem
import com.example.filmesemserie.models.Tipo
import com.example.filmesemserie.models.UpdateItem
import kotlinx.coroutines.launch

class EditItemViewModel(
    private val filmesEmSeireRepository: NetworFilmesEmSeireRepository,
    item: ItemModel?,
    type: Tipo
): ViewModel() {

    private val editItemUiState: EditItemUiState= EditItemUiState(item, type = type)


    private val _uiState= mutableStateOf(editItemUiState)
    val uiState: State<EditItemUiState> get() = _uiState

    companion object{
        fun provideFactory(
            item: ItemModel?,
            type: Tipo
        ): ViewModelProvider.Factory = viewModelFactory{
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FilmesEmSeireApplication)
                val filmesEmSeireRepository = application.conteiner.filmesEmSeireRepository
                EditItemViewModel(
                    filmesEmSeireRepository = filmesEmSeireRepository,
                    item= item,
                    type =type
                )
            }

        }
    }

    fun checkNull():Boolean{
        _uiState.value = _uiState.value.copy(isNullName = false)
        _uiState.value = _uiState.value.copy(isNullNota = false)

        if (_uiState.value.nome==""){
            _uiState.value = _uiState.value.copy(isNullName = true)
            return false
        }else if (_uiState.value.nota==""){
            _uiState.value = _uiState.value.copy(isNullNota = true)
            return false
        }
        return true
    }


    fun updateItemName(newUserName: String) {
        _uiState.value = _uiState.value.copy(nome = newUserName)
    }

    fun showPopupFalse(){
        _uiState.value= _uiState.value.copy(showPopup = false)
    }

    fun updateNota(newNota: String?) {
        if(newNota== null){
            _uiState.value = _uiState.value.copy(nota = "0")
        }else{
            _uiState.value = _uiState.value.copy(nota = newNota)
        }
    }

    fun updateStateItem(stateItem: StatusItem){
        _uiState.value = _uiState.value.copy(state = stateItem)
    }

    fun postItem(userId:Int){
        val item = RequestToPostItemModel(_uiState.value, userId)
        viewModelScope.launch {
            val resp = filmesEmSeireRepository.postItem(item)
            if (resp.isCorrect){
                _uiState.value = _uiState.value.copy(showPopup = true)
            }

        }
    }

    fun updateItemPut(userId: Int){
        val item = UpdateItem(
            id = userId,
            nomeItem = _uiState.value.nome,
            score = _uiState.value.nota.toFloatOrNull()?:0F,
            state = _uiState.value.state
        )
        viewModelScope.launch {
            val resp = filmesEmSeireRepository.updateItem(item)
            if (resp.isCorrect){
                _uiState.value = _uiState.value.copy(showPopup = true)
            }
        }
    }
}