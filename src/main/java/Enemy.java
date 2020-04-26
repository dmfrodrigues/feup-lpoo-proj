public abstract class Enemy extends DynamicElement {
    protected Effect effect;
    protected ArenaModel model;
    protected Node[][] matrix;

    public Enemy(Position pos, ArenaModel model) {
        super(pos);
        this.model = model;
        matrix = new Node[model.getH()][model.getW()];
    }

    @Override
    public boolean isObstacle() {
        return false;
    }

    @Override
    public boolean isEnemy() {
        return true;
    }
}
