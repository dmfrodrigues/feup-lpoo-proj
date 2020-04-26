public class Application {
    public static void main(String[] argv){
        //Direction enum definition

        // arenaModel
        ArenaModel arenaModel = new ArenaModel(150, 50);
        // arenaView
        TerminalGUI terminalGUI = new LanternaGUI();
        ArenaViewTerminal arenaViewTerminal = new ArenaViewTerminal(terminalGUI);
        // game
        Game game = new Game(arenaModel, arenaViewTerminal);
        game.run();
    }
}

public enum Direction {UP,DOWN,LEFT,RIGHT}
