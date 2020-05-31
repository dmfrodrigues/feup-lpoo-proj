package com.pacman.g60.Model.Elements;


import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;

import java.lang.reflect.InvocationTargetException;

public abstract class Element implements Cloneable{

    private Position pos;

    @Override
    public Object clone() {
        try {
            return getClass().getDeclaredConstructor(Position.class).newInstance(pos);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Element(Position pos){
        this.pos = pos;
    }

    public Position getPos(){
        return new Position(pos);
    }

    public void setPos(Position position) { this.pos = position; }

    /**
     * Override of equals. Also checks if they are the same class.
     * @param obj   Object to compare with
     * @return      True if they are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return (getClass().equals(obj.getClass()) &&
                pos.equals(((Element)obj).pos));
    }

    public abstract boolean beCollected(ArenaModel arenaModel);

}
