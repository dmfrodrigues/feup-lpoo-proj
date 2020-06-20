/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;

public class Coin extends Collectable {
    public Coin(Position pos) {
        super(pos);
    }

    @Override
    public boolean beCollected(ArenaModel arenaModel) {
        Hero hero = arenaModel.getHero();
        hero.incCoins();
        return true;
    }
}
