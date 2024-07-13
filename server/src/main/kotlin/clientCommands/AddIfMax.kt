package clientCommands

import commandArgumentsAndTheirsComponents.CommandArgument
import utils.Packet
import commandArgumentsAndTheirsComponents.CommandType
import commandArgumentsAndTheirsComponents.MyString

/***
 * add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
 * @author Demid0
 * @since 1.0
 */
class AddIfMax: ClientCommand(CommandType.OBJECT_ARG) {
    override fun execute(arguments: ArrayList<CommandArgument>): ArrayList<Packet> {
        val route = caster.toRoute(arguments[0])
        for (element in collectionManager.collection) {
            if (element.getDistance() >= route.getDistance()) {
                return Packet("print_to_client", arrayListOf(MyString("I didn't add it"))).wrapIntoArray()
            }
        }
        collectionManager.collection.add(route)
        return Packet("print_to_client", arrayListOf(MyString("Done!"))).wrapIntoArray()
    }

}
