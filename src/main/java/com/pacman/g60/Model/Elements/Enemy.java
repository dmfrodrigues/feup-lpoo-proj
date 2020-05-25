package com.pacman.g60.Model.Elements;


import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.Hierarchy.AttackerElement;
import com.pacman.g60.Model.Elements.Hierarchy.LivingElement;
import com.pacman.g60.Model.Position;

public abstract class Enemy extends DynamicElement implements LivingElement, AttackerElement {
    protected Effect effect;
    private Integer health;

    public Enemy(Position pos, Effect effect, Integer health) {
        super(pos);
        this.effect = effect;
        this.health = health;
    }

    @Override
    public void updateHealth(Integer diff)
    {
        this.health += diff;
    }

}
