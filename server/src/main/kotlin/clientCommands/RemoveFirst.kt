package clientCommands

import commandArgumentsAndTheirsComponents.CommandArgument
import utils.Packet
import commandArgumentsAndTheirsComponents.CommandType
import commandArgumentsAndTheirsComponents.MyString
import java.util.NoSuchElementException
/***
 * remove_first : удалить первый элемент из коллекции
 * @author Demid0
 * @since 1.0
 */
class RemoveFirst: ClientCommand(CommandType.NO_ARG) {
    override fun execute(arguments: ArrayList<CommandArgument>): ArrayList<Packet> {
        return Packet("print_to_client", arrayListOf(
            MyString(try {
            collectionManager.collection.remove(collectionManager.collection.first())
            "Done!"
        }
        catch (e: NoSuchElementException) { "Collection is empty" })
        )).wrapIntoArray()
    }
}
