package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.DamageEffect;
import com.pacman.g60.Model.Elements.Hierarchy.MeleeAttackerElement;
import com.pacman.g60.Model.Position;


public class Guard extends Enemy implements MeleeAttackerElement {
    private GuardMovementStrategy movement;


    public Guard(Position pos)
    {
        super(pos,new DamageEffect(1),10);
        this.movement = new HorizontalGuardMovementStrategy(pos);
    }

    public Guard(Position pos, GuardMovementStrategy movementStrategy)
    {
        super(new Position(100,100),new DamageEffect(1),10);
        this.movement = movementStrategy;
    }

    public Position tryGetNextPos()
    {
        return movement.tryMove();
    }

    public Position getNextPos()
    {
        return movement.move();
    }

    @Override
    public Object clone()
    {
        Guard guard = (Guard) super.clone();
        guard.movement = this.movement;
        return guard;
    }
}
