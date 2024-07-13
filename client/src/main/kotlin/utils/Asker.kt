package utils

import commandArgumentsAndTheirsComponents.Coordinates
import commandArgumentsAndTheirsComponents.Location
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import commandArgumentsAndTheirsComponents.Route
import java.io.BufferedReader
import java.io.PrintWriter
import java.util.function.Predicate

typealias ToType<T> = (input: String?) -> T

/***
 * Считывает строки из потока ввода значения полей, проверяет их на правильность и конвертирует в нужный тип
 * @author Demid0
 * @since 1.0
 */
class Asker: KoinComponent {
    private val writerManager : WriterManager by inject()
    private val readerManager : ReaderManager by inject()
    private val writer: PrintWriter = writerManager.get()
    private val reader: BufferedReader = readerManager.get()

    private fun <T> ask(
        printHint: String,
        converter: ToType<T>,
        checker: Predicate<T>
    ): T {
        var output: T
        writer.print("$printHint > ")
        writer.flush()
        while (true) {
            try {
                var input = reader.readLine()
                input = if (input == "") null else input
                output = converter(input)
                if (checker.test(output)) break
                else throw Exception()
            } catch (_: Exception) {
                writer.println("Incorrect input. Try again.")
                writer.flush()
            }
        }
        return output
    }

    val ToIntOrNull: ToType<Int?> = { it?.toIntOrNull() }
    val ToLong: ToType<Long> = { it!!.toLong() }
    val ToFloat: ToType<Float> = { it!!.toFloat() }
    val ToFloatOrNull: ToType<Float?> = { it?.toFloatOrNull() }
    val ToDouble: ToType<Double> = { it!!.toDouble() }
    val ToString: ToType<String> = { it!! }

    fun askRoute(): Route {
        val name: String = ask("name", ToString) { it != "" }
        val coordinates = askCoordinates()
        val from: Location? = askNullableLocation("from")
        val to: Location = askLocation("to")
        val distance: Double = ask("distance", ToDouble) { it > 1 }
        return Route(name, coordinates, from, to, distance)
    }

    private fun askCoordinates(): Coordinates {
        val x: Float? = ask("Coordinates - x", ToFloatOrNull) { it == null || it <= 800 }
        val y: Int? = ask("Coordinates - y", ToIntOrNull) { true }
        return Coordinates(x, y)
    }

    private fun askLocation(Lname: String): Location {
        val x: Int? = ask("$Lname - x", ToIntOrNull) { true }
        val y: Float = ask("$Lname - y", ToFloat) { true }
        val z: Long = ask("$Lname - z", ToLong) { true }
        val name: String = ask("$Lname - name", ToString) { it != "" && it.length <= 496}
        return Location(x, y, z, name)
    }

    private fun askNullableLocation(Lname: String): Location? {
        val ans = ask("$Lname is nullable field. If you want to make it null, print \"yes\".", ToString) {true}
        return if (ans == "yes") null
        else askLocation(Lname)
    }
}