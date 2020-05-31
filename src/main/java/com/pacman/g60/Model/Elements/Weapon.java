package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.DamageEffect;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;

public abstract class Weapon extends Collectable {
    private DamageEffect effect;

    @Override
    public boolean beCollected(ArenaModel arenaModel) {
        Hero hero = arenaModel.getHero();
        hero.setWeapon(this);
        return true;
    }

    public Weapon(Position pos, DamageEffect effect) {
        super(pos);
        this.effect = effect;
    }

    public DamageEffect getEffect()
    {
        return this.effect;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null) return false;
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;

        Weapon weapon = (Weapon) o;

        return weapon.effect == this.effect;
    }
}
