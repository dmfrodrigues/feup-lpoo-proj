package com.pacman.g60.View;


import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public interface TerminalGUI extends GUI {
    void drawCharacter(Integer x, Integer y, Character c, Color f, Color b);

    KeyStroke pollKey() throws IOException;
    
    Integer getW();
    Integer getH();
}
