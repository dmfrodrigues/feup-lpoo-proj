package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Position;

import java.util.List;
import java.util.Queue;

public class GuardMovement {
    private Queue<Position> positions;

    public GuardMovement(Queue<Position> positions) {
        this.positions = positions;
    }

    public Position move()
    {
        Position result = positions.poll();
        positions.add(result);
        return result;
    }
}
