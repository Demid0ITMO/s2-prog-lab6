package clientCommands

import commandArgumentsAndTheirsComponents.CommandArgument
import commandArgumentsAndTheirsComponents.CommandType
import commandArgumentsAndTheirsComponents.CommandTypeArgument
import commandArgumentsAndTheirsComponents.MyString
import utils.Packet

class Checkout : ClientCommand(CommandType.NO_ARG) {
    override fun execute(arguments: ArrayList<CommandArgument>): ArrayList<Packet> {
        val ans = arrayListOf(Packet("clear_client_commands", arrayListOf()))
        val existingCommands = clientCommandInvoker.getCommands()
        existingCommands.forEach{ ans.add(Packet("add_client_command", arrayListOf(MyString(it.key), CommandTypeArgument(it.value.type)))) }
        return ans
    }
}
