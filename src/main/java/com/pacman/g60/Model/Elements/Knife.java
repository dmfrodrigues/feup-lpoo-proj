/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.DamageEffect;

public class Knife extends Weapon {
    public Knife()
    {
        super(null, new DamageEffect(1));
    }
}
