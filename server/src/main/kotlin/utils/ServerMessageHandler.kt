package utils

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.util.logging.Logger

class ServerMessageHandler: Runnable {
    private val serializator = Serializator()
    private val logger = Logger.getLogger("Handler logger")
    private val socket = DatagramSocket(1488)
    private val clients = HashMap<String, ClientAssistant>()

    override fun run() {
        val byteArray = ByteArray(65535)
        val packet = DatagramPacket(byteArray, byteArray.size)
        receiveMessage(packet)
        val query = unpackMessage(packet)
        val clientName = query.first().clientName
        if (clients[clientName] == null) {
            logger.info("New client: $clientName")
            clients[clientName] = ClientAssistant(clientName)
        }
        logger.info("Message from client $clientName")
        val client = clients[clientName]!!
        val out = client.executeQuery(query)
        logger.info("Answer to client \"$out\"")
        sendMessage(packMessage(out, packet))
    }

    fun receiveMessage(datagramPacket: DatagramPacket) {
        socket.receive(datagramPacket)
    }

    fun unpackMessage(datagramPacket: DatagramPacket) : ArrayList<Packet> {
        return deserializeMessage(String(datagramPacket.data, 0, datagramPacket.length))
    }

    fun packMessage(packets: ArrayList<Packet>, packet: DatagramPacket): DatagramPacket {
        val byteArray = serializeMessage(packets).toByteArray()
        packet.setData(byteArray, 0, byteArray.size)
        return packet
    }

    fun sendMessage(datagramPacket: DatagramPacket) {
        socket.send(datagramPacket)
    }

    fun serializeMessage(packets: ArrayList<Packet>) : String {
        return serializator.serialize(packets)
    }

    fun deserializeMessage(message: String) : ArrayList<Packet> {
        return serializator.deserialize(message, ArrayList<Packet>())
    }
}