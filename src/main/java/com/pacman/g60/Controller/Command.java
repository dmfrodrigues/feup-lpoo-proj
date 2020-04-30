package com.pacman.g60.Controller;

import com.pacman.g60.Model.ArenaModel;

public abstract class Command {
    protected ArenaModel arenaModel;

    public Command(ArenaModel arenaModel) {this.arenaModel = arenaModel;}

    public abstract void execute();
}
