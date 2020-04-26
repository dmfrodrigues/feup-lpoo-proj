public abstract class DynamicElement extends Element {
    Direction dir;
    public DynamicElement(Position pos){
        super(pos);
        dir = new EastDirection();
    }
    public void setPos(Position pos){ this.pos = pos; }
    public void moveUp(){
        Position pos = getPos();
        pos.setY(pos.getY()-1);
        setPos(pos);
        dir = new NorthDirection();
    }
    public void moveDown(){
        Position pos = getPos();
        pos.setY(pos.getY()+1);
        setPos(pos);
        dir = new SouthDirection();
    }
    public void moveLeft(){
        Position pos = getPos();
        pos.setX(pos.getX()-1);
        setPos(pos);
        dir = new WestDirection();
    }
    public void moveRight(){
        Position pos = getPos();
        pos.setX(pos.getX()+1);
        setPos(pos);
        dir = new EastDirection();
    }

    public Direction getDirection() { return dir; }
}
