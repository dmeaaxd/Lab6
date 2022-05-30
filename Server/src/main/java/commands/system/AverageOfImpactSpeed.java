package commands.system;

import collections.ArrayListCollection;
import commands.CommandsToCollection;
import commands.ServerResult;
import structure.HumanBeing;

import java.util.ArrayList;

import static commands.CommandArgs.NO_ARGS;

public class AverageOfImpactSpeed extends CommandsToCollection {
    public AverageOfImpactSpeed() {
        super("averange_of_impact_speed", NO_ARGS, "вывести среднее значение поля impactSpeed для всех элементов коллекции");
    }

    public ServerResult function(String ... arguments) {
        long sum = 0;
        int amount = 0;
        try {
            for (HumanBeing humanBeing : ArrayListCollection.entitiesCollection) {
                sum += humanBeing.getImpactSpeed();
                amount += 1;
            }
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("Среднее значение ImpactSpeed для всех элементов = " + (sum / (long)amount));
            return new ServerResult(arrayList,true);
        } catch (Exception e) {
            return new ServerResult(false);
        }
    }
}
