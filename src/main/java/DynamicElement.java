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


    public void updatePos(Position newPos)
    {
        Position currentPos = this.pos;
        int currentX = currentPos.getX();
        int currentY = currentPos.getY();
        int newX = newPos.getX();
        int newY = newPos.getY();

        int diffY = newY - currentY;
        if (diffY == 1) this.dir = Direction.DOWN;
        else if (diffY == -1 ) this.dir = Direction.UP;

        int diffX = newX - currentX;
        if (diffX == 1) this.dir = Direction.RIGHT;
        else if (diffX == -1) this.dir = Direction.LEFT;

        this.pos = newPos;
    }

}
