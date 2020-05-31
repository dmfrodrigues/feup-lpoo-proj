package com.pacman.g60.Model.Models;

import com.pacman.g60.Model.Models.ArenaModel;

public class LevelModel {
    Integer level;
    ArenaModel arenaModel;
    public LevelModel(Integer level, ArenaModel arenaModel){ 
        this.level = level;
        this.arenaModel = new ArenaModel(arenaModel);
    }
    public ArenaModel getArenaModelClone(){
        return new ArenaModel(arenaModel);
    }
    public Integer getLevel() { return level; }
}
