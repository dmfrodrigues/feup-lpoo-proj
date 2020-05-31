package com.pacman.g60;

import com.pacman.g60.Model.Elements.Guard;
import com.pacman.g60.Model.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GuardTest {
    private Guard.MovementType movementType;
    private Position pos;

    @Before
    public void setup()
    {
        this.movementType = Guard.MovementType.HORIZONTAL;
        this.pos = new Position(5,5);
    }

    @Test
    public void initMovement()
    {
        Guard guard = new Guard(pos,movementType);
        assertEquals(Guard.MovementType.HORIZONTAL,guard.getMoveType());
    }

    @Test
    public void tryGetNextPos()
    {
        Guard guard = new Guard(pos,movementType);
        assertEquals(new Position(5,5),guard.tryGetNextPos());
    }
}
