/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.DamageEffect;
import com.pacman.g60.Model.Position;

public class Sword extends Weapon {
    public Sword(Position pos)
    {
        super(pos, new DamageEffect(2));
    }
}
