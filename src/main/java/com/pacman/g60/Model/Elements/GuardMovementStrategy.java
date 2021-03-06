/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Position;

import java.util.Queue;

public abstract class GuardMovementStrategy {
    protected Queue<Position> positions;

    public Position tryMove()
    {
        return positions.peek();
    }

    public Position move()
    {
        Position result = positions.poll();
        positions.add(result);
        return result;
    }

    public Queue<Position> getPositions()
    {
        return this.positions;
    }
}
