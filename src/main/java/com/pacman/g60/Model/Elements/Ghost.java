package com.pacman.g60.Model.Elements;


import com.pacman.g60.Model.Effect.DamageEffect;
import com.pacman.g60.Model.Elements.Enemy;
import com.pacman.g60.Model.Elements.Hierarchy.*;
import com.pacman.g60.Model.Position;

public class Ghost extends Enemy implements FollowHeroElement, CanSharePosition, MeleeAttackerElement, OrientedElement {
    public Ghost(Position pos){
        super(pos, new DamageEffect(1),5);
    }

}
