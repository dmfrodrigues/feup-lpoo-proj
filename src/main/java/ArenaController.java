import java.util.List;

public class ArenaController {
    ArenaModel arenaModel;
    ArenaView arenaView;
    public ArenaController(ArenaModel arenaModel, ArenaView arenaView){
        this.arenaModel = arenaModel;
        this.arenaView = arenaView;
    }
    public void run(){
        System.out.println("Running game");
        List<Element> listElements = arenaModel.getElements();
        for(final Element e: listElements){
            System.out.println(e.getClass().toString());
        }
    }
}
