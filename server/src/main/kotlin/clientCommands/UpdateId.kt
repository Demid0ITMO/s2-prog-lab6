package clientCommands

import commandArgumentsAndTheirsComponents.CommandArgument
import utils.Packet
import commandArgumentsAndTheirsComponents.CommandType
import commandArgumentsAndTheirsComponents.MyString
import java.lang.Exception
/***
 * update id {element} : обновить значение элемента коллекции, id которого равен заданному
 * @author Demid0
 * @since 1.0
 */
class UpdateId: ClientCommand(CommandType.MIXED_ARG) {
    override fun execute(arguments: ArrayList<CommandArgument>): ArrayList<Packet> {
        val id: Long = caster.toString(arguments[0]).toLong()
        val route = caster.toRoute(arguments[1])
        return Packet("print_to_client", arrayListOf(
            MyString(try {
            collectionManager.collection.first { it.getId() == id }.update(
                name = route.getName(),
                coordinates = route.getCoordinates(),
                from = route.getFrom(),
                to = route.getTo(),
                distance = route.getDistance()
            )
                "Done"
        } catch (e: Exception) {
            "Wrong id format."
        })
        )).wrapIntoArray()
    }

}
