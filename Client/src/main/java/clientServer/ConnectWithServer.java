package clientServer;

import commands.DataClients;
import commands.DataServer;
import lombok.Getter;
import сoloringText.ColorClass;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ConnectWithServer {
    private int port;
    @Getter
    private InetAddress IPAddress;
    private static ConnectWithServer instance;

    private ConnectWithServer() {
    }

    public static ConnectWithServer getInstance() {
        if (instance == null) {
            instance = new ConnectWithServer();
        }
        return instance;
    }

    public void setIPAddressAndPort(InetAddress inetAddress, int port) {
        IPAddress = inetAddress;
        this.port = port;
    }

    public DataServer connectWithServer(DataClients dataClients) throws IOException {
        DatagramSocket clientSocket = new DatagramSocket();
        byte[] sendingDataBuffer = dataClients.getBytes();
        byte[] receivingDataBuffer = new byte[8192];

        SocketAddress host2 = new InetSocketAddress(IPAddress,port);
        DatagramChannel datagramChannel = DatagramChannel.open();
        ByteBuffer buf ;
        buf= ByteBuffer.wrap(sendingDataBuffer);
        DatagramPacket sendingPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length, IPAddress, port);

        DatagramPacket receivingPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);

        try {
            datagramChannel.send(buf, host2);
        }catch (SocketTimeoutException e){
            System.out.println(ColorClass.red + "Сервер недоступен" + ColorClass.reset);
        }
        catch (IOException e) {
            System.out.println(ColorClass.red + "Проблема с сервером" + ColorClass.reset);
            return null;
        }
        buf.clear();
        buf = ByteBuffer.allocate(8192);
        datagramChannel.receive(buf);

        //todo если сервер недоступен, то улетает в бесконечный цикл (обработка)

        byte[] byteMessage = buf.array();
        DataServer obj;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(byteMessage));
            obj = (DataServer) inputStream.readObject();
        } catch (ClassNotFoundException e) {
            return null;
        }
        clientSocket.close();
        return obj;
    }
}
