package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Effect.DamageEffect;
import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.Hierarchy.MeleeAttackerElement;
import com.pacman.g60.Model.Position;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Guard extends Enemy implements MeleeAttackerElement {
    protected GuardMovement movement;

    public Guard(Position pos)
    {
        super(pos,new DamageEffect(1),10);
    }

    public Position getNextPos()
    {
        return movement.move();
    }

    public Guard(Position pos, Effect effect, Integer health) {
        super(pos, effect, health);
    }
}
