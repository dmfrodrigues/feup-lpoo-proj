package com.pacman.g60.Model.Elements;


import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.Hierarchy.AttackerElement;
import com.pacman.g60.Model.Elements.Hierarchy.LivingElement;
import com.pacman.g60.Model.Position;

public abstract class Enemy extends DynamicElement implements LivingElement, AttackerElement {
    protected Effect effect;

    public Enemy(Position pos, Effect effect) {
        super(pos);
        this.effect = effect;
    }

}
