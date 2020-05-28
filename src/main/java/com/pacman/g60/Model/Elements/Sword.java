package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.Hierarchy.CanSharePosition;
import com.pacman.g60.Model.Position;

public class Sword extends Weapon {

    public Sword(Position pos)
    {
        super(pos);
    }

    public Sword(Position pos, Effect effect) {
        super(pos, effect);
    }
}
