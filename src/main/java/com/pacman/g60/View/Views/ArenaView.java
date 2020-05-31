package com.pacman.g60.View.Views;



import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.View.GUI.GUI;

import java.time.Duration;

public abstract class ArenaView extends GUIView {
    ArenaModel arenaModel = null;
    
    public ArenaView(GUI gui){
        super(gui);
    }
    
    public void setArenaModel(ArenaModel arenaModel){ this.arenaModel = arenaModel; }
    protected ArenaModel getArenaModel(){ return arenaModel; }

    public abstract void setTime(Duration time);
}
