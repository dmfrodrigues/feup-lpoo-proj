

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ArenaController {
    ArenaModel arenaModel;
    ArenaView arenaView;
    boolean[][] isObstacle;

    public ArenaController(ArenaModel arenaModel, ArenaView arenaView){
        this.arenaModel = arenaModel;
        this.arenaView = arenaView;
        this.isObstacle = new boolean[arenaModel.getH()][arenaModel.getW()];
    }
    public void run() throws IOException {
        boolean good = true;
        while(good){
            updateEnemyLocations();
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

    private void setMatrixValues(List<Element> elements)
    {
        for (Element element : elements)
        {
            Position currentPos = element.getPos();
            int currentX = currentPos.getX();
            int currentY = currentPos.getY();

            if (element instanceof StaticElement) isObstacle[currentY][currentX] = true;
        }
    }

    public List<AdjacencyNode> generateGraph()
    {
        List<AdjacencyNode> result = new ArrayList<>();

        int height = arenaModel.getH();
        int width = arenaModel.getW();

        boolean[][] isObstacle = new boolean[height][width];

        List<Element> elements = arenaModel.getElements();

        setMatrixValues(elements);

        for (int i = 0; i < height; i++)
        {
            for (int i2 = 0; i2 < width; i2++)
            {
                if (!isObstacle[i][i2]) result.add(new AdjacencyNode(null,Integer.MAX_VALUE,new Position(i2,i)));
            }
        }

        return result;
    }

    private void calculatePath(AdjacencyGraph graph)
    {
        List<AdjacencyNode> nodes = (List<AdjacencyNode>) graph.getNodes();
        Position heroPos = arenaModel.getHero().getPos();
        AdjacencyNode start = new AdjacencyNode();

        for (AdjacencyNode node : nodes) {
            if (node.getPosition().equals(heroPos)) start = node;
            break;
        }

        BFSshortestPath shortestPath = new BFSshortestPath();
        shortestPath.calcPath(graph,start);
    }


    public void updateEnemyLocations()
    {
        AdjacencyGraph graph = new AdjacencyGraph(generateGraph());

        calculatePath(graph);

        List<AdjacencyNode> updatedNodes = (List<AdjacencyNode>) graph.getNodes();

        for (AdjacencyNode node : updatedNodes)
        {
            Position currentNodePos = node.getPosition();
            for (DynamicElement element : arenaModel.getDynamicElements())
            {
                Position currentElementPos = element.getPos();
                boolean nodeMatchesElem = currentNodePos.equals(currentElementPos);
                boolean elemIsEnemy = (element instanceof Enemy);
                if (elemIsEnemy && nodeMatchesElem) element.setPos(node.getPath().getPosition());
            }
        }
    }
}
