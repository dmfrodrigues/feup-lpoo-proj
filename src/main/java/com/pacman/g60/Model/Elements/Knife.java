package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Effect.DamageEffect;
import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Position;

public class Knife extends Weapon {
    public Knife()
    {
        super(null, new DamageEffect(1));
    }
}
