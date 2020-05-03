package com.pacman.g60.Controller;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeCommand implements Command {
    private List<Command> commandList;

    public CompositeCommand()
    {
        this.commandList = new ArrayList<>();
    }

    public CompositeCommand(List<Command> commandList) {
        this.commandList = commandList;
    }

    public void addCommand(Command command)
    {
        this.commandList.add(command);
    }

    @Override
    public void execute() {
        for (Command command : commandList)
        {
            command.execute();
        }
    }
}
