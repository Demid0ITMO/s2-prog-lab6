package systemCommands

import exceptions.SystemCommandInvocationException
import commandArgumentsAndTheirsComponents.CommandArgument

class ClearClientCommands : SystemCommand() {
    override fun execute(arguments: ArrayList<CommandArgument>) {
        try {
            commandParser.clear()
        } catch (_: Exception) {
            throw SystemCommandInvocationException()
        }
    }

}
