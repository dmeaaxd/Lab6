package commands.system;

import clientServer.ConnectWithServer;
import commands.*;
import сoloringText.ColorClass;

import java.io.IOException;
import java.util.ArrayList;

public class Exit extends CommandsToCollection {
    public Exit() {
        super("exit", CommandArgs.NO_ARGS, "завершить программу");
    }

    public Result function(String ... args) {
        DataClients dataClients = new DataClients("save",args);
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            DataServer dataServer = ConnectWithServer.getInstance().connectWithServer(dataClients);
            System.out.println(dataServer.getMessage());
        } catch (IOException e) {
            ColorClass.colorPrintln(ColorClass.RED, "Проблемы с сервером. Сохранение не удалось");
        }
        System.exit(0);
        return null;
    }
}
