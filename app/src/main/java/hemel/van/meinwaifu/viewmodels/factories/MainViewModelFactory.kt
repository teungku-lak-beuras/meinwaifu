package hemel.van.meinwaifu.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hemel.van.meincore.repository.MeinWaifuRepository
import hemel.van.meinwaifu.viewmodels.MainViewModel

class MainViewModelFactory(
    private val meinWaifuRepository: MeinWaifuRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(meinWaifuRepository = meinWaifuRepository) as T
        }
        throw IllegalArgumentException("Wrong view model: ${modelClass.name}")
    }
}