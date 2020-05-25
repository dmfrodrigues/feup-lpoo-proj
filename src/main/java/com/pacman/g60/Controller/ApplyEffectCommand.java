package com.pacman.g60.Controller;

import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.Element;

public class ApplyEffectCommand implements Command {
    private Effect effect;
    private Element target;

    public ApplyEffectCommand(Effect effect, Element target) {
        this.effect = effect;
        this.target = target;
    }

    @Override
    public void execute() {
        this.effect.apply(target);
    }
}
