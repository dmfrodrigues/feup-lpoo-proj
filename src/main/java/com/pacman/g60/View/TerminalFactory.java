package com.pacman.g60.View;

public class TerminalFactory implements ViewFactory {
    private TerminalGUI terminalGUI;
    public TerminalFactory(TerminalGUI terminalGUI){
        this.terminalGUI = terminalGUI;
    }
    @Override
    public TerminalArenaView createArenaView() {
        try {
            return new TerminalArenaView(terminalGUI);
        } catch(Exception e){
            return null;
        }
    }
}
