package hemel.van.meincore.repository

class MeinWaifuRepository {
    private val nekosBestApiRepository: NekosBestApiRepository = NekosBestApiRepository()

    fun getWaifuV1(amount: Int) = nekosBestApiRepository.getWaifuV1(amount = amount)
}
