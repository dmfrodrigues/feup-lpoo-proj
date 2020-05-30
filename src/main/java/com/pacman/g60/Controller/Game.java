package com.pacman.g60.Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pacman.g60.Model.*;
import com.pacman.g60.Model.Models.*;
import com.pacman.g60.View.Sprite.TerminalSprite;
import com.pacman.g60.View.Sprite.TerminalSpriteLoaderStream;
import com.pacman.g60.View.Views.*;

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
        TextModel title;
        MenuView menuView;
        GUIViewComposite view;
        public StateMainMenu(MenuView menuView, TextView textView){
            this.menuView = menuView;

            view = new GUIViewComposite(menuView.getGUI());
            view.addView(textView);
            view.addView(menuView);
            
            menuModel = new MenuModel();
            menuModel.setFrame(true);
            menuModel.setRelativePosition(new PositionReal(0.5, 0.18));
            menuModel.setVerticalAlign(MenuModel.VerticalAlign.TOP);
            menuModel.setHorizontalAlign(MenuModel.HorizontalAlign.CENTER);
            menuModel.append(new MenuModel.NormalItem(menuModel,0, "Play"      ));
            menuModel.append(new MenuModel.NormalItem(menuModel,1, "Controls"  ));
            menuModel.append(new MenuModel.NormalItem(menuModel,2, "Scoreboard"));
            menuModel.append(new MenuModel.NormalItem(menuModel,3, "Save game" ));
            menuModel.append(new MenuModel.NormalItem(menuModel,4, "Load game" ));
            menuModel.append(new MenuModel.NormalItem(menuModel,5, "Exit"      ));

            title = new TextModel("The Cursed Catacombs");
            title.setPosition(new PositionReal(0.5, 0.10));
            title.setVerticalAlign(Alignable.VerticalAlign.TOP);
            title.setHorizontalAlign(Alignable.HorizontalAlign.CENTER);
            textView.setTextModel(title);
        }
        @Override
        public State run() throws IOException {
            MenuModel menuModel_ = new MenuModel(menuModel);
            menuView.setMenuModel(menuModel_);
            MenuController menuController = new MenuController(menuModel_, view);
            while(true) {
                int r = menuController.run();
                switch (r) {
                    case -1: break;
                    case  0: return stateLevelSelect;
                    case  1: return stateControls;
                    case  2: return stateScoreboard;
                    case  3: return stateSave;
                    case  4: return stateLoad;
                    case  5: return stateExit;
                    default: throw new IndexOutOfBoundsException();
                }
            }
        }
    }
    
    private class StateControls implements State {
        MenuModel menuModel;
        MenuView menuView;
        GUIViewComposite view;

        public StateControls(MenuView menuView, TextView textView){
            this.menuView = menuView;
            
            view = new GUIViewComposite(menuView.getGUI());
            view.addView(textView);
            view.addView(menuView);
            
            menuModel = new MenuModel();
            menuModel.setRelativePosition(new PositionReal(0.5, 0.7));
            menuModel.setVerticalAlign(MenuModel.VerticalAlign.CENTER);
            menuModel.setHorizontalAlign(MenuModel.HorizontalAlign.CENTER);
            menuModel.append(new MenuModel.NormalItem(menuModel, 0, "Back to main menu"));

            String controls =
                    "   ▲               \n" +
                    "  ◀▼▶  Move hero   \n" +
                    "                   \n" +
                    " └───┘ Melee attack\n" +
                    "   F   Shoot bullet\n" +
                    " ESC/P Pause    ";
            TextModel text = new TextModel(controls);
            text.setHorizontalAlign(Alignable.HorizontalAlign.CENTER);
            text.setVerticalAlign(Alignable.VerticalAlign.CENTER);
            text.setPosition(new PositionReal(0.5, 0.4));
            text.setLineHeight(1.33);
            textView.setTextModel(text);
        }
        
        @Override
        public State run() throws IOException {
            MenuModel menuModel_ = new MenuModel(menuModel);
            menuView.setMenuModel(menuModel_);
            MenuController menuController = new MenuController(menuModel_, view);
            int r = menuController.run();
            switch(r){
                case -1:
                case 0:
                    return stateMainMenu;
                default: throw new IndexOutOfBoundsException();
            }
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
        MenuModel menuModel;
        MenuView menuView;
        GUIViewComposite view;
        List<LevelModel> levelModels;
        
        public StateLevelSelector(MenuView menuView, TextView textView) throws FileNotFoundException {
            this.menuView = menuView;
            
            loadLevels();
            
            view = new GUIViewComposite(menuView.getGUI());
            view.addView(textView);
            view.addView(menuView);
            
            TextModel textModel = new TextModel("Select level");
            textModel.setPosition(new PositionReal(0.5, 0.1));
            textModel.setVerticalAlign(Alignable.VerticalAlign.BOTTOM);
            textModel.setHorizontalAlign(Alignable.HorizontalAlign.CENTER);
            textView.setTextModel(textModel);
            
            menuModel = new MenuModel();
            menuModel.setFrame(true);
            menuModel.setRelativePosition(new PositionReal(0.5, 0.15));
            menuModel.setVerticalAlign(Alignable.VerticalAlign.TOP);
            menuModel.setHorizontalAlign(Alignable.HorizontalAlign.CENTER);
            for(int i = 0; i < levelModels.size(); ++i){
                menuModel.append(new MenuModel.NormalItem(menuModel,  i, "   Level " + i + "   "));
            }            
        }
        
        private void loadLevels() throws FileNotFoundException {
            List<String> levelPaths = new ArrayList<>(Arrays.asList(
                    "src/main/resources/maps/map1.map",
                    "src/main/resources/maps/map2.map",
                    "src/main/resources/maps/map3.map"
            ));
            levelModels = new ArrayList<>();
            for(Integer i = 0; i < levelPaths.size(); ++i){
                InputStream inputStream = new FileInputStream(levelPaths.get(i));
                ArenaModel.Loader arenaModelLoader = new ArenaModelLoaderStream(inputStream);
                levelModels.add(new LevelModel(arenaModelLoader.getArenaModel()));
            }
        }
        @Override
        public State run() throws IOException {
            MenuModel menuModel_ = new MenuModel(menuModel);
            menuView.setMenuModel(menuModel_);
            MenuController menuController = new MenuController(menuModel_, view);
            int r = menuController.run();
            if(r == -1) return stateMainMenu;
            stateArena.setArenaModel(levelModels.get(r).getArenaModelClone());
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
            if(arenaController.wasTerminated()) return stateExit;
            if(!arenaController.isOver()) return statePause;
            if(arenaController.isWin()) return stateWin;
            else                        return stateLose;
        }

        public void setArenaModel(ArenaModel arenaModel) {
            this.arenaModel = arenaModel;
            arenaController = null;
            mustContinueRunning = false;
        }

        public ArenaModel getArenaModel() {
            return arenaModel;
        }

        public void continueRunning() {
            mustContinueRunning = true;
        }
    }
    
    private class StateWin implements State {
        MenuModel menuModel;
        TextModel heartsTextModel;
        TextModel coinsTextModel;
        MenuView menuView;
        GUIViewComposite view;
        public StateWin(MenuView menuView, TextView textView, SpriteView spriteView) throws FileNotFoundException {
            this.menuView = menuView;
            
            TextView titleView = textView;
            TextView heartsTextView = textView.clone();
            TextView coinsTextView = textView.clone();
            
            SpriteView heartsView = spriteView;
            SpriteView coinsView = spriteView.clone();

            view = new GUIViewComposite(menuView.getGUI());
            view.addView(titleView);
            view.addView(heartsTextView);
            view.addView(heartsView);
            view.addView(coinsTextView);
            view.addView(coinsView);
            view.addView(menuView);
            
            TextModel title = new TextModel("Level completed");
            title.setPosition(new PositionReal(0.5, 0.30));
            title.setVerticalAlign(Alignable.VerticalAlign.BOTTOM);
            title.setHorizontalAlign(Alignable.HorizontalAlign.CENTER);
            titleView.setTextModel(title);

            TerminalSprite.Loader loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/heart-6-3.lan"));
            SpriteModel heart = new SpriteModel(loader.getTerminalSprite());
            heart.setPosition(new PositionReal(0.45, 0.4));
            heart.setHorizontalAlign(Alignable.HorizontalAlign.RIGHT);
            heart.setVerticalAlign(Alignable.VerticalAlign.CENTER);
            heartsView.setSpriteModel(heart);
            
            heartsTextModel = new TextModel("");
            heartsTextModel.setPosition(new PositionReal(0.45, 0.4));
            heartsTextModel.setHorizontalAlign(Alignable.HorizontalAlign.LEFT);
            heartsTextModel.setVerticalAlign(Alignable.VerticalAlign.CENTER);
            heartsTextView.setTextModel(heartsTextModel);

            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/coin-6-3.lan"));
            SpriteModel coin = new SpriteModel(loader.getTerminalSprite());
            coin.setPosition(new PositionReal(0.45, 0.45));
            coin.setHorizontalAlign(Alignable.HorizontalAlign.RIGHT);
            coin.setVerticalAlign(Alignable.VerticalAlign.CENTER);
            coinsView.setSpriteModel(coin);
           
            coinsTextModel = new TextModel("");
            coinsTextModel.setPosition(new PositionReal(0.45, 0.45));
            coinsTextModel.setHorizontalAlign(Alignable.HorizontalAlign.LEFT);
            coinsTextModel.setVerticalAlign(Alignable.VerticalAlign.CENTER);
            coinsTextView.setTextModel(coinsTextModel);
            
            menuModel = new MenuModel();
            menuModel.setRelativePosition(new PositionReal(0.5, 0.55));
            menuModel.setVerticalAlign(Alignable.VerticalAlign.TOP);
            menuModel.setHorizontalAlign(Alignable.HorizontalAlign.CENTER);
            menuModel.append(new MenuModel.NormalItem(menuModel, 0, "Continue playing"));
            menuModel.append(new MenuModel.NormalItem(menuModel, 1, "Back to main menu"));
        }
        @Override
        public State run() throws IOException {
            ArenaModel arenaModel = stateArena.getArenaModel();
            
            heartsTextModel.setText(" " + arenaModel.getHero().getHealth() + "/" + arenaModel.getHero().getMaxHealth());
            coinsTextModel .setText(" " + arenaModel.getHero().getCoins()  + "/" + (arenaModel.getNumCoins()+arenaModel.getHero().getCoins()));
            
            MenuModel menuModel_ = new MenuModel(menuModel);
            menuView.setMenuModel(menuModel_);
            MenuController menuController = new MenuController(menuModel_, view);
            int r = menuController.run();
            switch(r){
                case -1: return stateMainMenu;
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
        TextModel title;
        MenuView menuView;
        GUIViewComposite view;
        public StateLose(MenuView menuView, TextView textView){
            this.menuView = menuView;

            view = new GUIViewComposite(menuView.getGUI());
            view.addView(textView);
            view.addView(menuView);

            menuModel = new MenuModel();
            menuModel.setRelativePosition(new PositionReal(0.5, 0.5));
            menuModel.setVerticalAlign(MenuModel.VerticalAlign.CENTER);
            menuModel.setHorizontalAlign(MenuModel.HorizontalAlign.CENTER);
            menuModel.append(new MenuModel.NormalItem(menuModel, 0, "To level selector"));
            menuModel.append(new MenuModel.NormalItem(menuModel, 1, "Back to main menu"));

            title = new TextModel("You lost...");
            title.setPosition(new PositionReal(0.5, 0.3));
            title.setVerticalAlign(Alignable.VerticalAlign.BOTTOM);
            title.setHorizontalAlign(Alignable.HorizontalAlign.CENTER);
            textView.setTextModel(title);
        }
        @Override
        public State run() throws IOException {
            MenuModel menuModel_ = new MenuModel(menuModel);
            menuView.setMenuModel(menuModel_);
            MenuController menuController = new MenuController(menuModel_, view);
            int r = menuController.run();
            switch(r){
                case -1: return stateMainMenu;
                case 0: return stateLevelSelect;
                case 1: return stateMainMenu;
                default: throw new IndexOutOfBoundsException();
            }
        }
    }

    private class StatePause implements State {
        MenuModel menuModel;
        TextModel title;
        MenuView menuView;
        GUIViewComposite view;
        public StatePause(MenuView menuView, TextView textView){
            this.menuView = menuView;

            view = new GUIViewComposite(menuView.getGUI());
            view.addView(textView);
            view.addView(menuView);

            menuModel = new MenuModel();
            menuModel.setRelativePosition(new PositionReal(0.5, 0.5));
            menuModel.setVerticalAlign(MenuModel.VerticalAlign.CENTER);
            menuModel.setHorizontalAlign(MenuModel.HorizontalAlign.CENTER);
            menuModel.append(new MenuModel.NormalItem(menuModel, 0, "Unpause game"));
            menuModel.append(new MenuModel.NormalItem(menuModel, 1, "Back to main menu"));

            title = new TextModel("Paused");
            title.setPosition(new PositionReal(0.5, 0.3));
            title.setVerticalAlign(Alignable.VerticalAlign.BOTTOM);
            title.setHorizontalAlign(Alignable.HorizontalAlign.CENTER);
            textView.setTextModel(title);
        }
        @Override
        public State run() throws IOException {
            MenuModel menuModel_ = new MenuModel(menuModel);
            menuView.setMenuModel(menuModel_);
            MenuController menuController = new MenuController(menuModel_, view);
            int r = menuController.run();
            switch(r){
                case -1: return stateArena;
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
        stateMainMenu       = new StateMainMenu(viewFactory.createMenuView(), viewFactory.createTextView());
        stateControls       = new StateControls(viewFactory.createMenuView(), viewFactory.createTextView());
        stateScoreboard     = new StateScoreboard();
        stateSave           = new StateSave();
        stateLoad           = new StateLoad();
        stateLevelSelect    = new StateLevelSelector(viewFactory.createMenuView(), viewFactory.createTextView());
        stateArena          = new StateArena(viewFactory.createArenaView());
        stateWin            = new StateWin(viewFactory.createMenuView(), viewFactory.createTextView(), viewFactory.createSpriteView());
        stateLose           = new StateLose(viewFactory.createMenuView(), viewFactory.createTextView());
        statePause          = new StatePause(viewFactory.createMenuView(), viewFactory.createTextView());
        stateExit           = new StateExit();
    }
    
    public void run() {
        state = stateMainMenu;
        try {
            while (!(state instanceof StateExit)) {
                state = state.run();
            }
        } catch(Exception e){
            if(e instanceof EOFException) {
                System.out.println("Application closed after receiving exception (" + e.getClass().toString() + "): " + e.getMessage());
            } else {
                System.out.println("Application closed unexpectedly after receiving exception (" + e.getClass().toString() + "): " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
