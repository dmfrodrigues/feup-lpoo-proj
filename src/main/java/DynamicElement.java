public abstract class DynamicElement extends Element {
    protected Direction dir;
    public DynamicElement(Position pos){
        super(pos);
        dir = Direction.RIGHT;
    }

    public void moveUp(){
        Position pos = getPos();
        pos.setY(pos.getY()-1);
        setPos(pos);
        dir = Direction.UP;
    }
    public void moveDown(){
        Position pos = getPos();
        pos.setY(pos.getY()+1);
        setPos(pos);
        dir = Direction.DOWN;
    }
    public void moveLeft(){
        Position pos = getPos();
        pos.setX(pos.getX()-1);
        setPos(pos);
        dir = Direction.LEFT;
    }
    public void moveRight(){
        Position pos = getPos();
        pos.setX(pos.getX()+1);
        setPos(pos);
        dir = Direction.RIGHT;

    }

    public Direction getDirection() { return dir; }

}
