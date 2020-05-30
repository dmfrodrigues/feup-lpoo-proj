package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;

public abstract class Weapon extends Collectable {
    private Effect effect;

    public Weapon(Position pos)
    {
        super(pos);
        this.effect = null;
    }

    @Override
    public boolean beCollected(ArenaModel arenaModel) {
        Hero hero = arenaModel.getHero();
        hero.setWeapon(this);
        return true;
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
