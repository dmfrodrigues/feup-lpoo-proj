import java.io.*;

public class Application {
    public static void main(String[] argv){
        // arenaModel
        ArenaModel arenaModel;
        try{
            InputStream inputStream = new FileInputStream("src/main/resources/maps/map1.map");
            ArenaModel.Loader arenaModelLoader = new ArenaModelLoaderFile(inputStream);
            arenaModel = arenaModelLoader.getArenaModel();
        } catch(FileNotFoundException e) {
            System.err.println("File not found");
            arenaModel = new ArenaModel(150, 50);
        }
        // arenaView
        TerminalGUI terminalGUI = new LanternaGUI();
        ArenaViewTerminal arenaViewTerminal = new ArenaViewTerminal(terminalGUI);
        // game
        Game game = new Game(arenaModel, arenaViewTerminal);
        try {
            game.run();
        } catch(IOException e){
            System.err.println("IOException");
        }
    }
}
enum Direction {UP,DOWN,LEFT,RIGHT}
