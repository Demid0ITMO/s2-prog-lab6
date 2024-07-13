package clientCommands.utils

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.Asker
import utils.Packet
import commandArgumentsAndTheirsComponents.CommandType
import commandArgumentsAndTheirsComponents.MyString

class CommandParser: KoinComponent {
    private val asker: Asker by inject()
    private var commands: HashMap<String, CommandType> = HashMap()

    init {
        addCommand("exit", CommandType.NO_ARG)
        addCommand("help", CommandType.NO_ARG)
        addCommand("info", CommandType.NO_ARG)
        addCommand("show", CommandType.NO_ARG)
        addCommand("add", CommandType.OBJECT_ARG)
        addCommand("update", CommandType.MIXED_ARG)
        addCommand("remove_by_id", CommandType.SINGLE_ARG)
        addCommand("clear", CommandType.NO_ARG)
        addCommand("execute_script", CommandType.SINGLE_ARG)
        addCommand("remove_first", CommandType.NO_ARG)
        addCommand("add_if_max", CommandType.OBJECT_ARG)
        addCommand("remove_lower", CommandType.OBJECT_ARG)
        addCommand("count_by_distance", CommandType.SINGLE_ARG)
        addCommand("count_less_than_distance", CommandType.SINGLE_ARG)
        addCommand("print_field_descending_distance", CommandType.NO_ARG)
        addCommand("change_collection_type", CommandType.SINGLE_ARG)
        addCommand("change_serialization_strategy", CommandType.SINGLE_ARG)
    }
    fun getCommands() = commands

    fun addCommand(name: String, command: CommandType) {
        commands.put(name, command)
    }
    fun clear() {
        commands.clear()
    }

    fun parse(args: MutableList<String>): Packet {
        return if (args.isEmpty()) Packet()
        else {
            val commandName = args[0]
            val commandType = commands[commandName]
            if (commandType == null) Packet()
            else {
                when(commandType) {
                    CommandType.NO_ARG -> {
                        if (args.size != 1) Packet()
                        else Packet(commandName, arrayListOf())
                    }
                    CommandType.SINGLE_ARG -> {
                        if (args.size != 2) Packet()
                        else Packet(commandName, arrayListOf(MyString(args[1])))
                    }
                    CommandType.OBJECT_ARG -> {
                        if (args.size != 1) Packet()
                        else Packet(commandName, arrayListOf(asker.askRoute()))
                    }
                    CommandType.MIXED_ARG -> {
                        if (args.size != 2) Packet()
                        else Packet(commandName, arrayListOf(MyString(args[1]), asker.askRoute()))
                    }
                }
            }
        }
    }
}