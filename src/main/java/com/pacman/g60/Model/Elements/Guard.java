package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Effect.DamageEffect;
import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.Hierarchy.MeleeAttackerElement;
import com.pacman.g60.Model.Position;


public class Guard extends Enemy implements MeleeAttackerElement {
    private GuardMovementStrategy movement;
    public enum MovementType
    {
        HORIZONTAL,
        VERTICAL
    }
    private MovementType moveType;

    public Guard(Position pos, MovementType moveType)
    {
        super(pos,new DamageEffect(1),10);
        this.moveType = moveType;
        initMovement(pos);
    }

    private void initMovement(Position position)
    {
        if (moveType == MovementType.HORIZONTAL) this.movement = new HorizontalGuardMovementStrategy(position);
        if (moveType == MovementType.VERTICAL  ) this.movement = new VerticalGuardMovementStrategy(position);
    }

    public Position getNextPos()
    {
        return movement.move();
    }

    @Override
    public Object clone()
    {
        Guard guard = (Guard) super.clone();
        guard.moveType = this.moveType;
        guard.movement = this.movement;
        return guard;
    }
}
