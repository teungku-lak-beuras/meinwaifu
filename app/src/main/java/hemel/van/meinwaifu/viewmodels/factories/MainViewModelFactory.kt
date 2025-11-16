package hemel.van.meinwaifu.viewmodels.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hemel.van.meincore.repository.NekosBestApiRepository
import hemel.van.meinwaifu.viewmodels.MainViewModel

class MainViewModelFactory(
    private val nekosBestApiRepository: NekosBestApiRepository
) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: MainViewModelFactory? = null

        fun getInstance(context: Context): MainViewModelFactory = synchronized(this) {
            if (instance == null) {
                instance = MainViewModelFactory(
                    nekosBestApiRepository = NekosBestApiRepository()
                )
            }

            return instance as MainViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(nekosBestApiRepository = nekosBestApiRepository) as T
        }
        throw IllegalArgumentException("Wrong view model: ${modelClass.name}")
    }
}