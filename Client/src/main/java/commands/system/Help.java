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
        ColorClass.colorPrintln(ColorClass.BLUE, "|.............Client's commands.............|");
        for (Map.Entry<String, String> pair : CommandCollection.getClientCommands().entrySet()){
            String key = (String) pair.getKey();
            String value = (String) pair.getValue();
            ColorClass.colorPrintln(ColorClass.GREEN, key + " --> " + value);
        }
        if (!CommandCollection.getServerCommands().isEmpty()) {
            ColorClass.colorPrintln(ColorClass.BLUE, "|.............Server's commands.............|");
            for (Map.Entry<String, CommandData> pair : CommandCollection.getServerCommands().entrySet()){
                String key = pair.getKey();
                CommandData value = pair.getValue();
                ColorClass.colorPrintln(ColorClass.GREEN, key + " --> " + value.getDescription());
            }
        }
        return new Result(true);
    }
}
