import org.koin.core.component.inject
import org.koin.core.context.startKoin
import utils.ServerMessageHandler
import utils.ServerUtilFabric
import utils.serverKoinModule

fun main(args: Array<String>)  {
    startKoin {
        modules(serverKoinModule)
    }
    val serverUtilFabric = ServerUtilFabric()
    val serverMessageHandler: ServerMessageHandler by serverUtilFabric.inject()
    while (true) {
        serverMessageHandler.run()
    }
}
