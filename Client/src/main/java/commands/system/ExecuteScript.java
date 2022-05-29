package commands.system;

import clientServer.ConnectWithServer;
import collections.ExecuteFileCollection;
import collections.JavaIO;
import commands.CommandArgs;
import commands.CommandsToCollection;
import commands.Result;
import сoloringText.ColorClass;

import java.util.ArrayList;

public class ExecuteScript extends CommandsToCollection {
    public ExecuteScript() {
        super("execute_script", CommandArgs.STRING, "прочитать и исполнить скрипт из файла");
    }

    public Result function(String ... arguments) {
        if(ConnectWithServer.getInstance().getIPAddress()==null){
            System.out.println(ColorClass.red + "Ты должен написать" + ColorClass.green + " \"connect\" " + ColorClass.red + "до того как использовать эту команду" + ColorClass.reset);
            return new Result(false);
        }

        String filepath = arguments[0];

        try {
            if (ExecuteFileCollection.getExecuteFileCollection().contains(filepath)) {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(ColorClass.red + "Этот скрипт уже выполнялся" + ColorClass.reset);
                return new Result(arrayList,false);
            } else {
                ExecuteFileCollection.getExecuteFileCollection().add(filepath);
                if (JavaIO.readScript(filepath)) {
                    return new Result(true);
                } else {
                    ArrayList arrayList = new ArrayList<>();
                    arrayList.add(ColorClass.red + "Во время исполнения скрипта произошла ошибка" + ColorClass.reset);
                    return new Result(arrayList,false);
                }
            }
        } catch (Exception e) {
            return new Result(false);
        }
    }
}
