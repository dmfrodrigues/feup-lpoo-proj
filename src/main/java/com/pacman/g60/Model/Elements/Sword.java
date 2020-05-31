package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Effect.DamageEffect;
import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.Hierarchy.CanSharePosition;
import com.pacman.g60.Model.Position;

public class Sword extends Weapon {
    public Sword(Position pos)
    {
        super(pos, new DamageEffect(2));
    }
}
