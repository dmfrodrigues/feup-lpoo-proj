package Model;

import Controller.ArenaController;

import javax.swing.text.View;
import java.io.IOException;
import View.ArenaView;
import View.ViewFactory;

public class Game {
    private ArenaModel arenaModel;
    private ArenaView arenaView;
    private ArenaController arenaController;
    public Game(ArenaModel arenaModel, ViewFactory viewFactory){
        this.arenaModel = arenaModel;
        this.arenaView = viewFactory.createArenaView();
        this.arenaController = new ArenaController(arenaModel, arenaView);
    }
    public void run() throws IOException {
        arenaController.run();
    }
}
