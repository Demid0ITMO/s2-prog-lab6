package utils

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.logging.Logger

class ClientAssistant(val clientName: String): KoinComponent {
    private val clientCommandInvoker: ClientCommandInvoker by inject()
    private var collectionManager = CollectionManager(clientName)
    private val starter: Starter by inject()
    private val logger = Logger.getLogger("Handler logger")

    init {
        starter.downloadLastSystemCondition(collectionManager)
    }

    fun executeQuery(packets: ArrayList<Packet>) : ArrayList<Packet> {
        logger.info("Executing query to $clientName")
        return clientCommandInvoker.invoke(packets, collectionManager)
    }
}
