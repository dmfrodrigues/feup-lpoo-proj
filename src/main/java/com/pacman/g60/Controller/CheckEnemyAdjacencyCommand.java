package com.pacman.g60.Controller;

import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Enemy;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Position;

import java.util.List;

public class CheckEnemyAdjacencyCommand implements Command {
    private ArenaModel arenaModel;

    public CheckEnemyAdjacencyCommand(ArenaModel arenaModel) {
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
                Element currentElemInPos = arenaModel.getElemFromPos(currentPos);
                if (currentElemInPos instanceof Enemy)
                {
                    Effect effect = ((Enemy)currentElemInPos).getEffect();
                    Command command = new ApplyEffectCommand(effect,hero);
                    command.execute();
                }
            }
        }

    }
}
