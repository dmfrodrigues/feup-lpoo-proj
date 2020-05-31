package com.pacman.g60.Model.Elements;

import com.pacman.g60.Application;
import com.pacman.g60.Model.Effect.DamageEffect;
import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.Hierarchy.OrientedElement;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;

public class Bullet extends Collectable implements OrientedElement {
    private Effect effect;
    private Application.Direction dir;
    private boolean moving;

    public Bullet(Position pos) {
        super(pos);
        this.effect = new DamageEffect(5);
        this.dir = Application.Direction.UP;
        this.moving = false;
    }

    @Override
    public boolean beCollected(ArenaModel arenaModel) {
        Hero hero = arenaModel.getHero();
        hero.incAmmo();
        return true;
    }

    public void setDir(Application.Direction newDir)
    {
        this.dir = newDir;
    }

    public void setMoving(boolean moving)
    {
        this.moving = moving;
    }

    public Effect getEffect()
    {
        return this.effect;
    }

    public boolean getMoving()
    {
        return this.moving;
    }

    @Override
    public Application.Direction getDirection() {
        return dir;
    }
}
