package com.pacman.g60.Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.ArenaModelLoaderStream;
import com.pacman.g60.Model.LevelModel;
import com.pacman.g60.Model.MenuModel;
import com.pacman.g60.View.ArenaView;
import com.pacman.g60.View.MenuView;
import com.pacman.g60.View.ViewFactory;

public class Game {
    private List<LevelModel> levelModels;
    private ArenaView arenaView;
    private MenuModel menuModel;
    private MenuView menuView;

    private void createLevels() throws FileNotFoundException {
        final String[] levelPaths = {
                "src/main/resources/maps/map1.map"
        };
        levelModels = ArrayList<>();
        for(Integer i = 0; i < levelPaths.length; ++i){
            InputStream inputStream = new FileInputStream(levelPaths[i]);
            ArenaModel.Loader arenaModelLoader = new ArenaModelLoaderStream(inputStream);
            levelModels.add(new LevelModel(arenaModelLoader.getArenaModel()));
        }
    }
    
    private void createMenuModel() {
        menuModel = new MenuModel();
        menuModel.append(new MenuModel.NormalItem(menuModel,0, "Play"      ));
        menuModel.append(new MenuModel.NormalItem(menuModel,1, "Scoreboard"));
    }

    public Game(ViewFactory viewFactory) throws Exception {
        arenaView = viewFactory.createArenaView();
        menuView = viewFactory.createMenuView();
        createLevels();
        createMenuModel();
    }

    public void run() throws Exception {
        MenuModel menuModel_ = new MenuModel(menuModel);
        MenuController menuController = new MenuController(menuModel_, menuView);
        int r;
        while((r = menuController.run()) != -1){
            switch(r){
                case 0:
                    ArenaModel arenaModel = levelModels.get(0).getArenaModelClone();
                    ArenaController arenaController = new ArenaController(arenaModel, arenaView);
                    arenaController.run();
                    break;
                case 1: break;
            }
        }
    }
}
