/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60;

import com.pacman.g60.Controller.Game;
import com.pacman.g60.View.GUI.LanternaGUI;
import com.pacman.g60.View.Views.TerminalFactory;
import com.pacman.g60.View.GUI.TerminalGUI;
import com.pacman.g60.View.Views.ViewFactory;

public class Application {
    private static final String copyrightNotice =
            "The Cursed Catacombs Copyright (C) 2020 Diogo Rodrigues, João Matos\n" +
            "This program comes with ABSOLUTELY NO WARRANTY; for details see the LICENSE file.\n" +
            "This is free software, and you are welcome to redistribute it\n" +
            "under certain conditions; refer to the LICENSE file for details.\n";
    public static void main(String[] argv) throws Exception {
        System.out.print(copyrightNotice);
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

