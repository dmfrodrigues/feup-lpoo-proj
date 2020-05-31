package com.pacman.g60;

import com.pacman.g60.Model.Elements.Guard;
import com.pacman.g60.Model.Elements.HorizontalGuardMovementStrategy;
import com.pacman.g60.Model.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GuardTest {
    private Position pos;

    @Before
    public void setup()
    {
        this.pos = new Position(5,5);
    }

    @Test
    public void tryGetNextPos()
    {
        Guard guard = new Guard(pos,new HorizontalGuardMovementStrategy(pos));
        assertEquals(new Position(5,5),guard.tryGetNextPos());
    }
}
