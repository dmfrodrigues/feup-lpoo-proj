package com.pacman.g60.Controller;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pacman.g60.Model.*;
import com.pacman.g60.View.ArenaView;
import com.pacman.g60.View.MenuView;
import com.pacman.g60.View.ViewFactory;

public class Game {

    private interface State {
        State run() throws IOException, NoSuchMethodException;
    }

    private final StateMainMenu stateMainMenu;
    private final StateControls stateControls;
    private final StateScoreboard stateScoreboard;
    private final StateSave stateSave;
    private final StateLoad stateLoad;
    private final StateLevelSelector stateLevelSelect;
    private final StateArena stateArena;
    private final StateWin stateWin;
    private final StateLose stateLose;
    private final StatePause statePause;
    private final StateExit stateExit;

    private class StateMainMenu implements State {
        MenuModel menuModel;
        MenuView menuView;
        public StateMainMenu(MenuView menuView){
            this.menuView = menuView;
            
            menuModel = new MenuModel();
            menuModel.setFrame(true);
            menuModel.setRelativePosition(new RelativePosition(0.5, 0.5));
            menuModel.setVerticalAlign(MenuModel.VerticalAlign.CENTER);
            menuModel.setHorizontalAlign(MenuModel.HorizontalAlign.CENTER);
            menuModel.append(new MenuModel.NormalItem(menuModel,0, "Play"      ));
            menuModel.append(new MenuModel.NormalItem(menuModel,1, "Controls"  ));
            menuModel.append(new MenuModel.NormalItem(menuModel,2, "Scoreboard"));
            menuModel.append(new MenuModel.NormalItem(menuModel,3, "Save game" ));
            menuModel.append(new MenuModel.NormalItem(menuModel,4, "Load game" ));
        }
        @Override
        public State run() throws IOException {
            MenuModel menuModel_ = new MenuModel(menuModel);
            MenuController menuController = new MenuController(menuModel_, menuView);
            int r = menuController.run();
            switch(r){
                case -1: return stateExit;
                case 0: return stateLevelSelect;
                case 1: return stateControls;
                case 2: return stateScoreboard;
                case 3: return stateSave;
                case 4: return stateLoad;
                default: throw new IndexOutOfBoundsException();
            }
        }
    }
    
    private class StateControls implements State {
        @Override
        public State run() {
            return stateMainMenu;
        }
    }
    
    private class StateScoreboard implements State {
        @Override
        public State run() {
            return stateMainMenu;
        }
    }

    private class StateSave implements State {
        @Override
        public State run() {
            return stateMainMenu;
        }
    }

    private class StateLoad implements State {
        @Override
        public State run() {
            return stateMainMenu;
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
        ArenaController arenaController = null;
        boolean mustContinueRunning = false;
        public StateArena(ArenaView arenaView){
            this.arenaView = arenaView;
        }
        @Override
        public State run() throws IOException {
            if(arenaController == null) arenaController = new ArenaController(arenaModel, arenaView);
            if(mustContinueRunning) {
                arenaController.continueRunning();
            }
            arenaController.run();
            
            if(!arenaController.isOver()) return statePause;
            if(arenaController.isWin()) return stateWin;
            else                        return stateLose;
        }

        public void setArenaModel(ArenaModel arenaModel) {
            this.arenaModel = arenaModel;
            arenaController = null;
            mustContinueRunning = false;
        }

        public void continueRunning() {
            mustContinueRunning = true;
        }
    }
    
    private class StateWin implements State {
        MenuModel menuModel;
        MenuView menuView;
        public StateWin(MenuView menuView){
            this.menuView = menuView;
            
            menuModel = new MenuModel();
            menuModel.setRelativePosition(new RelativePosition(0.5, 0.4));
            menuModel.setVerticalAlign(MenuModel.VerticalAlign.TOP);
            menuModel.setHorizontalAlign(MenuModel.HorizontalAlign.CENTER);
            menuModel.append(new MenuModel.NormalItem(menuModel, 0, "Continue playing"));
            menuModel.append(new MenuModel.NormalItem(menuModel, 1, "Back to main menu"));
        }
        @Override
        public State run() throws IOException {
            MenuModel menuModel_ = new MenuModel(menuModel);
            MenuController menuController = new MenuController(menuModel_, menuView);
            int r = menuController.run();
            switch(r){
                case -1: return stateExit;
                case 0:
                    stateArena.continueRunning();
                    return stateArena;
                case 1: return stateMainMenu;
                default: throw new IndexOutOfBoundsException();
            }
        }
    }

    private class StateLose implements State {
        MenuModel menuModel;
        MenuView menuView;
        public StateLose(MenuView menuView){
            this.menuView = menuView;

            menuModel = new MenuModel();
            menuModel.setRelativePosition(new RelativePosition(0.5, 0.5));
            menuModel.setVerticalAlign(MenuModel.VerticalAlign.CENTER);
            menuModel.setHorizontalAlign(MenuModel.HorizontalAlign.CENTER);
            menuModel.append(new MenuModel.NormalItem(menuModel, 0, "To level selector"));
            menuModel.append(new MenuModel.NormalItem(menuModel, 1, "Back to main menu"));
        }
        @Override
        public State run() throws IOException {
            MenuModel menuModel_ = new MenuModel(menuModel);
            MenuController menuController = new MenuControllerPause(menuModel_, menuView);
            int r = menuController.run();
            switch(r){
                case -1: return stateExit;
                case 0: return stateLevelSelect;
                case 1: return stateMainMenu;
                default: throw new IndexOutOfBoundsException();
            }
        }
    }

    private class StatePause implements State {
        MenuModel menuModel;
        MenuView menuView;
        public StatePause(MenuView menuView){
            this.menuView = menuView;

            menuModel = new MenuModel();
            menuModel.setRelativePosition(new RelativePosition(0.5, 0.5));
            menuModel.setVerticalAlign(MenuModel.VerticalAlign.CENTER);
            menuModel.setHorizontalAlign(MenuModel.HorizontalAlign.CENTER);
            menuModel.append(new MenuModel.NormalItem(menuModel, 0, "Unpause game"));
            menuModel.append(new MenuModel.NormalItem(menuModel, 1, "Back to main menu"));
        }
        @Override
        public State run() throws IOException {
            MenuModel menuModel_ = new MenuModel(menuModel);
            MenuController menuController = new MenuControllerPause(menuModel_, menuView);
            int r = menuController.run();
            switch(r){
                case -1: return stateExit;
                case 0:
                    if(!stateArena.arenaModel.getShouldGameContinue()) stateArena.continueRunning();
                    return stateArena;
                case 1: return stateMainMenu;
                default: throw new IndexOutOfBoundsException();
            }
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
        stateMainMenu       = new StateMainMenu(viewFactory.createMenuView());
        stateControls       = new StateControls();
        stateScoreboard     = new StateScoreboard();
        stateSave           = new StateSave();
        stateLoad           = new StateLoad();
        stateLevelSelect    = new StateLevelSelector();
        stateArena          = new StateArena(viewFactory.createArenaView());
        stateWin            = new StateWin(viewFactory.createMenuView());
        stateLose           = new StateLose(viewFactory.createMenuView());
        statePause          = new StatePause(viewFactory.createMenuView());
        stateExit           = new StateExit();
    }
    
    public void run() throws Exception {
        state = stateMainMenu;
        while(! (state instanceof StateExit)){
            state = state.run();
        }
    }
}
