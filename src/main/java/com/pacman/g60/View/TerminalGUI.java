package com.pacman.g60.View;


import com.googlecode.lanterna.input.KeyStroke;
import com.pacman.g60.Model.Color;
import com.pacman.g60.Model.GUI;

import java.io.IOException;

public interface TerminalGUI extends GUI {
    void drawCharacter(int x, int y, char c, Color f, Color b);

    KeyStroke pollKey() throws IOException;
    
    Integer getW();
    Integer getH();
}
