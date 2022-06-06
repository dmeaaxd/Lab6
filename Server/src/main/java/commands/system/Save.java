package commands.system;

import collections.ArrayListCollection;
import collections.JavaIO;
import commands.CommandArgs;
import commands.CommandsToCollection;
import commands.ServerResult;
import structure.HumanBeing;
import сoloringText.ColorClass;

import java.util.ArrayList;
import java.util.Iterator;

public class Save extends CommandsToCollection {
    public Save() {
        super("save", CommandArgs.NO_ARGS, "сохранить коллекцию в файл");

    }

    public ServerResult function(String ... arguments) {

        try {
            Iterator iterator = ArrayListCollection.entitiesCollection.iterator();

            while(iterator.hasNext()) {
                HumanBeing obj = (HumanBeing)iterator.next();
                JavaIO.writeToFile(obj.csvToString() + "\n");
            }
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("Файл сохранен");
            return new ServerResult(arrayList,true);
        }
        catch (Exception e) {
            return new ServerResult(false);
        }
    }

}
