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

    public void setup()
    {
        Graph<Position> G = arenaModel.getGraph();
        ShortestPathStrategy<Position> shortestPathStrategy = new BFSShortestPathStrategy<Position>(new BFSTieBreakerDiag(arenaModel.getHero().getPos()));
        shortestPathStrategy.setGraph(G);
        shortestPathStrategy.calcPaths(arenaModel.getHero().getPos());
        List<Element> elements = arenaModel.getElements();
        for (final Element element : elements) {
            if (element instanceof Ghost) {
                Position newPos = shortestPathStrategy.getPrev(element.getPos());
                this.addCommand(new UpdateEnemyPosCommand(this.arenaModel, element,element.getPos(),newPos));
            }
        }
    }

    @Override
    public void execute()
    {
        setup();
        super.execute();
    }
}
