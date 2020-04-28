package Model.Elements;

import Model.Application;
import Model.Position;

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
    
    public void updatePos(Position newPos)
    {
        Position currentPos = this.pos;
        int currentX = currentPos.getX();
        int currentY = currentPos.getY();
        int newX = newPos.getX();
        int newY = newPos.getY();

        int diffY = newY - currentY;
        if (diffY == 1) this.dir = Application.Direction.DOWN;
        else if (diffY == -1 ) this.dir = Application.Direction.UP;

        int diffX = newX - currentX;
        if (diffX == 1) this.dir = Application.Direction.RIGHT;
        else if (diffX == -1) this.dir = Application.Direction.LEFT;

        this.pos = newPos;
    }

}
