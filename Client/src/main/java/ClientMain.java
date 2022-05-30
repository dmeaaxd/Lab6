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

public class ClientMain {
    public static void main(String[] args) {
        CommandCollection.commandManager();
        Scanner scanner = new Scanner(System.in);
        System.out.println(ColorClass.blue + "Введите help для получении справки по командам" + ColorClass.reset);

        while (true) {
            String command;
            String[] arguments;
            String strArgs;
            if (!scanner.hasNext()) {
                System.out.println(ColorClass.red + "Ошибочный ввод. Попробуйте снова" + ColorClass.reset);
                System.exit(0);
            }

            String input;
            while (true) {
                try {
                    input = scanner.nextLine();
                    break;
                } catch (IllegalStateException e) {
                    System.out.println(ColorClass.red + "Ошибочный ввод. Попробуйте снова" + ColorClass.reset);
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
                System.out.println(ColorClass.red + "Такой команды не существует. Попробуйте снова" + ColorClass.reset);
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
                    System.out.println(ColorClass.red + "Сервер недоступен" + ColorClass.reset);
                }
            }
        }
    }
}
