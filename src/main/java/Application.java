public class Application {
    public static void main(String[] argv){
        TerminalGUI terminalGUI = new LanternaGUI();
        ArenaViewTerminal arenaViewTerminal = new ArenaViewTerminal(terminalGUI);
        Game game = new Game(arenaViewTerminal);
        game.run();
    }
}
