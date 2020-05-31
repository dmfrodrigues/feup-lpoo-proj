package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.DamageEffect;
import com.pacman.g60.Model.Elements.Hierarchy.MeleeAttackerElement;
import com.pacman.g60.Model.Path_Calculation.ShortestPathStrategy;
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

   public GuardMovementStrategy getMovement()
   {
       return this.movement;
   }

    @Override
    public Object clone()
    {
        Guard guard = (Guard) super.clone();
        guard.movement = this.movement;
        return guard;
    }

    @Override
    public Position beMoved(ShortestPathStrategy<Position> shortestPathStrategy) {
        return this.movement.tryMove();
    }
}
