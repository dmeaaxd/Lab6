package commands.system;

import collection.IdCollection;
import collections.ArrayListCollection;
import commands.CommandArgs;
import commands.CommandsToCollection;
import commands.ServerResult;
import commands.WriteTheValues;
import structure.HumanBeing;
import сoloringText.ColorClass;

import java.util.ArrayList;

public class UpdateId extends CommandsToCollection {
    public UpdateId() {
        super("updateId", CommandArgs.FILLING_ALL_ARGS, "обновить значение элемента коллекции, id которого равен заданному. Вы должны написать: \n   String name, Integer x, Double y, Boolean realhero, Boolean hasToothpick, Long impactSpeed,\n WeaponType weaponType, Mood mood, boolean cool");
    }

    public ServerResult function(String... arguments) {
        if (ArrayListCollection.entitiesCollection.isEmpty()) {
            System.out.println(ColorClass.red + "Коллекция пуста" + ColorClass.reset);
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(ColorClass.red + "Коллекция пуста" + ColorClass.reset);
            return new ServerResult(arrayList, false);
        }

        String[] local = arguments;
        Long id;
        id = Long.parseLong(arguments[0]);
        if (!IdCollection.idCollection.contains(id)) {
            System.out.println(ColorClass.red + "Данные некорректны (элемента с таким id нет). Попробуйте снова" + ColorClass.reset);
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("Данные некорректны (элемента с таким id нет). Попробуйте снова");
            return new ServerResult(arrayList, false);
        }
        ArrayList<HumanBeing> clone = new ArrayList<>();
        for (int i = 0; ArrayListCollection.entitiesCollection.size() > 0; i++) {
            HumanBeing lol;
            if ((lol = (HumanBeing) ArrayListCollection.entitiesCollection.get(i)).getId() != id) {
                clone.add(lol);
            }
        }

        ArrayListCollection.entitiesCollection = clone;
        WriteTheValues.createObject(arguments);
        return new ServerResult(true);

    }
}
