package com.pacman.g60;

import com.pacman.g60.View.Color;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ColorTest {
    final int MAX_COLOR = 255;
    final int GETTERS_NUM_TESTS = 100;
    @Test
    public void regular_ctor(){
        for(int i = 0; i < GETTERS_NUM_TESTS; ++i){
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
    public void hex_ctor(){
        for(int i = 0; i < GETTERS_NUM_TESTS; ++i){
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
}
