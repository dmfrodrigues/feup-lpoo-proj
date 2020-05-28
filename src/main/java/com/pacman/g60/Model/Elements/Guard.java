package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Effect.DamageEffect;
import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.Hierarchy.MeleeAttackerElement;
import com.pacman.g60.Model.Position;

public class Guard extends Enemy implements MeleeAttackerElement {
    public Guard(Position pos)
    {
        super(pos,new DamageEffect(1),10);
    }

    public Guard(Position pos, Effect effect, Integer health) {
        super(pos, effect, health);
    }
}
