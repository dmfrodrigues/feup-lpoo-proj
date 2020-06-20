/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Controller;

import com.pacman.g60.Model.Elements.Element;

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

    public List<Command> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<Command> commandList)
    {
        this.commandList = commandList;
    }

    @Override
    public void execute() {
        for (Command command : commandList)
        {
            command.execute();
        }
    }
}
