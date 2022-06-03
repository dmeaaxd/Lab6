package commands.system;

import collections.ArrayListCollection;
import commands.CommandArgs;
import commands.CommandsToCollection;
import commands.ServerResult;
import structure.HumanBeing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveById extends CommandsToCollection {
    public RemoveById() {
        super("remove_by_id", CommandArgs.ID_ARGS, "удалить элемент коллекции по его id");
    }

    public ServerResult function(String... arguments) {
        Long id = Long.parseLong(arguments[0]);

        List<HumanBeing> list = ArrayListCollection.getEntitiesCollection().stream().filter(x->x.getId()==id).collect(Collectors.toCollection(ArrayList::new));
        ArrayListCollection.getEntitiesCollection().removeAll(list);
        return new ServerResult(true);
    }
}
