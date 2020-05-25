package com.pacman.g60.Model.Elements;



import com.pacman.g60.Application;
import com.pacman.g60.Model.Elements.Hierarchy.MovableElement;
import com.pacman.g60.Model.Elements.Hierarchy.OrientedElement;
import com.pacman.g60.Model.Position;

public abstract class DynamicElement extends Element implements MovableElement, OrientedElement {
    protected Application.Direction dir;
    public DynamicElement(Position pos){
        super(pos);
        dir = Application.Direction.RIGHT;
    }

    public Position moveUp(){
        Position pos = getPos();
        pos.setY(pos.getY()-1);
        return pos;
    }
    public Position moveDown(){
        Position pos = getPos();
        pos.setY(pos.getY()+1);
        return pos;
    }
    public Position moveLeft(){
        Position pos = getPos();
        pos.setX(pos.getX()-1);
        return pos;
    }
    public Position moveRight(){
        Position pos = getPos();
        pos.setX(pos.getX()+1);
        return pos;
    }

    public Application.Direction getDirection() { return dir; }

    private void updateDir(Position newPos)
    {
        Position currentPos = this.getPos();

        Integer diffY = newPos.getY() - currentPos.getY();
        Integer diffX = newPos.getX() - currentPos.getX();

        if (diffY == 1) this.dir = Application.Direction.DOWN;
        else if (diffY == -1 ) this.dir = Application.Direction.UP;
        else if (diffX == 1) this.dir = Application.Direction.RIGHT;
        else if (diffX == -1) this.dir = Application.Direction.LEFT;
    }
    
    public void updatePos(Position newPos)
    {
        updateDir(newPos);
        this.setPos(newPos);
    }

}
