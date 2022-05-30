package commands.system;

import collections.ArrayListCollection;
import commands.CommandArgs;
import commands.CommandsToCollection;
import commands.ServerResult;
import structure.HumanBeing;
import structure.WeaponType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PrintFieldDescendingWeaponType extends CommandsToCollection {
    public PrintFieldDescendingWeaponType() {
        super("print_field_descending_weapon_type", CommandArgs.NO_ARGS, "вывести значения поля weaponType всех элементов в порядке убывания");
    }

    @Override
    public ServerResult function(String... args) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            List<WeaponType> weaponTypeList = new ArrayList<>();

            for (HumanBeing humanBeing : ArrayListCollection.entitiesCollection) {
                weaponTypeList.add(humanBeing.getWeaponType());
            }
            weaponTypeList.sort(WeaponType::compareTo);

            Iterator iterator = ArrayListCollection.entitiesCollection.iterator();

            while (iterator.hasNext()){
                WeaponType obj = (WeaponType) iterator.next();
                arrayList.add(obj.toString());
            }
            return new ServerResult(arrayList,true);
        }catch (Exception e) {
            return new ServerResult(false);
        }
    }
}
