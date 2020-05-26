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
    private MenuModel menuModel;
    private MenuView menuView;

    private void createArenaModel() throws FileNotFoundException
    {
        InputStream inputStream = new FileInputStream("src/main/resources/maps/map1.map");
        ArenaModel.Loader arenaModelLoader = new ArenaModelLoaderStream(inputStream);
        arenaModel = arenaModelLoader.getArenaModel();
    }
    
    private void createMenuModel() {
        menuModel = new MenuModel();
        menuModel.append(new MenuModel.NormalItem(menuModel,0, "Play"      ));
        menuModel.append(new MenuModel.NormalItem(menuModel,1, "Scoreboard"));
    }

    public Game(ViewFactory viewFactory) throws Exception {
        arenaView = viewFactory.createArenaView();
        menuView = viewFactory.createMenuView();
        createArenaModel();
        createMenuModel();
    }

    public void run() throws Exception {
        MenuModel menuModel_ = new MenuModel(menuModel);
        MenuController menuController = new MenuController(menuModel_, menuView);
        int r;
        while((r = menuController.run()) != -1){
            switch(r){
                case 0:
                    ArenaModel arenaModel_ = new ArenaModel(arenaModel);
                    ArenaController arenaController = new ArenaController(arenaModel_, arenaView);
                    arenaController.run();
                    break;
                case 1: break;
            }
        }
    }
}
