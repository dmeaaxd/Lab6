package ServerMain;

import collections.CommandCollection;
import collections.JavaIO;
import commands.DataClients;
import commands.DataServer;
import commands.ServerResult;
import connect.ConnectWithClient;
import сoloringText.ColorClass;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

    public int PORT = 1000;

    public void start(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CommandCollection.commandManager();
        JavaIO.CSVCreateObject();
        ColorClass.colorPrintln(ColorClass.GREEN, "Сервер работает");

        byte[] arr = new byte[8192];
        DatagramPacket outputPacket;
        int len = arr.length;
        DatagramSocket ds = null;
        DatagramPacket inputPacket;
        while (true) {
            try {
                ds = new DatagramSocket(PORT);
                break;
            } catch (BindException e) {
                ColorClass.colorPrintln(ColorClass.RED, "Порт занят. Укажите другой порт");
                PORT = scanner.nextInt();
            } catch (SocketException e) {
                throw new RuntimeException(e);
            }
        }

        inputPacket = new DatagramPacket(arr, len);

        while (true) {
            ColorClass.colorPrintln(ColorClass.YELLOW, "Ожидание запроса клиента");
            try {
                ds.receive(inputPacket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            byte[] byteMessage = inputPacket.getData();
            DataClients obj;
            InetAddress senderAddress = inputPacket.getAddress();
            int senderPort = inputPacket.getPort();
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(byteMessage));
                obj = (DataClients) inputStream.readObject();
            } catch (ClassNotFoundException e) {
                ColorClass.colorPrintln(ColorClass.RED, "Ошибка. Сервер не видит объект");
                ArrayList<String> message = new ArrayList<>();
                message.add("Ошибка. Сервер не видит объект");
                try {
                    ConnectWithClient.sendToClient(new DataServer(message), ds, senderAddress, senderPort);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                continue;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String command = obj.getCommand();
            ColorClass.colorPrintln(ColorClass.BLUE, "Отправленная с клиента команда: " + command);
            if (!CommandCollection.getInstance().getServerCollection().containsKey(command)) {
                try {
                    ArrayList<String> message = new ArrayList<>();
                    ColorClass.colorPrintln(ColorClass.RED, "Ошибка. Это не команда");
                    message.add("Ошибка. Это не команда: " + command);
                    ConnectWithClient.sendToClient(new DataServer(message), ds, senderAddress, senderPort);
                } catch (IOException e) {
                    ColorClass.colorPrintln(ColorClass.RED, "Пользователь прервал соединение");
                }
            } else {
                String[] arguments = obj.getArgs();
                ServerResult result = (ServerResult) CommandCollection.getInstance().getServerCollection().get(command).function(arguments);
                if (!result.isCommand()) {
                    try {
                        for (String s :
                                result.getMessage()) {
                            System.out.println(s);
                        }
                        ConnectWithClient.sendToClient(result.getDataServer(), ds, senderAddress, senderPort);
                    } catch (IOException e) {
                        ColorClass.colorPrintln(ColorClass.RED, "Проблемы с клиентом");
                    }
                } else {
                    try {
                        ConnectWithClient.sendToClient(result.getDataServer(), ds, senderAddress, senderPort);
                        ColorClass.colorPrintln(ColorClass.BLUE, "Команда " + command + " выполнена");
                    } catch (IOException ex) {
                        ColorClass.colorPrintln(ColorClass.RED, "Проблемы с клиентом");
                    }
                }
            }
        }
    }
}
