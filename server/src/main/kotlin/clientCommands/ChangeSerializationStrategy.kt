package clientCommands

import commandArgumentsAndTheirsComponents.CommandArgument
import utils.Packet
import commandArgumentsAndTheirsComponents.CommandType
import commandArgumentsAndTheirsComponents.MyString

/***
 * change_serialization_strategy strategy : поменять тип сериализации
 * @author Demid0
 * @since 1.0
 */
class ChangeSerializationStrategy: ClientCommand(CommandType.SINGLE_ARG) {
    override fun execute(arguments: ArrayList<CommandArgument>): ArrayList<Packet> {
        return Packet("print_to_client", arrayListOf(
            MyString(try {
            val newType = caster.toString(arguments[0]) + "strategy"
            serializator.changeStrategy(serializator.getStrategy(newType)!!)
            "Changed"
        } catch (e: NullPointerException) {
            "Unknown serialization strategy\n${printSupportedStrategies()}"
        } catch (e: IndexOutOfBoundsException) {
            "Empty input\n${printSupportedStrategies()}"
        })
        )).wrapIntoArray()
    }

    private fun printSupportedStrategies(): String {
        var out = "You can use this strategies:\n"
        for (strategy in serializator.getStrategies()) {
            out += strategy.value.toString() + "\n"
        }
        return out.dropLast(1)
    }
}