public abstract class StaticElement extends Element{


    public StaticElement(Position pos){
        super(pos);

    }

    @Override
    public boolean isObstacle() {
        return true;
    }

    @Override
    public boolean isEnemy() {
        return false;
    }
}
