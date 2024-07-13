package systemCommands

import commandArgumentsAndTheirsComponents.CommandArgument
import kotlin.system.exitProcess

class EndClientSession: SystemCommand() {
    override fun execute(arguments: ArrayList<CommandArgument>) {
        exitProcess(0)
    }
}