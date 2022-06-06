package commands.system;

import collections.ArrayListCollection;
import commands.CommandArgs;
import commands.CommandsToCollection;
import commands.ServerResult;
import сoloringText.ColorClass;

import java.util.ArrayList;

public class Info extends CommandsToCollection {
    public Info() {
        super("info", CommandArgs.NO_ARGS, "вывести информацию о коллекции");
    }

    public ServerResult function(String ... args) {
        try {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("Тип коллекции - " + String.valueOf(ArrayListCollection.entitiesCollection.getClass()));
            arrayList.add("Дата инициализации - " + String.valueOf(ArrayListCollection.getDataCreation()));
            arrayList.add("Длинна коллекции - " + String.valueOf(ArrayListCollection.entitiesCollection.size()));
            return new ServerResult(arrayList,true);
        } catch (Exception e) {
            return new ServerResult(false);
        }
    }
}
