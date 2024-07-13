package clientCommands

import commandArgumentsAndTheirsComponents.CommandArgument
import utils.Packet
import commandArgumentsAndTheirsComponents.CommandType
import commandArgumentsAndTheirsComponents.MyString

/***
 * add {element} : добавить новый элемент в коллекцию
 * @author Demid0
 * @since 1.0
 */
class Add: ClientCommand(CommandType.OBJECT_ARG) {
    override fun execute(arguments: ArrayList<CommandArgument>): ArrayList<Packet> {
        val objectArg = caster.toRoute(arguments[0])
        collectionManager.collection.add(objectArg)
        return Packet("print_to_client", arrayListOf(MyString("Done!"))).wrapIntoArray()
    }
}