package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.DamageEffect;
import com.pacman.g60.Model.Elements.Hierarchy.FollowHeroElement;
import com.pacman.g60.Model.Elements.Hierarchy.MeleeAttackerElement;
import com.pacman.g60.Model.Elements.Hierarchy.OrientedElement;
import com.pacman.g60.Model.Path_Calculation.ShortestPathStrategy;
import com.pacman.g60.Model.Position;

public class Ogre extends Enemy implements FollowHeroElement, MeleeAttackerElement, OrientedElement {
    public Ogre(Position pos){
        super(pos, new DamageEffect(2),8);
    }

    @Override
    public Position beMoved(ShortestPathStrategy<Position> shortestPathStrategy) {
        return shortestPathStrategy.getPrev(this.getPos());
    }
}
