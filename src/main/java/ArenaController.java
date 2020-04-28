

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
        while(good){
            Graph<Position> G = arenaModel.getGraph();
            ShortestPath<Position> shortestPath = new BFSshortestPath<Position>();
            shortestPath.setGraph(G);
            shortestPath.calcPaths(arenaModel.getHero().getPos());
            List<Element> elements = arenaModel.getElements();
            for (final Element element : elements)
            {
                if (element instanceof Ghost)
                {
                    element.setPos(shortestPath.getPrev(element.getPos()));
                }
            }
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
