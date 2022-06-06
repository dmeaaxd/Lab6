import clientServer.ConnectWithServer;
import collections.CommandCollection;
import commands.ArgsValidator;
import commands.DataClients;
import commands.DataServer;
import commands.Result;
import exceptions.IncorrectArgsException;
import сoloringText.ColorClass;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class Client {
    public void start(){
        CommandCollection.commandManager();
        Scanner scanner = new Scanner(System.in);
        ColorClass.colorPrintln(ColorClass.BLUE, "Введите help для получении справки по командам");

        while (true) {
            String command;
            String[] arguments;
            String strArgs;
            if (!scanner.hasNext()) {
                ColorClass.colorPrintln(ColorClass.RED, "Ошибочный ввод. Попробуйте снова");
                System.exit(0);
            }

            String input;
            while (true) {
                try {
                    input = scanner.nextLine();
                    break;
                } catch (IllegalStateException e) {
                    ColorClass.colorPrintln(ColorClass.RED, "Ошибочный ввод. Попробуйте снова");
                }
            }
            input = input.trim();
            if(input.isEmpty())continue;
            command = input.split(" ")[0];
            Result result;
            try {
                strArgs = input.replaceFirst(command, "").trim();
            } catch (PatternSyntaxException e) {
                strArgs = "";
            }
            arguments = strArgs.split(",");
            if (CommandCollection.getClientCommands().containsKey(command)) {


                try {
                    result = (CommandCollection.getCommandColl().get(command)).function(arguments);
                } catch (NullPointerException e) {
                    continue;
                }

                for (int i = 0; i < result.getMessage().size(); i++) {
                    System.out.println(result.getMessage().get(i));
                }

            } else if (!CommandCollection.getServerCommands().containsKey(command)) {
                ColorClass.colorPrintln(ColorClass.RED, "Такой команды не существует. Попробуйте снова");
            } else {
                try {
                    arguments = ArgsValidator.argsValidator(CommandCollection.getServerCommands().get(command).getCommandArgs(), arguments);

                    DataServer dataServer = ConnectWithServer.getInstance().connectWithServer(new DataClients(command, arguments));
                    for (String s : dataServer.getMessage()) {
                        System.out.println(s);
                    }
                } catch (IncorrectArgsException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    ColorClass.colorPrintln(ColorClass.RED, "Сервер недоступен");
                }
            }
        }
    }
}
