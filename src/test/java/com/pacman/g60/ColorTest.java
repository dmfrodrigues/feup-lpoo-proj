package com.pacman.g60;

import com.pacman.g60.View.Color;
import org.junit.Test;

import static org.junit.Assert.*;

public class ColorTest {
    final int MAX_COLOR = 255;
    final int NUM_CYCLE_RUNS = 100;
    @Test
    public void regular_ctor(){
        for(int i = 0; i < NUM_CYCLE_RUNS; ++i){
            int r = (int)(Math.random()*MAX_COLOR);
            int g = (int)(Math.random()*MAX_COLOR);
            int b = (int)(Math.random()*MAX_COLOR);
            Color c = new Color(r, g, b);
            assertEquals(r, c.getR());
            assertEquals(g, c.getG());
            assertEquals(b, c.getB());
        }
    }
    @Test
    public void ctor_exceptions(){
        try {
            new Color("#abcde");
            fail();
        }catch(Exception e){
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Argument has wrong length", e.getMessage());
        }
        try {
            new Color("#0123456");
            fail();
        }catch(Exception e){
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Argument has wrong length", e.getMessage());
        }
        try {
            new Color("012345");
            fail();
        }catch(Exception e){
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Argument has wrong length", e.getMessage());
        }
        try {
            new Color("0123456");
            fail();
        }catch(Exception e){
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Argument has missing initial '#'", e.getMessage());
        }

    }
    @Test
    public void hex_ctor(){
        for(int i = 0; i < NUM_CYCLE_RUNS; ++i){
            int r = (int)(Math.random()*MAX_COLOR);
            int g = (int)(Math.random()*MAX_COLOR);
            int b = (int)(Math.random()*MAX_COLOR);
            String s = String.format("#%02x%02x%02x", r, g, b);
            Color cs = new Color(s);
            assertEquals(r, cs.getR());
            assertEquals(g, cs.getG());
            assertEquals(b, cs.getB());
            Color c = new Color(r, g, b);
            assertEquals(c, cs);
        }
    }
    @Test
    public void toString_test(){
        for(int i = 0; i < NUM_CYCLE_RUNS; ++i){
            int r = (int)(Math.random()*MAX_COLOR);
            int g = (int)(Math.random()*MAX_COLOR);
            int b = (int)(Math.random()*MAX_COLOR);
            String s = String.format("#%02X%02X%02X", r, g, b);
            Color cs = new Color(s);
            assertEquals(s, cs.toString());
        }
    }
    @Test
    public void equals_text(){
        assertEquals(new Color(127, 127, 127), new Color("#7f7f7f"));
        assertFalse((new Color(127, 127, 127)).equals(null));
        assertFalse((new Color(127, 127, 127)).equals(new Object()));
    }
}
