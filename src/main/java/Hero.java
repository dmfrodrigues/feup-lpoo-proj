public class Hero extends DynamicElement {
    public Hero(Position pos){ super(pos); }

    @Override
    public boolean isObstacle() {
        return false;
    }

    @Override
    public boolean isEnemy() {
        return false;
    }
}
