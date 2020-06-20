/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Controller;

import com.pacman.g60.Model.DamageEffect;
import com.pacman.g60.Model.Elements.Element;

public class ApplyEffectCommand implements Command {
    private DamageEffect effect;
    private Element target;

    public ApplyEffectCommand(DamageEffect effect, Element target) {
        this.effect = effect;
        this.target = target;
    }

    @Override
    public void execute() {
        this.effect.apply(target);
    }
}
