package utils

import org.koin.core.component.KoinComponent
import java.io.*
import org.koin.core.component.inject
import commandArgumentsAndTheirsComponents.MyString

/***
 * Класс, загружающий последнее сохраненное состояние системы и коллекции
 * @author Demid0
 * @since 1.0
 */
class Starter: KoinComponent {
    private val serializator: Serializator by inject()
    fun downloadLastSystemCondition(collectionManager: CollectionManager) : Packet {
        try {
            val file = File(collectionManager.getInfoFileName())
            val reader = InputStreamReader(file.inputStream())
            val output = reader.readLines()

            // Пробует десериализовать по последней стратегии, если не получается, то пробегается по всем возможным
            serializator.changeStrategy(serializator.getStrategy(output[1])!!)
            if (!downloadCollection(collectionManager)) {
                for (strategy in serializator.getStrategies()) {
                    serializator.changeStrategy(strategy.value)
                    if (downloadCollection(collectionManager)) {
                        break
                    }
                }
            }
            serializator.changeStrategy(serializator.getStrategy(output[1])!!)
            collectionManager.changeType(output[0])

            return Packet("print_to_client", arrayListOf(MyString("Last system condition downloaded successful")))
        } catch (_: Exception) {
            return Packet("print_to_client", arrayListOf(MyString("Can't found info about last system condition. Will use default serialization strategy and default collection type.")))
        }
    }

    private fun downloadCollection(collectionManager: CollectionManager): Boolean {
        try {
            val file = File(collectionManager.getFileName())
            val reader = InputStreamReader(file.inputStream())
            collectionManager.collection = serializator.deserialize(reader.readText(), collectionManager.collection)
        } catch (_: Exception) {
            return false
        }
        return true
    }

}