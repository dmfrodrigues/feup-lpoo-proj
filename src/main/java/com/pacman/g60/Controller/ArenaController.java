package com.pacman.g60.Controller;


import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Ghost;
import com.pacman.g60.Model.Path_Calculation.BFSShortestPathStrategy;
import com.pacman.g60.Model.Path_Calculation.Graph;
import com.pacman.g60.Model.Path_Calculation.ShortestPathStrategy;
import com.pacman.g60.Model.Position;
import com.pacman.g60.View.ArenaView;

import java.io.IOException;
import java.util.List;


public class ArenaController {
    ArenaModel arenaModel;
    ArenaView arenaView;


    public ArenaController(ArenaModel arenaModel, ArenaView arenaView){
        this.arenaModel = arenaModel;
        this.arenaView = arenaView;
    }
    public void run() throws IOException {
        boolean good = true;
        int i = 0;
        while(good){
            
            try {
                Thread.sleep(10);
            } catch(InterruptedException e){
                
            }
            
            if(i%10 == 0) {
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
                i = 1;
            }
            ++i;
            
            ArenaView.COMMAND cmd = arenaView.pollCommand();
            if(!(cmd == null)) {
                switch (cmd) {
                    case EXIT:
                        good = false;
                        break;
                    case UP:
                        arenaModel.getHero().moveUp(); break;
                    case DOWN:
                        arenaModel.getHero().moveDown(); break;
                    case LEFT:
                        arenaModel.getHero().moveLeft(); break;
                    case RIGHT:
                        arenaModel.getHero().moveRight(); break;
                }
            }
            arenaView.draw(arenaModel);
        }
    }







}