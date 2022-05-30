package commands.system;

import collections.CommandCollection;
import commands.*;

import java.util.HashSet;

public class Connect extends CommandsToCollection {
    public Connect() {
        super("connect", CommandArgs.NO_ARGS, "повторная отправка HashMap клиенту с командами сервера");
    }

    @Override
    public ServerResult function(String... args) {
        HashSet<CommandData> hashSet = new HashSet<>();
        for (AbstractCommand abstractCommand: CommandCollection.getInstance().getServerCollection().values()) {
            if(abstractCommand.getData().getName().equals("save"))continue;
            hashSet.add(abstractCommand.getData());
        }
        return new ServerResult(hashSet,true);
    }
}
