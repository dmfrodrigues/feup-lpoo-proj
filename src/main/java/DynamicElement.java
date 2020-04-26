public abstract class DynamicElement extends Element {
    protected Application.Direction dir;
    public DynamicElement(Position pos){
        super(pos);
        dir = Application.Direction.RIGHT;
    }

    public void moveUp(){
        Position pos = getPos();
        pos.setY(pos.getY()-1);
        setPos(pos);
        dir = Application.Direction.UP;
    }
    public void moveDown(){
        Position pos = getPos();
        pos.setY(pos.getY()+1);
        setPos(pos);
        dir = Application.Direction.DOWN;
    }
    public void moveLeft(){
        Position pos = getPos();
        pos.setX(pos.getX()-1);
        setPos(pos);
        dir = Application.Direction.LEFT;
    }
    public void moveRight(){
        Position pos = getPos();
        pos.setX(pos.getX()+1);
        setPos(pos);
        dir = Application.Direction.RIGHT;

    }

    public Application.Direction getDirection() { return dir; }

}
