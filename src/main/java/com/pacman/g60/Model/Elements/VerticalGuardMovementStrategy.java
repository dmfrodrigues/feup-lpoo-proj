package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class VerticalGuardMovementStrategy {
    private Queue<Position> positions;

    public VerticalGuardMovementStrategy(Position initialPos)
    {
        this.positions = new LinkedList<>();
        initPos(initialPos);
    }

    private void initPos(Position initialPos)
    {

    }


}
