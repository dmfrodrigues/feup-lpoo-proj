import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArenaController {
    ArenaModel arenaModel;
    ArenaView arenaView;
    private Node[][] arenaMatrix;

    public ArenaController(ArenaModel arenaModel, ArenaView arenaView){
        this.arenaModel = arenaModel;
        this.arenaView = arenaView;
        arenaMatrix = new Node[arenaModel.getH()][arenaModel.getW()];
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

    private void generateMatrix()
    {
        List<Element> elements = arenaModel.getElements();

        for (Element element : elements)
        {
            Node.CellType currentValue;
            if (element.isObstacle()) currentValue = Node.CellType.OBSTACLE;
            else if (element.isEnemy()) currentValue = Node.CellType.ENEMY;
            else currentValue = Node.CellType.FREE;
            Position currentPos = element.getPos();
            int currentX = currentPos.getX();
            int currentY = currentPos.getY();
            arenaMatrix[currentY][currentX] = new Node(currentValue,null,Integer.MAX_VALUE, new Position(currentX,currentY));
        }


        for (int i = 0; i < arenaModel.getH(); i++)
        {
            for (int i2 = 0; i2 < arenaModel.getW(); i2++)
            {
                arenaMatrix[i][i2].setPosition(new Position(i,i2));
            }
        }

    }

    private void shortestPaths()
    {
        generateMatrix();

        int startX = this.arenaModel.getHero().getPos().getX();
        int startY = this.arenaModel.getHero().getPos().getY();

        Node start = arenaMatrix[startY][startX];

        start.setDist(0);
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(start);
        while (!queue.isEmpty())
        {
            Node currentNode = queue.poll();
            Position currentPos = currentNode.getPosition();
            int currentX = currentPos.getX();
            int currentY = currentPos.getY();

            List<Node> adjacent = new ArrayList<Node>();

            Node leftPos = arenaMatrix[currentY][currentX - 1];
            Node rightPos = arenaMatrix[currentY][currentX + 1];
            Node upPos = arenaMatrix[currentY - 1][currentX];
            Node downPos = arenaMatrix[currentY + 1][currentX];

            if (leftPos.getCellType() != Node.CellType.OBSTACLE) adjacent.add(leftPos);
            if (rightPos.getCellType() != Node.CellType.OBSTACLE) adjacent.add(rightPos);
            if (upPos.getCellType() != Node.CellType.OBSTACLE) adjacent.add(upPos);
            if (downPos.getCellType() != Node.CellType.OBSTACLE) adjacent.add(downPos);

            for (Node adjNode : adjacent)
            {
                if (adjNode.getDist() == Integer.MAX_VALUE)
                {
                    queue.add(adjNode);
                    adjNode.setDist(currentNode.getDist() + 1);
                    adjNode.setPath(currentNode);
                }
            }
        }
    }

    public void updateEnemyLocations()
    {
        shortestPaths();

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
