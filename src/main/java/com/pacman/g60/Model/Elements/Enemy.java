package com.pacman.g60.Model.Elements;


import com.pacman.g60.Model.DamageEffect;
import com.pacman.g60.Model.Elements.Hierarchy.AttackerElement;
import com.pacman.g60.Model.Elements.Hierarchy.LivingElement;
import com.pacman.g60.Model.Path_Calculation.ShortestPathStrategy;
import com.pacman.g60.Model.Position;

public abstract class Enemy extends DynamicElement implements LivingElement, AttackerElement {
    protected DamageEffect effect;


    @Override
    public Object clone() {
        Enemy res = (Enemy)super.clone();
        res.effect = (DamageEffect) effect.clone();
        res.health = Integer.valueOf(health);
        return res;
    }

    public Enemy(Position pos, DamageEffect effect, Integer health) {
        super(pos);
        this.effect = effect;
        this.health = health;
    }

    public DamageEffect getEffect() {
        return effect;
    }

    public abstract Position beMoved(ShortestPathStrategy<Position> shortestPathStrategy);
}
