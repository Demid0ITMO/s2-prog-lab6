package clientCommands

import commandArgumentsAndTheirsComponents.CommandArgument
import utils.Packet
import commandArgumentsAndTheirsComponents.CommandType
import commandArgumentsAndTheirsComponents.MyString

/***
 * remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный
 * @author Demid0
 * @since 1.0
 */
class RemoveLower: ClientCommand(CommandType.OBJECT_ARG) {
    override fun execute(arguments: ArrayList<CommandArgument>): ArrayList<Packet> {
        val route = caster.toRoute(arguments[0])
        collectionManager.collection.removeAll { it.getDistance() < route.getDistance() }
        return Packet("print_to_client", arrayListOf(MyString("Done!"))).wrapIntoArray()
    }
}
