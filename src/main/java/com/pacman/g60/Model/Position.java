/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Model;

public class Position implements Comparable<Position>{
    private Integer x,y;

    public Position(Position pos) {
        this.x = pos.x;
        this.y = pos.y;
    }
    public Position(PositionReal pos){ this((int)pos.getX(), (int)pos.getY()); }

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        return (compareTo((Position)obj) == 0);
    }

    @Override
    public int compareTo(Position position) {
        if(this == position) return 0;
        if(!this.x.equals(position.x)) return this.x - position.x;
        return this.y - position.y;
    }

    @Override
    public int hashCode() {
        return x.hashCode() + y.hashCode();
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
