package collections;

import commands.AbstractCommand;
import commands.system.*;
import lombok.Getter;

import java.util.HashMap;

public class CommandCollection {
    private static CommandCollection instance;
    private CommandCollection(){}
    public static synchronized CommandCollection getInstance(){
        if(instance == null){
            instance = new CommandCollection();
        }
        return instance;
    }
    @Getter
    private HashMap<String, AbstractCommand> serverCollection = new HashMap();

    public synchronized static void commandManager() {
        new Connect();
        new Add();
        new AverageOfImpactSpeed();
        new Clear();
        new Info();
        new RemoveById();
        new RemoveLower();
        new Save();
        new Show();
        new UpdateId();
    }
}
