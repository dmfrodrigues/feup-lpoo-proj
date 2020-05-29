package com.pacman.g60.View;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public abstract class GUIView {
    public enum COMMAND{ UP, DOWN, LEFT, RIGHT, ESC, EOF, SPACEBAR, P }
    
    GUI gui;
    public GUIView(GUI gui){
        this.gui = gui;
    }
    
    public final void clear(){ gui.clear(); }
    public abstract  void draw();
    public final void refresh() throws IOException { gui.refresh(); }
    public final COMMAND pollCommand() throws IOException {
        KeyStroke key = gui.pollKey();
        if(key == null) return null;
        if(key.getKeyType() == KeyType.ArrowUp                               ) return COMMAND.UP;
        if(key.getKeyType() == KeyType.ArrowDown                             ) return COMMAND.DOWN;
        if(key.getKeyType() == KeyType.ArrowLeft                             ) return COMMAND.LEFT;
        if(key.getKeyType() == KeyType.ArrowRight                            ) return COMMAND.RIGHT;
        if(key.getKeyType() == KeyType.Escape                                ) return COMMAND.ESC;
        if(key.getKeyType() == KeyType.EOF                                   ) return COMMAND.EOF;
        if(key.getKeyType() == KeyType.Character && key.getCharacter() == ' ') return COMMAND.SPACEBAR;
        if(key.getKeyType() == KeyType.ArrowDown && key.getCharacter() == 'P') return COMMAND.P;
        return null;
    }
}
