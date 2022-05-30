package commands.system;

import collections.ArrayListCollection;
import commands.CommandsToCollection;
import commands.ServerResult;

import static commands.CommandArgs.NO_ARGS;

public class Clear extends CommandsToCollection {
    public Clear() {
        super("clear", NO_ARGS, "очистить коллекцию");
    }

    public ServerResult function(String ... args) {

        try {
            ArrayListCollection.entitiesCollection.clear();
            return new ServerResult(true);
        } catch (Exception e) {
            return new ServerResult(false);
        }
    }
}
