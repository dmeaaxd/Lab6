package commands;

import collections.CommandCollection;
import exceptions.IncorrectArgsException;
import сoloringText.ColorClass;

public abstract class CommandsToCollection extends AbstractCommand{
    @Override
    public abstract Result function(String... args) ;

    public CommandsToCollection(String name, CommandArgs commandArgs, String description) {
        super(name, commandArgs, description);
        if(!(CommandCollection.getCommandColl().containsKey(name))){
            CommandCollection.getCommandColl().put(name,this);
        }
        if(!(CommandCollection.getClientCommands().containsKey(name))){
            CommandCollection.getClientCommands().put(name,description);
        }

    }
    public String[] checkTypeArgs(String[] args) throws IncorrectArgsException {

        try {
            return ArgsValidator.argsValidator(getData().getCommandArgs(),args);
        } catch (IncorrectArgsException e) {
            ColorClass.colorPrintln(ColorClass.RED, e.getMessage());
            throw new IncorrectArgsException();
        }
    }
}
