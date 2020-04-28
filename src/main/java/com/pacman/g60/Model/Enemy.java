package com.pacman.g60.Model;


import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.DynamicElement;

public abstract class Enemy extends DynamicElement {
    protected Effect effect;

    public Enemy(Position pos, Effect effect) {
        super(pos);
       this.effect = effect;
    }

}
