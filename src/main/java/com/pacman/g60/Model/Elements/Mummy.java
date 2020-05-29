package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Effect.DamageEffect;
import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.Hierarchy.FollowHeroElement;
import com.pacman.g60.Model.Elements.Hierarchy.MeleeAttackerElement;
import com.pacman.g60.Model.Position;

public class Mummy extends Enemy implements MeleeAttackerElement, FollowHeroElement {
    public Mummy(Position pos)
    {
        super(pos,new DamageEffect(1),8);
    }

    public Mummy(Position pos, Effect effect, Integer health) {
        super(pos, effect, health);
    }
}
