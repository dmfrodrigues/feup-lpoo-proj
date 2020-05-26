package com.pacman.g60.Controller;

import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.Effect.DamageEffect;
import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Enemy;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Position;

import java.util.List;

public class AttackCommand implements Command{
    private ArenaModel arenaModel;

    public AttackCommand(ArenaModel arenaModel) {
        this.arenaModel = arenaModel;
    }

    @Override
    public void execute() {
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
                        Effect effect = new DamageEffect(1);
                        Command command = new ApplyEffectCommand(effect,elem);
                        command.execute();
                    }
                }

            }
        }
    }
}
