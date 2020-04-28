

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

    public List<AdjacencyNode> generateMatrix()
    {
        List<AdjacencyNode> result = new ArrayList<>();

        List<Element> elements = arenaModel.getElements();

        int height = arenaModel.getH();
        int width = arenaModel.getW();

        boolean[][] isObstacle = new boolean[height][width];

        for (Element element : elements)
        {
            Position currentPos = element.getPos();
            int currentX = currentPos.getX();
            int currentY = currentPos.getY();

            if (element instanceof StaticElement) isObstacle[currentY][currentX] = true;
        }

        for (int i = 0; i < height; i++)
        {
            for (int i2 = 0; i2 < width; i2++)
            {
                if (!isObstacle[i][i2]) result.add(new AdjacencyNode(null,Integer.MAX_VALUE,new Position(i2,i)));
            }
        }

        return result;
    }


    public void updateEnemyLocations(AdjacencyGraph graph)
    {


        for (DynamicElement element : arenaModel.getDynamicElements())
        {
            if (element.getClass() != Hero.class)
            {
                Position currentEnemyPos = element.getPos();
                int currentEnemyX = currentEnemyPos.getX();
                int currentEnemyY = currentEnemyPos.getY();
                Node currentEnemy = arenaMatrix[currentEnemyY][currentEnemyX];
                Position newEnemyPos = currentEnemy.getPath().getPosition();
                element.updatePos(newEnemyPos);
            }
        }
    }
}
