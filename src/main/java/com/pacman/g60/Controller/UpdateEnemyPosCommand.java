package com.pacman.g60.Controller;

import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.Elements.DynamicElement;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Ghost;
import com.pacman.g60.Model.Path_Calculation.BFSShortestPathStrategy;
import com.pacman.g60.Model.Path_Calculation.Graph;
import com.pacman.g60.Model.Path_Calculation.ShortestPathStrategy;
import com.pacman.g60.Model.Position;

import java.util.List;

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
        boolean isNewPosValid = arenaModel.isPositionAvailable(newPos);

        if (newPos != null && isNewPosValid)
        {
            arenaModel.updateMapKey(oldPos,newPos);
            ((DynamicElement) element).updatePos(newPos);
        }

    }
}
