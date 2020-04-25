public class Game {
    private ArenaModel arena;
    private ArenaView arenaView;
    private ArenaController arenaController;
    public Game(ArenaView arenaView){
        this.arena = new ArenaModel();
        this.arenaView = arenaView;
        this.arenaController = new ArenaController(arena, arenaView);
    }
    public void run(){
        arenaController.run();
    }
}
