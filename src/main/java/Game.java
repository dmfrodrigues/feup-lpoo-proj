import java.io.IOException;

public class Game {
    private ArenaModel arenaModel;
    private ArenaView arenaView;
    private ArenaController arenaController;
    public Game(ArenaModel arenaModel, ArenaView arenaView){
        this.arenaModel = arenaModel;
        this.arenaView = arenaView;
        this.arenaController = new ArenaController(arenaModel, arenaView);
    }
    public void run() throws IOException {
        arenaController.run();
    }
}
