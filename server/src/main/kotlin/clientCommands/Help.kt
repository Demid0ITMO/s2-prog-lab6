package clientCommands

import utils.*
import commandArgumentsAndTheirsComponents.CommandArgument
import commandArgumentsAndTheirsComponents.CommandType
import commandArgumentsAndTheirsComponents.MyString

/***
 * help : вывести справку по доступным командам
 * @author Demid0
 * @since 1.0
 */
class Help: ClientCommand(CommandType.NO_ARG) {
    override fun execute(arguments: ArrayList<CommandArgument>): ArrayList<Packet> {
        val commands = ClientCommandInvoker().getCommands()
        return Packet("print_to_client", arrayListOf(
            MyString(
            if (commands.isEmpty()) "No commands"
            else {
                var out = "You can use this commands:\n"
                commands.toSortedMap().forEach { out += it.key + "\n" }
                out.dropLast(1)
            }
        )
        )).wrapIntoArray()
    }
}
