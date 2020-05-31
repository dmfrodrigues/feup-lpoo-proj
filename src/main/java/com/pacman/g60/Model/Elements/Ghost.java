package com.pacman.g60.Model.Elements;


import com.pacman.g60.Model.DamageEffect;
import com.pacman.g60.Model.Elements.Hierarchy.*;
import com.pacman.g60.Model.Path_Calculation.ShortestPathStrategy;
import com.pacman.g60.Model.Position;

public class Ghost extends Enemy implements FollowHeroElement, CanSharePosition, MeleeAttackerElement, OrientedElement {
    public Ghost(Position pos){
        super(pos, new DamageEffect(1),5);
    }


    @Override
    public Position beMoved(ShortestPathStrategy<Position> shortestPathStrategy) {
        return shortestPathStrategy.getPrev(this.getPos());
    }
}
