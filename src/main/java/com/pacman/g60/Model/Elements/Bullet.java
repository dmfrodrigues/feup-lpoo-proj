package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Effect.DamageEffect;
import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Position;

public class Bullet extends Collectable {
    private Effect effect;

    public Bullet(Position pos) {
        super(pos);
        this.effect = new DamageEffect(1);
    }
}
