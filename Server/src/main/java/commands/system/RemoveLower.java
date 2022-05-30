package commands.system;

import collections.ArrayListCollection;
import commands.CommandArgs;
import commands.CommandsToCollection;
import commands.ServerResult;
import structure.HumanBeing;

import java.util.ArrayList;

public class RemoveLower extends CommandsToCollection {
    public RemoveLower() {
        super("remove_lower", CommandArgs.ID_ARGS, "удалить из коллекции все элементы размером меньше указанного");
    }


    public ServerResult function(String... arguments) {
        try {
            long id = Long.parseLong(arguments[0]);

            ArrayList<HumanBeing> clone = new ArrayList<>();

  // todo Возможно getenitiesCollection (Чекнуть все команды)

            for (int i = 0; ArrayListCollection.entitiesCollection.size() > 0; i++) {
                HumanBeing lol;
                if ((lol = (HumanBeing) ArrayListCollection.entitiesCollection.get(i)).getId() >= id) {
                    clone.add(lol);
                }
            }

            ArrayListCollection.entitiesCollection = clone;
            return new ServerResult(true);
        } catch (Exception e) {
            return new ServerResult(false);
        }
    }
}
