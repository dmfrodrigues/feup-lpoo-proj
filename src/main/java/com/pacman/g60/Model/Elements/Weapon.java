package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Position;

public class Weapon extends Collectable {
    private Effect effect;

    public Weapon(Position pos)
    {
        super(pos);
        this.effect = null;
    }

    public Weapon(Position pos, Effect effect) {
        super(pos);
        this.effect = effect;
    }

    public Effect getEffect()
    {
        return this.effect;
    }
}
