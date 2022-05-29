package collections;

import clientServer.ConnectWithServer;
import commands.ArgsValidator;
import commands.DataClients;
import commands.DataServer;
import commands.Result;
import exceptions.IncorrectArgsException;
import сoloringText.ColorClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.PatternSyntaxException;

public class JavaIO {
    public static boolean readScript(String filepath) {
        Set keys = CommandCollection.getCommandColl().keySet();

        Scanner scanner;
        try {
            scanner = new Scanner(new File(filepath));
        } catch (FileNotFoundException e) {
            System.out.println(ColorClass.red + "Проблема с файлом. Попробуйте снова" + ColorClass.reset);
            return false;
        }

        while (scanner.hasNext()) {
            String command;
            String[] arguments;
            String strArgs;
            String input = scanner.nextLine().trim();
            command = input.split(" ")[0];
            Result result;
            try {
                strArgs = input.replaceFirst(command, "").trim();
            } catch (PatternSyntaxException e) {
                strArgs = "";
            }
            arguments = strArgs.split(",");
            System.out.println(ColorClass.blue + "Команда : " + ColorClass.reset + command);
            if (CommandCollection.getClientCommands().containsKey(command)) {

                result = (CommandCollection.getCommandColl().get(command)).function(arguments);

                for (int i = 0; i < result.getMessage().size(); i++) {
                    System.out.println(result.getMessage().get(i));
                }

            } else if (!CommandCollection.getServerCommands().containsKey(command)) {
                System.out.println(ColorClass.red + "Такой команды не существует" + ColorClass.reset);
            } else {
                try {
                    arguments = ArgsValidator.argsValidator(CommandCollection.getServerCommands().get(command).getCommandArgs(), arguments);
                    DataServer dataServer = ConnectWithServer.getInstance().connectWithServer(new DataClients(command, arguments));
                    for (String s : dataServer.getMessage()) {
                        System.out.println(s);
                    }
                } catch(IncorrectArgsException e){
                    System.out.println(e.getMessage());

                } catch(IOException e){
                    System.out.println(ColorClass.red + "Сервер недоступен" + ColorClass.reset);
                }
            }
        }
        return true;
    }
}
