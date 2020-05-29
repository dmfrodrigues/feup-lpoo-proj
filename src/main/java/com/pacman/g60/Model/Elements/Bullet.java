package com.pacman.g60.Model.Elements;

import com.pacman.g60.Application;
import com.pacman.g60.Model.Effect.DamageEffect;
import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.Hierarchy.OrientedElement;
import com.pacman.g60.Model.Position;

public class Bullet extends Collectable implements OrientedElement {
    private Effect effect;
    private Application.Direction dir;

    public Bullet(Position pos) {
        super(pos);
        this.effect = new DamageEffect(1);
        this.dir = Application.Direction.UP;
    }

    public void setDir(Application.Direction newDir)
    {
        this.dir = newDir;
    }

    @Override
    public Application.Direction getDirection() {
        return dir;
    }
}
