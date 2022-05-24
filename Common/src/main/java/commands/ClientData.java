package commands;

import lombok.Getter;

import java.io.Serializable;

public class ClientData implements Serializable {
    @Getter
    private final String command;

    @Getter
    private final String[] args;

    public ClientData(String command, String[] args) {
        this.command = command;
        this.args = args;
    }
}
