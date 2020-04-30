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
    private Element element;
    private Position position;

    public UpdateEnemyPosCommand(Element element, Position position)
    {
        this.element = element;
        this.position = position;
    }

    @Override
    public void execute() {
        if (position != null) ((DynamicElement) element).updatePos(position);
    }
}
