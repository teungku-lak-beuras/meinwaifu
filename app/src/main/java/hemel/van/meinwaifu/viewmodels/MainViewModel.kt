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
    private val _nekosBestWaifus = MutableStateFlow<ApiState<List<NekosBestWaifuEntity>>>(ApiState.Loading)
    val nekosBestWaifus = _nekosBestWaifus.asStateFlow()

    init {
        getWaifu()
    }

    fun getWaifu(amount: Int = 15) = viewModelScope.launch {
        nekosBestApiRepository.getWaifu(amount = amount).collect { collecter ->
            _nekosBestWaifus.value = collecter
        }
    }
}
