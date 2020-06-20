/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Model.Elements;

import com.pacman.g60.Application;
import com.pacman.g60.Model.DamageEffect;
import com.pacman.g60.Model.Elements.Hierarchy.OrientedElement;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;

public class Bullet extends Collectable implements OrientedElement {
    private DamageEffect effect;
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

    public DamageEffect getEffect()
    {
        return this.effect;
    }

    public boolean getMoving()
    {
        return this.moving;
    }

    public Position getNextPos()
    {
        Position nextPos = null;
        Position currentPos = this.getPos();
        switch(this.dir)
        {
            case UP:
            {
                nextPos = new Position(currentPos.getX(),currentPos.getY() - 1);
                break;
            }
            case DOWN:
            {
                nextPos = new Position(currentPos.getX(),currentPos.getY() + 1);
                break;
            }
            case LEFT:
            {
                nextPos = new Position(currentPos.getX() - 1,currentPos.getY());
                break;
            }
            case RIGHT:
            {
                nextPos = new Position(currentPos.getX() + 1,currentPos.getY());
                break;
            }
        }
        return nextPos;
    }

    @Override
    public Application.Direction getDirection() {
        return dir;
    }
}
