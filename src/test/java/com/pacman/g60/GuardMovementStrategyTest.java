package com.pacman.g60;

import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.HorizontalGuardMovementStrategy;
import com.pacman.g60.Model.Elements.VerticalGuardMovementStrategy;
import com.pacman.g60.Model.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class GuardMovementStrategyTest {
    private Position initialPos;

    @Before
    public void setup()
    {
        initialPos = new Position(5,5);
    }

    @Test
    public void horizontalMovement()
    {
        HorizontalGuardMovementStrategy strategy = new HorizontalGuardMovementStrategy(initialPos);

        Queue<Position> expectedQueue = new LinkedList<>();
        Position pos1 = new Position(5,5);
        Position pos2 = new Position(4,5);
        Position pos3 = new Position(3,5);
        Position pos4 = new Position(4,5);
        expectedQueue.add(pos1);
        expectedQueue.add(pos2);
        expectedQueue.add(pos3);
        expectedQueue.add(pos4);
        assertEquals(expectedQueue,strategy.getPositions());
        assertEquals(pos1,strategy.tryMove());

        expectedQueue.poll();
        expectedQueue.add(pos1);
        strategy.move();

        assertEquals(expectedQueue,strategy.getPositions());
    }

    @Test
    public void verticalMovement()
    {
        VerticalGuardMovementStrategy strategy = new VerticalGuardMovementStrategy(initialPos);

        Queue<Position> expectedQueue = new LinkedList<>();
        Position pos1 = new Position(5,5);
        Position pos2 = new Position(5,4);
        Position pos3 = new Position(5,3);
        Position pos4 = new Position(5,4);
        expectedQueue.add(pos1);
        expectedQueue.add(pos2);
        expectedQueue.add(pos3);
        expectedQueue.add(pos4);
        assertEquals(expectedQueue,strategy.getPositions());
        assertEquals(pos1,strategy.tryMove());

        expectedQueue.poll();
        expectedQueue.add(pos1);
        strategy.move();

        assertEquals(expectedQueue,strategy.getPositions());
    }
}
