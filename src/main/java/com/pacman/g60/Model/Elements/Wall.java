package com.pacman.g60.Model.Elements;


import com.pacman.g60.Model.Elements.Hierarchy.CanSharePosition;
import com.pacman.g60.Model.Elements.Hierarchy.LivingElement;
import com.pacman.g60.Model.Elements.StaticElement;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;

public class Wall extends StaticElement  {
    public Wall(Position pos){
        super(pos);

    }

    @Override
    public boolean beCollected(ArenaModel arenaModel) {
        return false;
    }
}
