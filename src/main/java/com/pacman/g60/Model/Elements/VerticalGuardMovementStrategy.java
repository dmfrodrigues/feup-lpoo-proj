/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Position;

import java.util.LinkedList;

public class VerticalGuardMovementStrategy extends GuardMovementStrategy {

    public VerticalGuardMovementStrategy(Position initialPos)
    {
        this.positions = new LinkedList<>();
        initPos(initialPos);
    }

    private void initPos(Position initialPos)
    {
        Position pos1 = initialPos;
        int initialX = initialPos.getX();
        int initialY = initialPos.getY();

        Position pos2 = new Position(initialX,initialY - 1);
        Position pos3 = new Position(initialX,initialY - 2);
        Position pos4 = new Position(initialX,initialY - 1);

        this.positions.add(pos1);
        this.positions.add(pos2);
        this.positions.add(pos3);
        this.positions.add(pos4);
    }
}
