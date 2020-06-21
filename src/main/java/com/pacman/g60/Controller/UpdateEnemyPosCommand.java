/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Controller;

import com.pacman.g60.Model.Elements.Guard;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Elements.DynamicElement;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Position;

public class UpdateEnemyPosCommand implements Command {
    private ArenaModel arenaModel;
    private Element element;
    private Position oldPos, newPos;

    public UpdateEnemyPosCommand(ArenaModel arenaModel,Element element, Position oldPos, Position newPos)
    {
        this.arenaModel = arenaModel;
        this.element = element;
        this.oldPos = oldPos;
        this.newPos = newPos;
    }

    @Override
    public void execute()
    {
        boolean isNewPosValid = arenaModel.isPositionAvailable(newPos,element);

        if (newPos != null && isNewPosValid)
        {
            arenaModel.updateMapKey(oldPos,newPos,element);
            ((DynamicElement) element).updatePos(newPos);

            if (element instanceof Guard) ((Guard) element).getMovement().move();
        }

    }
}
