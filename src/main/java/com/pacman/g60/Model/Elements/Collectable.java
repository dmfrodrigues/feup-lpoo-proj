package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Elements.Hierarchy.CanSharePosition;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;

public abstract class Collectable extends StaticElement implements CanSharePosition {
    public Collectable(Position pos){ super(pos); }

}
