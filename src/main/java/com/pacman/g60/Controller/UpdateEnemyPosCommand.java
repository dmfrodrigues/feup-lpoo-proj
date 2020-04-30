package com.pacman.g60.Controller;

import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Ghost;
import com.pacman.g60.Model.Path_Calculation.BFSShortestPathStrategy;
import com.pacman.g60.Model.Path_Calculation.Graph;
import com.pacman.g60.Model.Path_Calculation.ShortestPathStrategy;
import com.pacman.g60.Model.Position;

import java.util.List;

public class UpdateEnemyPosCommand extends Command {

    public UpdateEnemyPosCommand(ArenaModel arenaModel)
    {
        super(arenaModel);
    }

    @Override
    public void execute() {
        Graph<Position> G = arenaModel.getGraph();
        ShortestPathStrategy<Position> shortestPathStrategy = new BFSShortestPathStrategy<Position>();
        shortestPathStrategy.setGraph(G);
        shortestPathStrategy.calcPaths(arenaModel.getHero().getPos());
        List<Element> elements = arenaModel.getElements();
        for (final Element element : elements) {
            if (element instanceof Ghost) {
                Position newPos = shortestPathStrategy.getPrev(element.getPos());
                if (newPos != null) ((Ghost) element).updatePos(newPos);
            }
        }
    }
}
