package com.pacman.g60.Model.Effect;

import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Hierarchy.LivingElement;

public class DamageEffect implements Effect{
    private Integer damage;

    public DamageEffect(Integer damage) {
        this.damage = damage;
    }

    @Override
    public void apply(Element target) {
        if (target instanceof LivingElement)
        {
            ((LivingElement) target).updateHealth(-damage);
        }
    }
}
