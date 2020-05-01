package com.pacman.g60.Model;

import com.pacman.g60.Model.Elements.Coin;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Elements.Wall;
import com.pacman.g60.Model.Path_Calculation.AdjacencyGraph;
import com.pacman.g60.Model.Path_Calculation.Graph;

import java.util.ArrayList;

public class ArenaModel {
    private int W, H;
    private Integer numCoins;
    private Hero hero = null;
    private ArrayList<Element> listElements;

    public ArenaModel(int W, int H){
        this.W = W;
        this.H = H;
        listElements = new ArrayList<>();
        this.numCoins = 0;
    }

    public void addElement(Element element){
        listElements.add(element);
        if(element instanceof Hero) hero = (Hero)element;
        else if (element instanceof Coin) numCoins++;
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
                if (x < W-1 && !obstacle[x+1][y]) G.addEdge(new Position(x,y),new Position(x+1,y));
                if (y < H-1 && !obstacle[x][y+1]) G.addEdge(new Position(x,y),new Position(x,y+1));
            }
        }
        return G;
    }

    public Integer getNumCoins() {return numCoins;}

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
