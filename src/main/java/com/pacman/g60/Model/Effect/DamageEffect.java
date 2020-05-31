package com.pacman.g60.Model.Effect;

import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Hierarchy.LivingElement;

public class DamageEffect implements Effect{
    private Integer damage;

    @Override
    public Object clone() {
        DamageEffect res = new DamageEffect(this);
        return res;
    }
    public DamageEffect(Integer damage) {
        this.damage = damage;
    }
    public DamageEffect(DamageEffect damageEffect){ this(damageEffect.damage); }
    
    @Override
    public void apply(Element target) {
        if (target instanceof LivingElement)
        {
            ((LivingElement) target).updateHealth(-damage);
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null) return false;
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;

        DamageEffect effect = (DamageEffect) o;

        return this.damage == effect.damage;
    }
}
