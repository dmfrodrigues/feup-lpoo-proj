package com.pacman.g60.Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.ArenaModelLoaderStream;
import com.pacman.g60.Model.MenuModel;
import com.pacman.g60.View.ArenaView;
import com.pacman.g60.View.MenuView;
import com.pacman.g60.View.ViewFactory;

public class Game {
    private ArenaModel arenaModel;
    private ArenaView arenaView;
    private ArenaController arenaController;
    private MenuModel menuModel;
    private MenuView menuView;
    private MenuController menuController;

    private void processArenaModel() throws FileNotFoundException
    {
        InputStream inputStream = new FileInputStream("src/main/resources/maps/map1.map");
        ArenaModel.Loader arenaModelLoader = new ArenaModelLoaderStream(inputStream);
        arenaModel = arenaModelLoader.getArenaModel();
    }

    private void processOthers(ViewFactory viewFactory) throws Exception {
        this.arenaView = viewFactory.createArenaView();
        this.arenaController = new ArenaController(arenaModel, arenaView);
    }
    
    private void processMenu(ViewFactory viewFactory) throws Exception {
        menuModel = new MenuModel();
        menuModel.append(new MenuModel.NormalItem(0, "Play"      ));
        menuModel.append(new MenuModel.NormalItem(1, "Scoreboard"));

        menuView = viewFactory.createMenuView();
        menuController = new MenuController(menuModel, menuView);
    }

    public Game(ViewFactory viewFactory) throws Exception {
        processArenaModel();
        processOthers(viewFactory);
        processMenu(viewFactory);
    }

    public void run() throws IOException {
        //menuController.run();
         arenaController.run();
    }
}
