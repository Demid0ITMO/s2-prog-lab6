package clientCommands

import commandArgumentsAndTheirsComponents.CommandArgument
import utils.Packet
import commandArgumentsAndTheirsComponents.CommandType
import commandArgumentsAndTheirsComponents.MyString

/***
 * change_collection_type type : поменять тип коллекции
 * @author Demid0
 * @since 1.0
 */
class ChangeCollectionType: ClientCommand(CommandType.SINGLE_ARG) {

    override fun execute(arguments: ArrayList<CommandArgument>): ArrayList<Packet> {
        return Packet("print_to_client", arrayListOf(
            MyString(try {
            val newType = caster.toString(arguments[0])
            collectionManager.changeType(newType)
            "Changed"
        } catch (e: NullPointerException) {
            "Unsupported collection type\n${printSupportedTypes()}"
        } catch (e: IndexOutOfBoundsException) {
            "Empty input\n${printSupportedTypes()}"
        })
        )).wrapIntoArray()
    }

    private fun printSupportedTypes() : String {
        var out = "You can use this types:\n"
        for (type in collectionManager.getSupportedCollectionTypes()) {
            out += type.key +"\n"
        }
        return out.dropLast(1)
    }
}