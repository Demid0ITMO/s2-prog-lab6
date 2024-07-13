package systemCommands

import clientCommands.utils.CommandParser
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.Caster
import commandArgumentsAndTheirsComponents.CommandArgument
import utils.ReaderManager
import utils.WriterManager
import java.io.BufferedReader

abstract class SystemCommand: KoinComponent {
    internal val caster: Caster by inject()
    internal val scriptStack: ArrayDeque<String> by inject()
    internal val readerStack: HashMap<String, BufferedReader> by inject()
    internal val commandParser : CommandParser by inject()
    internal val writerManager : WriterManager by inject()
    internal val readerManager : ReaderManager by inject()
    abstract fun execute(arguments: ArrayList<CommandArgument>)
}
