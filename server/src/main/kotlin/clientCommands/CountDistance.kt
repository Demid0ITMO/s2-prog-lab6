package clientCommands

import commandArgumentsAndTheirsComponents.CommandArgument
import utils.Packet
import commandArgumentsAndTheirsComponents.CommandType
import commandArgumentsAndTheirsComponents.MyString
import java.lang.Exception

/***
 * count_by_distance distance : вывести количество элементов, значение поля distance которых равно заданному
 * count_less_than_distance distance : вывести количество элементов, значение поля distance которых меньше заданного
 * @author Demid0
 * @since 1.0
 */
class CountDistance(val compare: (a: Double, b: Double) -> Boolean): ClientCommand(CommandType.SINGLE_ARG) {
    override fun execute(arguments: ArrayList<CommandArgument>): ArrayList<Packet> {
        return Packet("print_to_client", arrayListOf(
            MyString(try {
                val distance: Double = caster.toString(arguments[0]).toDouble()
                val counter = collectionManager.collection.filter { compare(it.getDistance(), distance) }.count()
                "$counter element(s)"
            } catch (e: Exception) {
                "Wrong distance format"
            })
        )).wrapIntoArray()
    }

}
//CountDistance { a: Double, b: Double -> a == b }
//CountDistance { a: Double, b: Double -> a < b }