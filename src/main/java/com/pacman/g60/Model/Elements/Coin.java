package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Elements.Hierarchy.CanSharePosition;
import com.pacman.g60.Model.Position;

public class Coin extends StaticElement implements CanSharePosition {
    public Coin(Position pos) {
        super(pos);
    }
}
