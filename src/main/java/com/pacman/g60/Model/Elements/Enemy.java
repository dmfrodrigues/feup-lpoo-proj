package com.pacman.g60.Model.Elements;


import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.Hierarchy.AttackerElement;
import com.pacman.g60.Model.Elements.Hierarchy.LivingElement;
import com.pacman.g60.Model.Position;

public abstract class Enemy extends DynamicElement implements LivingElement, AttackerElement {
    protected Effect effect;
    private Integer health;

    @Override
    public Object clone() {
        Enemy res = (Enemy)super.clone();
        res.effect = (Effect)effect.clone();
        res.health = Integer.valueOf(health);
        return res;
    }

    public Enemy(Position pos, Effect effect, Integer health) {
        super(pos);
        this.effect = effect;
        this.health = health;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setHealth(Integer health)
    {
        this.health = health;
    }

    public Integer getHealth()
    {
        return this.health;
    }

    @Override
    public void updateHealth(Integer diff)
    {
        this.health += diff;
    }

    @Override
    public boolean isAlive()
    {
        return health > 0;
    }

}
