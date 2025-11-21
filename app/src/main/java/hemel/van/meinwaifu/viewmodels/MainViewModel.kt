package hemel.van.meinwaifu.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hemel.van.meincore.entitity.WaifuEntityV1
import hemel.van.meincore.repository.MeinWaifuRepository
import hemel.van.meincore.repository.utilities.ApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val meinWaifuRepository: MeinWaifuRepository
) : ViewModel() {
    private val _waifuEntity = MutableStateFlow<ApiState<List<WaifuEntityV1>>>(ApiState.Loading)
    val waifuEntity = _waifuEntity.asStateFlow()

    init {
        getWaifu()
    }

    fun getWaifu(amount: Int = 16) = viewModelScope.launch {
        meinWaifuRepository.getWaifuV1(amount = amount).collect { collecter ->
            _waifuEntity.value = collecter
        }
    }
}
