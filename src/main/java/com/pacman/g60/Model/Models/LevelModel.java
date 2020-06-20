/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Model.Models;

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
