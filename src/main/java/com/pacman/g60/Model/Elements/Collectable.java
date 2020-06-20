/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Elements.Hierarchy.CanSharePosition;
import com.pacman.g60.Model.Position;

public abstract class Collectable extends StaticElement implements CanSharePosition {
    public Collectable(Position pos){ super(pos); }

}
