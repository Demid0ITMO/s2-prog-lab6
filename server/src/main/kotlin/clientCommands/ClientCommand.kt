package clientCommands

import utils.CollectionManager
import utils.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import commandArgumentsAndTheirsComponents.CommandArgument
import commandArgumentsAndTheirsComponents.CommandType

/***
 * Абстрактный класс команды
 * @author Demid0
 * @since 1.0
 */
//@Serializable
abstract class ClientCommand(val type: CommandType) : KoinComponent {
    var collectionManager = CollectionManager("default")
    internal val serializator: Serializator by inject()
    internal val caster: Caster by inject()
    internal val clientCommandInvoker: ClientCommandInvoker by inject()

    abstract fun execute(arguments: ArrayList<CommandArgument>) : ArrayList<Packet>
}
