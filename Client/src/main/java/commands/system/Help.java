package commands.system;

import collections.CommandCollection;
import commands.CommandArgs;
import commands.CommandData;
import commands.CommandsToCollection;
import commands.Result;
import сoloringText.ColorClass;

import java.util.Map;

public class Help extends CommandsToCollection {
    public Help() {
        super("help", CommandArgs.NO_ARGS, "вывести описание доступных команд");
    }

    public Result function(String... args) {
        System.out.println(ColorClass.blue + "|.............Client's commands.............|" + ColorClass.reset);
        for (Map.Entry<String, String> pair : CommandCollection.getClientCommands().entrySet()){
            String key = (String) pair.getKey();
            String value = (String) pair.getValue();
            System.out.println(ColorClass.green + key + ColorClass.reset + " --> " + value);
        }
        if (!CommandCollection.getServerCommands().isEmpty()) {
            System.out.println(ColorClass.blue + "|.............Server's commands.............|" + ColorClass.reset);
            for (Map.Entry<String, CommandData> pair : CommandCollection.getServerCommands().entrySet()){
                String key = pair.getKey();
                CommandData value = pair.getValue();
                System.out.println(ColorClass.green + key + ColorClass.reset + " --> " + value.getDescription());
            }
        }
        return new Result(true);
    }
}
