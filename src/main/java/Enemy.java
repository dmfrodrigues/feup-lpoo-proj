import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Enemy extends DynamicElement {
    protected Effect effect;
    protected ArenaModel model;
    protected Node[][] matrix;

    public Enemy(Position pos, ArenaModel model) {
        super(pos);
        this.model = model;
        matrix = new Node[model.getH()][model.getW()];
    }

    private void generateMatrix()
    {
        List<Element> elements = model.getElements();

        for (Element element : elements)
        {
            Node.CellType currentValue;
            if (element.isObstacle()) currentValue = Node.CellType.OBSTACLE;
            else currentValue = Node.CellType.FREE;
            Position currentPos = element.getPos();
            int currentX = currentPos.getX();
            int currentY = currentPos.getY();
            matrix[currentY][currentX] = new Node(currentValue,null,Integer.MAX_VALUE, new Position(currentX,currentY));
        }

        //TODO positions that aren't held by elements need to be FREE

        Position heroPos = model.getHero().getPos();
        int heroX = heroPos.getX();
        int heroY = heroPos.getY();
        matrix[heroY][heroX].setCellType(Node.CellType.HERO);
    }

    private void shortestPath()
    {
        generateMatrix();

        int startX = this.getPos().getX();
        int startY = this.getPos().getY();

        Node start = matrix[startY][startX];

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

            Node leftPos = matrix[currentY][currentX - 1];
            Node rightPos = matrix[currentY][currentX + 1];
            Node upPos = matrix[currentY - 1][currentX];
            Node downPos = matrix[currentY + 1][currentX];

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
                    if (adjNode.getCellType() == Node.CellType.HERO) break;
                }
            }
        }
    }

    public Position calculatePath()
    {
        shortestPath();
        Position heroPos = model.getHero().getPos();
        int heroX = heroPos.getX();
        int heroY = heroPos.getY();
        Node currentNode = matrix[heroY][heroX];

        Position thisEnemyPos = this.pos;
        int thisX = thisEnemyPos.getX();
        int thisY = thisEnemyPos.getY();
        Node thisNode = matrix[thisY][thisX];

        while(currentNode.getPath() != null)
        {
            if (currentNode.getPath() == thisNode) return currentNode.getPosition();
            currentNode = currentNode.getPath();
        }
        return null;
    }
}
