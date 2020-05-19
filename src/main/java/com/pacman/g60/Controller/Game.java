package com.pacman.g60.Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.ArenaModelLoaderStream;
import com.pacman.g60.View.ArenaView;
import com.pacman.g60.View.ViewFactory;

public class Game {
    private ArenaModel arenaModel;
    private ArenaView arenaView;
    private ArenaController arenaController;

    private void processArenaModel() throws FileNotFoundException
    {
        InputStream inputStream = new FileInputStream("src/main/resources/maps/map1.map");
        ArenaModel.Loader arenaModelLoader = new ArenaModelLoaderStream(inputStream);
        arenaModel = arenaModelLoader.getArenaModel();
    }

    private void processOthers(ViewFactory viewFactory)
    {
        this.arenaView = viewFactory.createArenaView();
        this.arenaController = new ArenaController(arenaModel, arenaView);
    }

    public Game(ViewFactory viewFactory) throws FileNotFoundException {
        processArenaModel();
        processOthers(viewFactory);
    }

    public void run() throws IOException {
        arenaController.run();
    }
}
