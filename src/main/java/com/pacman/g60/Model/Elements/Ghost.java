package com.pacman.g60.Model.Elements;


import com.pacman.g60.Model.Effect.DamageEffect;
import com.pacman.g60.Model.Elements.Enemy;
import com.pacman.g60.Model.Elements.Hierarchy.CanSharePosition;
import com.pacman.g60.Model.Elements.Hierarchy.LivingElement;
import com.pacman.g60.Model.Elements.Hierarchy.MeleeAttackerElement;
import com.pacman.g60.Model.Elements.Hierarchy.OrientedElement;
import com.pacman.g60.Model.Position;

public class Ghost extends Enemy implements CanSharePosition, MeleeAttackerElement, OrientedElement {
    public Ghost(Position pos){
        super(pos, new DamageEffect(1),5);
    }

}
