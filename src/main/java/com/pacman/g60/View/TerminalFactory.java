package com.pacman.g60.View;

import java.io.FileNotFoundException;

public class TerminalFactory implements ViewFactory {
    private TerminalGUI terminalGUI;
    public TerminalFactory(TerminalGUI terminalGUI){
        this.terminalGUI = terminalGUI;
    }
    @Override
    public TerminalArenaView createArenaView() throws FileNotFoundException {
        return new TerminalArenaView(terminalGUI);
    }

    @Override
    public MenuView createMenuView() throws FileNotFoundException {
        return new TerminalMenuView(terminalGUI);
    }
}
