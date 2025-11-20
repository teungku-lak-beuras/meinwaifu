package hemel.van.meincore.repository

class MeinWaifuRepository {
    private val nekosBestApiRepository: NekosBestApiRepository = NekosBestApiRepository()

    fun getWaifu(amount: Int) = nekosBestApiRepository.getWaifu(amount = amount)
}
