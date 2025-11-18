package hemel.van.meinwaifu.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hemel.van.meincore.entitity.NekosBestWaifuEntity
import hemel.van.meincore.repository.NekosBestApiRepository
import hemel.van.meincore.repository.utilities.ApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val nekosBestApiRepository: NekosBestApiRepository
) : ViewModel() {
    private val _waifuEntity = MutableStateFlow<ApiState<List<NekosBestWaifuEntity>>>(ApiState.Loading)
    val waifuEntity = _waifuEntity.asStateFlow()

    init {
        getWaifu()
    }

    fun getWaifu(amount: Int = 16) = viewModelScope.launch {
        nekosBestApiRepository.getWaifu(amount = amount).collect { collecter ->
            _waifuEntity.value = collecter
        }
    }
}
