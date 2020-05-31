package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;

public class HealthPotion extends Collectable {
    final static Integer health = 5;
    public HealthPotion(Position pos){ super(pos); }

    @Override
    public boolean beCollected(ArenaModel arenaModel) {
        Hero hero = arenaModel.getHero();
        hero.updateHealth(health);
        return true;
    }

}
