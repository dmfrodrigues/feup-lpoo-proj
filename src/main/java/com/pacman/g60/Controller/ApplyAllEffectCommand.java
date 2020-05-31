package com.pacman.g60.Controller;

import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Enemy;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;

import java.util.List;

public class ApplyAllEffectCommand extends CompositeCommand {
    private ArenaModel arenaModel;
    private boolean isThisAHeroAttack;

    public ApplyAllEffectCommand(ArenaModel arenaModel, boolean isThisAHeroAttack) {
        this.arenaModel = arenaModel;
        this.isThisAHeroAttack = isThisAHeroAttack;
    }

    private void setupExecution()
    {
        Hero hero = arenaModel.getHero();
        Position heroPos = hero.getPos();
        Integer heroPosX = heroPos.getX(), heroPosY = heroPos.getY();
        for (int i = heroPosX - 1; i <= heroPosX + 1; i++)
        {
            for (int i2 = heroPosY - 1; i2 <= heroPosY + 1; i2++)
            {
                Position currentPos = new Position(i,i2);
                List<Element> elemsInPos = arenaModel.getElemFromPos(currentPos);
                if (elemsInPos == null) continue;
                for (Element elem : elemsInPos)
                {
                    if (elem instanceof Enemy)
                    {
                        Effect effect;
                        Element target;
                        if (isThisAHeroAttack)
                        {
                            effect = hero.getWeapon().getEffect();
                            target = elem;
                        }
                        else
                        {
                            effect = ((Enemy)elem).getEffect();
                            target = hero;
                        }
                        Command command = new ApplyEffectCommand(effect,target);
                        addCommand(command);
                    }
                }

            }
        }
    }

    @Override
    public void execute() {
        setupExecution();
        super.execute();
    }
}
