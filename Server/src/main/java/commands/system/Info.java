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
            arrayList.add(ColorClass.blue + "Тип коллекции - " + String.valueOf(ArrayListCollection.entitiesCollection.getClass()) + ColorClass.reset);
            arrayList.add(ColorClass.blue + "Дата инициализации - " + String.valueOf(ArrayListCollection.getDataCreation()) + ColorClass.reset);
            arrayList.add(ColorClass.blue + "Длинна коллекции - " + String.valueOf(ArrayListCollection.entitiesCollection.size()) + ColorClass.reset);
            return new ServerResult(arrayList,true);
        } catch (Exception e) {
            return new ServerResult(false);
        }
    }
}
