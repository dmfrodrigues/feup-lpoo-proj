package com.pacman.g60.Controller;

import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Ghost;
import com.pacman.g60.Model.Path_Calculation.BFSShortestPathStrategy;
import com.pacman.g60.Model.Path_Calculation.BFSTieBreakerDiag;
import com.pacman.g60.Model.Path_Calculation.Graph;
import com.pacman.g60.Model.Path_Calculation.ShortestPathStrategy;
import com.pacman.g60.Model.Position;

import java.util.List;

public class UpdateAllEnemyPosCommand extends CompositeCommand {
    private final ArenaModel arenaModel;

    public UpdateAllEnemyPosCommand(ArenaModel arenaModel)
    {
        super();
        this.arenaModel = arenaModel;
    }

    public ShortestPathStrategy<Position> calcPath()
    {
        Graph<Position> Graph = arenaModel.getGraph();
        ShortestPathStrategy<Position> shortestPathStrategy = new BFSShortestPathStrategy<Position>(new BFSTieBreakerDiag(arenaModel.getHero().getPos()));
        shortestPathStrategy.setGraph(Graph);
        shortestPathStrategy.calcPaths(arenaModel.getHero().getPos());

        return shortestPathStrategy;
    }

    public void setupCommands(ShortestPathStrategy<Position> shortestPathStrategy)
    {
        List<Element> elements = arenaModel.getElements();
        for (final Element element : elements) {
            if (element instanceof Ghost) {
                Position newPos = shortestPathStrategy.getPrev(element.getPos());
                this.addCommand(new UpdateEnemyPosCommand(this.arenaModel, element,element.getPos(),newPos));
            }
        }
    }

    public void setupExecution()
    {
        ShortestPathStrategy<Position> shortestPathStrategy = calcPath();
        setupCommands(shortestPathStrategy);
    }

    @Override
    public void execute()
    {
        setupExecution();
        super.execute();
    }
}
