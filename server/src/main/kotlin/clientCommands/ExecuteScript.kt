package clientCommands

import commandArgumentsAndTheirsComponents.CommandArgument
import utils.Packet
import commandArgumentsAndTheirsComponents.CommandType
import commandArgumentsAndTheirsComponents.MyString

/***
 * execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
 * @author Demid0
 * @since 1.0
 */
class ExecuteScript: ClientCommand(CommandType.SINGLE_ARG) {
    override fun execute(arguments: ArrayList<CommandArgument>): ArrayList<Packet> {
        val file_name = caster.toString(arguments[0])
        return Packet("read_from_file", arrayListOf(MyString(file_name))).wrapIntoArray()
    }
}
/***
 * example
 * /home/demid/Desktop/Itmo/programming/Lab5/script*
 *
 */
