package com.pacman.g60.Model;

import com.pacman.g60.Model.Elements.DynamicElement;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.StaticElement;
import com.pacman.g60.Model.Path_Calculation.AdjacencyGraph;
import com.pacman.g60.Model.Path_Calculation.Graph;

import java.util.ArrayList;
import java.util.List;

public class ArenaModel {
    private int W, H;
    private Hero hero = null;
    private ArrayList<Element> listElements;

    public ArenaModel(int W, int H){
        this.W = W;
        this.H = H;
        listElements = new ArrayList<>();
    }

    public void addElement(Element element){
        listElements.add(element);
        if(element instanceof Hero) hero = (Hero)element;
    }

    public ArrayList<Element> getElements(){
        return listElements;
    }

    public Graph<Position> getGraph()
    {
        boolean[][] obstacle = new boolean[W][H];
        for (final Element e : listElements)
        {
            if (e instanceof Wall) obstacle[e.getPos().getX()][e.getPos().getY()] = true;
        }

        Graph<Position> G = new AdjacencyGraph<>();
        for (int x = 0; x < W; ++x)
        {
            for (int y = 0; y < H; ++y)
            {
                if (!obstacle[x][y]) G.addNode(new Position(x,y));
            }
        }
        for (int x = 0; x < W; ++x)
        {
            for (int y = 0; y < H; ++y)
            {
                if (obstacle[x][y]) continue;
                if (x > 0 && !obstacle[x-1][y]) G.addEdge(new Position(x,y),new Position(x-1,y));
                if (y > 0 && !obstacle[x][y-1]) G.addEdge(new Position(x,y),new Position(x,y-1));
                if (x < W-1 && !obstacle[x+1][y]) G.addEdge(new Position(x,y),new Position(x+1,y));
                if (y < H-1 && !obstacle[x][y+1]) G.addEdge(new Position(x,y),new Position(x,y+1));
            }
        }
        return G;
    }

    public int getW() {
        return W;
    }

    public int getH() {
        return H;
    }

    public Hero getHero() { return hero; }
    
    public interface Loader {
        public ArenaModel getArenaModel();
    }
}
