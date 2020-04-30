package com.pacman.g60;

import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.ArenaModelLoaderStream;
import com.pacman.g60.Controller.Game;
import com.pacman.g60.View.LanternaGUI;
import com.pacman.g60.View.TerminalFactory;
import com.pacman.g60.View.TerminalGUI;
import com.pacman.g60.View.ViewFactory;

import java.io.*;

public class Application {
    public static void main(String[] argv){
        // ViewFactory
        TerminalGUI terminalGUI = new LanternaGUI();
        ViewFactory viewFactory = new TerminalFactory(terminalGUI);
        // game
        try {
            Game game = new Game(viewFactory);
            game.run();
            terminalGUI.close();
        } catch(FileNotFoundException e){
            System.err.println("FileNotFoundException: " + e.getMessage());
        } catch(IOException e){
            System.err.println("IOException: " + e.getMessage());
        }
    }
    public enum Direction {UP,DOWN,LEFT,RIGHT}
}

