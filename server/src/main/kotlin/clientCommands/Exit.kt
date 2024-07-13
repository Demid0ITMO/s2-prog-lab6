package clientCommands

import commandArgumentsAndTheirsComponents.CommandArgument
import utils.Packet
import commandArgumentsAndTheirsComponents.CommandType
import java.io.File
import java.io.PrintWriter

/***
 * exit : завершить программу (без сохранения в файл)
 * @author Demid0
 * @since 1.0
 */
class Exit: ClientCommand(CommandType.NO_ARG) {
    override fun execute(arguments: ArrayList<CommandArgument>): ArrayList<Packet> {
        //save collection
        val collection = collectionManager.collection
        var file = File(collectionManager.getFileName())
        var fileWriter = PrintWriter(file.outputStream(), true)
        fileWriter.println(serializator.serialize(collection))
        fileWriter.close()
        //save info about collection
        file = File(collectionManager.getInfoFileName())
        fileWriter = PrintWriter(file.outputStream(), true)
        fileWriter.println(collectionManager.collection.javaClass.simpleName.lowercase())
        fileWriter.println(serializator.getChosenStrategy().javaClass.simpleName.lowercase())
        fileWriter.close()
        return Packet("end_client_session", arrayListOf()).wrapIntoArray()
    }
}