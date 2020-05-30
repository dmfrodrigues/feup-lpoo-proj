package com.pacman.g60;

import com.pacman.g60.Controller.Game;
import com.pacman.g60.View.GUI.LanternaGUI;
import com.pacman.g60.View.Views.TerminalFactory;
import com.pacman.g60.View.GUI.TerminalGUI;
import com.pacman.g60.View.Views.ViewFactory;

public class Application {
    public static void main(String[] argv) throws Exception {
        // ViewFactory
        TerminalGUI terminalGUI = new LanternaGUI();
        ViewFactory viewFactory = new TerminalFactory(terminalGUI);
        // game
        try {
            Game game = new Game(viewFactory);
            game.run();
            terminalGUI.close();
        } catch(Exception e){
            System.err.println("Exception (" + e.getClass().toString() + "): " + e.getMessage());
            e.printStackTrace();
        }
    }
    public enum Direction {UP,DOWN,LEFT,RIGHT}
}

