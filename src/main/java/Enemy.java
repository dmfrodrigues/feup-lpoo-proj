import java.util.List;

public abstract class Enemy extends DynamicElement {
    protected Effect effect;
    protected ArenaModel model;
    protected int[][] matrix;

    public Enemy(Position pos, ArenaModel model) {
        super(pos);
        this.model = model;
        matrix = new int[model.getH()][model.getW()];
    }

    private void generateMatrix()
    {
        List<Element> elements = model.getElements();

        for (Element element : elements)
        {
            int currentValue;
            if (element.isObstacle()) currentValue = 1;
            else currentValue = 0;
            Position currentPos = element.getPos();
            int currentX = currentPos.getX();
            int currentY = currentPos.getY();
            matrix[currentY][currentX] = currentValue;
        }


    }

    public Position calculatePath()
    {
        generateMatrix();


    }
}
