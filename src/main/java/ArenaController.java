import java.io.IOException;

public class ArenaController {
    ArenaModel arenaModel;
    ArenaView arenaView;
    public ArenaController(ArenaModel arenaModel, ArenaView arenaView){
        this.arenaModel = arenaModel;
        this.arenaView = arenaView;
    }
    public void run() throws IOException {
        while(true){
            arenaView.draw(arenaModel);
        }
    }
}
