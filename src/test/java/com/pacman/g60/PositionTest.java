package com.pacman.g60;

import com.pacman.g60.Model.Position;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class PositionTest {

    @Test
    public void getX()
    {
        Position position = new Position(2,4);
        assertEquals(((Integer)2),position.getX());
    }

    @Test
    public void getY()
    {
        Position position = new Position(5,6);
        assertEquals(((Integer)6),position.getY());
    }

    @Test
    public void setX()
    {
        Position position = new Position(10,32);
        position.setX(5);
        assertEquals(((Integer)5),position.getX());
    }

    @Test
    public void setY()
    {
        Position position = new Position(10,32);
        position.setY(5);
        assertEquals(((Integer)5),position.getY());
    }

    @Test
    public void equals()
    {
        Position position = new Position(10,10);
        Position position2 = new Position(5,5);
        assertNotEquals(position,position2);
        Position position3 = new Position(10,10);
        assertEquals(position, position3);
    }

    @Test
    public void equals2()
    {
        Position position = new Position(10,20);
        Position position2 = new Position(10,20);
        assertEquals(position,position2);
        assertNotEquals(position, null);
        assertNotEquals(position, new Object());
    }
    
    @Test
    public void hashCode_value(){
        final int NPOS = 100;
        final int MINX = -1000000000;
        final int MAXX = +1000000000;
        final int AMPX = MAXX-MINX;
        for(int i = 0; i < NPOS; ++i){
            Integer x = (int)(Math.random()*AMPX + MINX);
            Integer y = (int)(Math.random()*AMPX + MINX);
            Position pos = new Position(x, y);
            assertEquals(pos.hashCode(), x.hashCode()+y.hashCode());
        }
    }
    
    @Test
    public void hashCode_quality(){
        final int NPOS = 1000000;
        final int MINX = -1000000000;
        final int MAXX = +1000000000;
        final int AMPX = MAXX-MINX;
        final double collision_limit = 0.001;
        Set<Integer> hashes = new TreeSet<>();
        for(int i = 0; i < NPOS; ++i){
            int x = (int)(Math.random()*AMPX + MINX);
            int y = (int)(Math.random()*AMPX + MINX);
            Position pos = new Position(x, y);
            hashes.add(pos.hashCode());
        }
        int num_collisions = NPOS - hashes.size();
        double collision = (double)(num_collisions)/(double)(NPOS);
        assertTrue(collision < collision_limit);
    }
    
    @Test
    public void toString_test(){
        final int NPOS = 1000;
        final int MINX = -1000000000;
        final int MAXX = +1000000000;
        final int AMPX = MAXX-MINX;
        for(int i = 0; i < NPOS; ++i){
            int x = (int)(Math.random()*AMPX + MINX);
            int y = (int)(Math.random()*AMPX + MINX);
            Position pos = new Position(x, y);
            assertEquals(pos.toString(), "("+x+", "+y+")");
        }
    }
}
