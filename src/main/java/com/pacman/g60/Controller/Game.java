package com.pacman.g60.Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.ArenaModelLoaderStream;
import com.pacman.g60.Model.LevelModel;
import com.pacman.g60.Model.MenuModel;
import com.pacman.g60.View.ArenaView;
import com.pacman.g60.View.MenuView;
import com.pacman.g60.View.ViewFactory;

public class Game {

    private interface State {
        State run() throws IOException, NoSuchMethodException;
    }

    private final StateMainMenu stateMainMenu;
    private final StateLevelSelector stateLevelSelect;
    private final StateArena stateArena;
    private final StateExit stateExit;

    private class StateMainMenu implements State {
        MenuModel menuModel;
        MenuView menuView;
        public StateMainMenu(MenuView menuView){
            menuModel = new MenuModel();
            menuModel.append(new MenuModel.NormalItem(menuModel,0, "Play"      ));
            menuModel.append(new MenuModel.NormalItem(menuModel,1, "Scoreboard"));

            this.menuView = menuView;
        }
        @Override
        public State run() throws IOException {
            MenuModel menuModel_ = new MenuModel(menuModel);
            MenuController menuController = new MenuController(menuModel_, menuView);
            int r;
            while((r = menuController.run()) != -1){
                switch(r){
                    case 0:
                        return stateLevelSelect;
                    case 1: break;
                }
            }
            return stateExit;
        }
    }
    
    private class StateLevelSelector implements State {
        List<LevelModel> levelModels;
        public StateLevelSelector() throws FileNotFoundException {
            List<String> levelPaths = new ArrayList<>(Arrays.asList(
                    "src/main/resources/maps/map1.map"
            ));
            levelModels = new ArrayList<>();
            for(Integer i = 0; i < levelPaths.size(); ++i){
                InputStream inputStream = new FileInputStream(levelPaths.get(i));
                ArenaModel.Loader arenaModelLoader = new ArenaModelLoaderStream(inputStream);
                levelModels.add(new LevelModel(arenaModelLoader.getArenaModel()));
            }
        }
        @Override
        public State run() throws IOException, NoSuchMethodException {
            stateArena.setArenaModel(levelModels.get(0).getArenaModelClone());
            return stateArena;
        }
    }
    
    private class StateArena implements State {
        ArenaModel arenaModel;
        ArenaView arenaView;
        public StateArena(ArenaView arenaView){
            this.arenaView = arenaView;
        }
        @Override
        public State run() throws IOException {
            ArenaController arenaController = new ArenaController(arenaModel, arenaView);
            arenaController.run();
            return stateMainMenu;
        }

        public void setArenaModel(ArenaModel arenaModel) {
            this.arenaModel = arenaModel;
        }
    }

    private class StateExit implements State {
        @Override
        public State run() throws NoSuchMethodException {
            throw new NoSuchMethodException();
        }
    }
    
    private State state;

    public Game(ViewFactory viewFactory) throws Exception {
        stateMainMenu = new StateMainMenu(viewFactory.createMenuView());
        stateLevelSelect = new StateLevelSelector();
        stateArena = new StateArena(viewFactory.createArenaView());
        stateExit  = new StateExit();
    }
    
    public void run() throws Exception {
        state = stateMainMenu;
        while(! (state instanceof StateExit)){
            state = state.run();
        }
    }
}
