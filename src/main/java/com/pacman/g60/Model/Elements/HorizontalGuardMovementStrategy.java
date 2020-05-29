package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Position;

import java.util.LinkedList;

public class HorizontalGuardMovementStrategy extends GuardMovementStrategy {

    public HorizontalGuardMovementStrategy(Position initialPos)
    {
        this.positions = new LinkedList<>();
        initPos(initialPos);
    }

    private void initPos(Position initialPos)
    {
        Position pos1 = initialPos;
        int initialX = initialPos.getX();
        int initialY = initialPos.getY();

        Position pos2 = new Position(initialX - 1,initialY);
        Position pos3 = new Position(initialX - 2,initialY);
        Position pos4 = new Position(initialX - 1,initialY);

        this.positions.add(pos1);
        this.positions.add(pos2);
        this.positions.add(pos3);
        this.positions.add(pos4);
    }
}
