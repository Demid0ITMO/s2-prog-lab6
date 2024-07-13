package systemCommands

import exceptions.SystemCommandInvocationException
import commandArgumentsAndTheirsComponents.CommandArgument

class AddClientCommand : SystemCommand() {
    override fun execute(arguments: ArrayList<CommandArgument>) {
        try {
            val singleArg = caster.toString(arguments[0])
            val commandType = caster.toCommandType(arguments[1])
            commandParser.addCommand(singleArg, commandType)
        } catch (_: Exception) {
            throw SystemCommandInvocationException()
        }
    }

}
