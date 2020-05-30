package com.pacman.g60.Model.Models;

import com.pacman.g60.Model.Models.ArenaModel;

public class LevelModel {
    ArenaModel arenaModel;
    public LevelModel(ArenaModel arenaModel){
        this.arenaModel = new ArenaModel(arenaModel);
    }
    public ArenaModel getArenaModelClone(){
        return new ArenaModel(arenaModel);
    }
}
