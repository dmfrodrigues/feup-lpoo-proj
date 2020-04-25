import java.util.List;

public class ArenaViewTerminal implements ArenaView {
    private TerminalGUI terminalGUI;
    public ArenaViewTerminal(TerminalGUI terminalGUI){
        this.terminalGUI = terminalGUI;
    }

    @Override
    public void draw(ArenaModel arena) {
        List<Element> listElements = arena.getElements();
        for(final Element e : listElements){
            System.out.println(e.getClass().toString());
        }
    }
}
