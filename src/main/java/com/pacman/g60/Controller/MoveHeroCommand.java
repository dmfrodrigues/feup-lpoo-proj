/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Controller;

import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Elements.*;
import com.pacman.g60.Model.Position;

import java.util.ArrayList;
import java.util.List;


public abstract class MoveHeroCommand implements Command {
    protected ArenaModel arenaModel;

    public MoveHeroCommand(ArenaModel arenaModel) {
        this.arenaModel = arenaModel;
    }

    public void processMovementConsequences(Position newPos)
    {
        Hero hero = arenaModel.getHero();

        if (this.arenaModel.isPositionAvailable(newPos,hero))
        {
            List<Element> toBeRemoved = new ArrayList<>();

            List<Element> elemsInNewPos = arenaModel.getElemFromPos(newPos);
            if (elemsInNewPos != null)
            {
                for (Element elemInNewPos : elemsInNewPos)
                {
                    if(elemInNewPos.beCollected(this.arenaModel)) toBeRemoved.add(elemInNewPos);
                }

                for (Element elem : toBeRemoved)
                {
                    arenaModel.removeElement(elem);
                }
            }

            Position currentHeroPos = hero.getPos();
            this.arenaModel.updateMapKey(currentHeroPos,newPos,hero);
            this.arenaModel.getHero().updatePos(newPos);
        }
    }
}
