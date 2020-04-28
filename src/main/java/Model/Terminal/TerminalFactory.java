package Model.Terminal;

import View.TerminalArenaView;
import View.ViewFactory;

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
