package com.pacman.g60.Controller;

import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Enemy;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Position;

import java.util.List;

public class CheckHeroAdjacencyCommand implements Command {
    private ArenaModel arenaModel;

    public CheckHeroAdjacencyCommand(ArenaModel arenaModel) {
        this.arenaModel = arenaModel;
    }

    @Override
    public void execute() {
        Hero hero = arenaModel.getHero();
        Position heroPos = hero.getPos();
        Integer heroX = heroPos.getX(), heroY = heroPos.getY();
        for (int i = heroX - 1; i <= heroX + 1; i++)
        {
            for (int i2 = heroY - 1; i2 <= heroY + 1; i2++)
            {
                Position currentPos = new Position(i,i2);
                List<Element> elemsInPos = arenaModel.getElemFromPos(currentPos);
                if (elemsInPos == null) continue;
                for (Element elem : elemsInPos)
                {
                    if (elem instanceof Enemy)
                    {
                        Effect effect = ((Enemy)elem).getEffect();
                        Command command = new ApplyEffectCommand(effect,hero);
                        command.execute();
                    }
                }
            }
        }

    }
}
