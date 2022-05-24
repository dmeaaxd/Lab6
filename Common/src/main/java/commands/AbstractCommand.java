package commands;

import lombok.Getter;

public abstract class AbstractCommand implements Command {
    @Getter
    private final CommandData data;


    protected AbstractCommand(String name, CommandArgs commandArgs, String description) {
        this.data = new CommandData(name, commandArgs, description);
    }
}
