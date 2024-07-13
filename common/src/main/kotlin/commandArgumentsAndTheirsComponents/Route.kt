package commandArgumentsAndTheirsComponents

import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
/***
 * Класс, экземляры которого содержаться в коллекции
 * @author Demid0
 * @since 1.0
 * @param name Название тура
 * @param coordinates Местоположение
 * @param from Откуда
 * @param to Куда
 * @param distance Расстояние
 * @param id Id генерируется автоматически
 * @param creationDate Дата создания экземпляра класса
 */
@Serializable
class Route (private var name: String,
             private var coordinates: Coordinates,
             private var from: Location?,
             private var to: Location,
             private var distance: Double
) : CommandArgument() {
    private var id: Long = abs(UUID.randomUUID().mostSignificantBits)
    private var creationDate = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Date())

    override fun toString(): String {
        return "name: $name\ncoordinates: ${coordinates.toString()}\nfrom(Location): ${from.toString()}" +
                "\nto(Location): ${to.toString()}\ndistance: $distance\nid: $id\ncreationDate: $creationDate"
    }

    fun getId(): Long = id
    fun getName(): String = name
    fun getCoordinates(): Coordinates = coordinates
    fun getFrom(): Location? = from
    fun getTo(): Location = to
    fun getDistance(): Double = distance
    fun getCreationDate() = creationDate

    fun update(name: String = this.name,
               coordinates: Coordinates = this.coordinates,
               from: Location? = this.from,
               to: Location = this.to,
               distance: Double = this.distance) {
        this.name = name
        this.coordinates = coordinates
        this.from = from
        this.to = to
        this.distance = distance
    }

}
