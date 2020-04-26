public abstract class Enemy extends DynamicElement {
    protected Effect effect;

    public Enemy(Position pos, Effect effect) {
        super(pos);
       this.effect = effect;
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
