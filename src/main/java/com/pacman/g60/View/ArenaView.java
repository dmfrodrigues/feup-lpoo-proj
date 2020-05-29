package com.pacman.g60.View;



import com.pacman.g60.Model.ArenaModel;

import java.io.IOException;

public abstract class ArenaView extends GUIView {
    ArenaModel arenaModel = null;
    
    public ArenaView(GUI gui){
        super(gui);
    }
    
    public void setArenaModel(ArenaModel arenaModel){ this.arenaModel = arenaModel; }
    protected ArenaModel getArenaModel(){ return arenaModel; }
    
    public abstract void start();
}
