package commands.system;

import collections.ArrayListCollection;
import commands.CommandArgs;
import commands.CommandsToCollection;
import commands.ServerResult;
import structure.HumanBeing;

import java.util.ArrayList;

public class Show extends CommandsToCollection {
    public Show() {
        super("show", CommandArgs.NO_ARGS, "вывести на экран все элементы коллекции");
    }

    public ServerResult function(String ... arguments) {

        try {
            ArrayList<String> arrayList = new ArrayList<>();
            for(int i =0;i< ArrayListCollection.entitiesCollection.size(); i++) {
                HumanBeing local = (HumanBeing)ArrayListCollection.entitiesCollection.get(i);
                arrayList.add(local.toString());
            }
            return new ServerResult(arrayList,true);
        } catch (Exception e) {
            return new ServerResult(false);
        }
    }

}
