package commands;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;

public class Result {
    @Getter
    private ArrayList<String> message;
    @Getter
    private boolean command;
    @Getter
    private HashSet<CommandData> hashSet;

    public Result(ArrayList<String> message, boolean command) {
        this.message = message;
        this.command = command;
    }

    public Result(boolean command) {
        if (message == null) {
            if (command) {
                message = new ArrayList<>();
            } else {
                message = new ArrayList<>();
                message.add("Ошибка в команде");
            }
        }
        this.command = command;
    }

    public Result(HashSet<CommandData> hashSet, boolean command) {
        this.hashSet = hashSet;
        this.command = command;
        if (command) {
            message = new ArrayList<>();
        } else {
            message = new ArrayList<>();
            message.add("Ошибка в команде");
        }
    }
}
