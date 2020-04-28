package com.pacman.g60.Model.Terminal;

import com.pacman.g60.View.TerminalArenaView;
import com.pacman.g60.View.ViewFactory;

public class TerminalFactory implements ViewFactory {
    private TerminalGUI terminalGUI;
    public TerminalFactory(TerminalGUI terminalGUI){
        this.terminalGUI = terminalGUI;
    }
    @Override
    public TerminalArenaView createArenaView() {
        return new TerminalArenaView(terminalGUI);
    }
}
