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
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerMain {

//    public static String path_to_file = System.getenv("PATH_TO_FILE");
    public static String path_to_file = "/Users/dmeaaxd/Documents/JavaProjects/Lab6ServerClient/file.txt";

    public static int PORT = 1000;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        CommandCollection.commandManager();
        JavaIO.CSVCreateObject();
        System.out.println(ColorClass.green + "Сервер работает" + ColorClass.reset);

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
                System.out.println(ColorClass.red + "Порт занят. Укажите другой порт" + ColorClass.reset);
                PORT = scanner.nextInt();
            }
        }

        inputPacket = new DatagramPacket(arr, len);

        while (true) {
            System.out.println(ColorClass.yellow + "Ожидание запроса клиента" + ColorClass.reset);
            ds.receive(inputPacket);

            byte[] byteMessage = inputPacket.getData();
            DataClients obj = null;
            InetAddress senderAddress = inputPacket.getAddress();
            int senderPort = inputPacket.getPort();
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(byteMessage));
                obj = (DataClients) inputStream.readObject();
            } catch (ClassNotFoundException e) {
                System.out.println(ColorClass.red + "Ошибка. Сервер не видит объект" + ColorClass.reset);
                ArrayList<String> message = new ArrayList<>();
                message.add(ColorClass.red + "Ошибка. Сервер не видит объект" + ColorClass.reset);
                ConnectWithClient.sendToClient(new DataServer(message), ds, senderAddress, senderPort);
                continue;
            }
            String command = obj.getCommand();
            System.out.println(ColorClass.blue + "Отправленная с клиента команда: " + ColorClass.reset + command);
            if (!CommandCollection.getInstance().getServerCollection().containsKey(command)) {
                try {
                    ArrayList<String> message = new ArrayList<>();
                    System.out.println(ColorClass.red + "Ошибка. Это не команда" + ColorClass.reset);
                    message.add(ColorClass.red + "Ошибка. Это не команда: " + ColorClass.reset + command);
                    ConnectWithClient.sendToClient(new DataServer(message), ds, senderAddress, senderPort);
                    continue;
                } catch (IOException e) {
                    System.out.println(ColorClass.red + "Пользователь прервал соединение" + ColorClass.reset);
                }
            } else {
                String[] arguments = obj.getArgs();
                ServerResult result =(ServerResult) CommandCollection.getInstance().getServerCollection().get(command).function(arguments);
                if (!result.isCommand()) {
                    try {
                        for (String s:
                                result.getMessage()) {
                            System.out.println(s);
                        }
                        ConnectWithClient.sendToClient(result.getDataServer(), ds, senderAddress, senderPort);
                        continue;
                    } catch (IOException e) {
                        System.out.println(ColorClass.red + "Проблемы с клиентом" + ColorClass.reset);
                        continue;
                    }
                } else {

                    try {

                        ConnectWithClient.sendToClient(result.getDataServer(), ds, senderAddress, senderPort);
                        System.out.println(ColorClass.blue + "Команда " + command + " выполнена" + ColorClass.reset);
                    } catch (IOException ex) {
                        System.out.println(ColorClass.red + "Проблемы с клиентом" + ColorClass.reset);
                        continue;
                    }
                }
            }
        }
    }
}
