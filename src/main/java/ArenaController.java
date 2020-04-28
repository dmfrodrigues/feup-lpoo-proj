

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ArenaController {
    ArenaModel arenaModel;
    ArenaView arenaView;


    public ArenaController(ArenaModel arenaModel, ArenaView arenaView){
        this.arenaModel = arenaModel;
        this.arenaView = arenaView;
    }
    public void run() throws IOException {
        boolean good = true;
        int i = 0;
        while(good){
            
            try {
                Thread.sleep(10);
            } catch(InterruptedException e){
                
            }
            
            if(i%10 == 0) {
                Graph<Position> G = arenaModel.getGraph();
                ShortestPath<Position> shortestPath = new BFSshortestPath<Position>();
                shortestPath.setGraph(G);
                shortestPath.calcPaths(arenaModel.getHero().getPos());
                List<Element> elements = arenaModel.getElements();
                for (final Element element : elements) {
                    if (element instanceof Ghost) {
                        Position newPos = shortestPath.getPrev(element.getPos());
                        if (newPos != null) element.setPos(newPos);
                    }
                }
                i = 1;
            }
            ++i;
            
            ArenaView.COMMAND cmd = arenaView.pollCommand();
            if(!(cmd == null)) {
                switch (cmd) {
                    case EXIT:
                        good = false;
                        break;
                    case UP:
                        arenaModel.getHero().moveUp(); break;
                    case DOWN:
                        arenaModel.getHero().moveDown(); break;
                    case LEFT:
                        arenaModel.getHero().moveLeft(); break;
                    case RIGHT:
                        arenaModel.getHero().moveRight(); break;
                }
            }
            arenaView.draw(arenaModel);
        }
    }







}
