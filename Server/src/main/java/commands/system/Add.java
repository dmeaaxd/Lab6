package commands.system;

import commands.CommandArgs;
import commands.CommandsToCollection;
import commands.ServerResult;
import commands.WriteTheValues;

public class Add extends CommandsToCollection {
    public Add() {
        super("add", CommandArgs.FILLING_ALL_ARGS_WITHOUT_ID,"добавить новый элемент в коллекцию. Вы должны написать:\n   String name, Integer x, Double y, Boolean realhero, Boolean hasToothpick, Long impactSpeed,\nWeaponType weaponType, Mood mood, Boolean cool" );
    }
    public ServerResult function(String ... arguments) {
        try {
            if (WriteTheValues.createObject(arguments)) {
                return new ServerResult(true);
            } else {
                return new ServerResult(false);
            }
        } catch (Exception e) {
            return new ServerResult(false);
        }
    }
}
