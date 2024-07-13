package utils

import exceptions.SystemCommandInvocationException
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

class ClientMessageHandler: Runnable, KoinComponent {
    private val serializator: Serializator by inject()
    private val app: App by inject()
    private val writerManager: WriterManager by inject()
    private val readerManager: ReaderManager by inject()
    private val systemCommandInvoker: SystemCommandInvoker by inject()
    private var username: String = ""
    private val channel = DatagramChannel.open()
    private val serverAddress = InetSocketAddress("localhost", 1488)

    override fun run() {
        sendRecieveInvoke(checkout())
        sendRecieveInvoke(getPacket())
    }

    fun sendRecieveInvoke(packet: Packet) {
        packet.clientName = username
        val byteBuffer = packMessage(packet.wrapIntoArray())
        val ansBuffer = ByteBuffer.wrap(ByteArray(65535))
        sendMessage(byteBuffer, serverAddress)
        receiveMessage(ansBuffer)
        val ans = unpackMessage(ansBuffer)
        try {
            systemCommandInvoker.invoke(ans)
        } catch (e: SystemCommandInvocationException) {
            app.setDefaultCondition(e)
        }
    }

    fun checkout() = Packet("checkout", arrayListOf())
    fun getPacket(): Packet {
        var packet = app.run(readerManager, writerManager)
        while (packet == null) packet = app.run(readerManager, writerManager)
        return packet
    }

    fun packMessage(packet: ArrayList<Packet>) : ByteBuffer {
        return ByteBuffer.wrap(serializator.serialize(packet).toByteArray())
    }

    fun unpackMessage(byteBuffer: ByteBuffer): ArrayList<Packet> {
        return serializator.deserialize(String(byteBuffer.array(), 0, byteBuffer.position()), ArrayList<Packet>())
    }

    fun sendMessage(byteBuffer: ByteBuffer, address: InetSocketAddress) {
        channel.send(byteBuffer, address)
    }

    fun receiveMessage(byteBuffer: ByteBuffer) {
        channel.receive(byteBuffer)
    }

    fun login() {
        writerManager.get().println("Enter username:")
        writerManager.get().flush()
        while(username == "") username = readerManager.get().readLine()
    }

}
