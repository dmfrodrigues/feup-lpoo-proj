package com.pacman.g60.View.Views;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.pacman.g60.View.GUI.GUI;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public abstract class GUIView implements Cloneable {
    
    public enum COMMAND{ UP, DOWN, LEFT, RIGHT, ESC, SPACEBAR, P, ENTER, FIRE }
    
    GUI gui;
    
    public GUIView(GUIView guiView){
        gui = guiView.gui;
    }
    
    public GUIView(GUI gui){
        this.gui = gui;
    }

    public GUI getGUI() { return gui; }

    public final void clear(){ gui.clear(); }
    public abstract  void draw();
    public final void refresh() throws IOException { gui.refresh(); }
    public final COMMAND pollCommand() throws IOException {
        KeyStroke key = gui.pollKey();
        if(key == null) return null;
        if(key.getKeyType() == KeyType.ArrowUp                                                      ) return COMMAND.UP;
        if(key.getKeyType() == KeyType.ArrowDown                                                    ) return COMMAND.DOWN;
        if(key.getKeyType() == KeyType.ArrowLeft                                                    ) return COMMAND.LEFT;
        if(key.getKeyType() == KeyType.ArrowRight                                                   ) return COMMAND.RIGHT;
        if(key.getKeyType() == KeyType.Escape                                                       ) return COMMAND.ESC;
        if(key.getKeyType() == KeyType.Enter                                                        ) return COMMAND.ENTER;
        if(key.getKeyType() == KeyType.Character && Character.toUpperCase(key.getCharacter()) == ' ') return COMMAND.SPACEBAR;
        if(key.getKeyType() == KeyType.Character && Character.toUpperCase(key.getCharacter()) == 'P') return COMMAND.P;
        if(key.getKeyType() == KeyType.Character && Character.toUpperCase(key.getCharacter()) == 'F') return COMMAND.FIRE;
        return null;
    }

    @Override
    public GUIView clone() {
        try {
            return getClass().getDeclaredConstructor(GUIView.class).newInstance(this);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
