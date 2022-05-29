package commands.system;

import clientServer.ConnectWithServer;
import collections.CommandCollection;
import commands.*;
import exceptions.IncorrectArgsException;
import сoloringText.ColorClass;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

public class Connect extends CommandsToCollection {
    public Connect() {
        super("connect", CommandArgs.CONNECT_ARGS, "установить соединение с сервером");
    }

    @Override
    public Result function(String... args) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            args = checkTypeArgs(args);
        } catch (IncorrectArgsException e) {
            return new Result(false);
        }
        try {
            ConnectWithServer.getInstance().setIPAddressAndPort(InetAddress.getByName(args[0]), Integer.valueOf(args[1]));
            DataClients dataClients = new DataClients("connect", args);

            DataServer dataServer = ConnectWithServer.getInstance().connectWithServer(dataClients);
            for (CommandData commandData : dataServer.getCommandDataHashSet()) {
                CommandCollection.getServerCommands().put(commandData.getName(), commandData);
            }
        } catch (IOException e) {
            System.out.println(ColorClass.red + "Проблемы с сервером" + ColorClass.reset);
            return new Result(false);
        }
        arrayList.add(ColorClass.green + "Команда выполнена" + ColorClass.reset);
        return new Result(arrayList, true);
    }
}
