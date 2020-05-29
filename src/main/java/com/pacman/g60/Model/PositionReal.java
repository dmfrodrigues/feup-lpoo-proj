package com.pacman.g60.Model;

public class PositionReal {
    double x, y;
    public PositionReal(double x, double y){
        this.x = x;
        this.y = y;
    }
    public PositionReal(Position pos){
        x = pos.getX();
        y = pos.getY();
    }
    public double getX(){ return x; }
    public double getY(){ return y; }
}
