package com.pacman.g60.Controller;

import java.io.IOException;

import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.View.ArenaView;
import com.pacman.g60.View.ViewFactory;

public class Game {
    private ArenaModel arenaModel;
    private ArenaView arenaView;
    private ArenaController arenaController;
    public Game(ArenaModel arenaModel, ViewFactory viewFactory){
        this.arenaModel = arenaModel;
        this.arenaView = viewFactory.createArenaView();
        this.arenaController = new ArenaController(arenaModel, arenaView);
    }
    public void run() throws IOException {
        arenaController.run();
    }
}
