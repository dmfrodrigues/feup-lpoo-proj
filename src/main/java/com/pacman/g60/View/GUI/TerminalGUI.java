package com.pacman.g60.View.GUI;


import com.pacman.g60.View.Color;
import com.pacman.g60.View.GUI.GUI;

public interface TerminalGUI extends GUI {
    void drawCharacter(Integer x, Integer y, Character c, Color f, Color b);
    void drawString(Integer x, Integer y, String s, Color f, Color b);
    
    Integer getW();
    Integer getH();
}
