package com.example.warningapp.ui.warning

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warningapp.data.model.WarningResponseItem
import com.example.warningapp.data.repository.WarningRepository
import com.example.warningapp.utilities.Constants.BIWAPP
import com.example.warningapp.utilities.Constants.ERROR
import com.example.warningapp.utilities.Constants.KATWARN
import com.example.warningapp.utilities.Constants.MOWAS
import com.example.warningapp.utilities.Constants.WARNING_CHANNEL
import com.example.warningapp.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WarningViewModel @Inject constructor(
    private val repository: WarningRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state =
        MutableStateFlow<Resource<ArrayList<WarningResponseItem>>>(Resource.Empty())
    val state: StateFlow<Resource<ArrayList<WarningResponseItem>>> = _state

    init {
        savedStateHandle.get<String>(WARNING_CHANNEL)?.let { channel ->
            getWarning(channel)
        }
    }
    fun getWarning(channel: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = Resource.Loading()

                val warnings = when (channel) {
                    KATWARN -> repository.getKatwarnWarning()
                    MOWAS -> repository.getMowasWarning()
                    BIWAPP -> repository.getBiwappWarning()
                    else -> repository.getDwdWarning()
                }

                if (warnings.size > 0) {
                    _state.value = Resource.Success(warnings)
                } else {
                    _state.value = Resource.Empty()
                }
            } catch (e: Exception) {
                _state.value = Resource.Failure(ERROR)
            }
        }
    }
}
