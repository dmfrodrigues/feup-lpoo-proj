package com.pacman.g60.Model;

import com.pacman.g60.Model.Elements.Coin;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Elements.Hierarchy.CanSharePosition;
import com.pacman.g60.Model.Elements.Wall;
import com.pacman.g60.Model.Path_Calculation.AdjacencyGraph;
import com.pacman.g60.Model.Path_Calculation.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArenaModel {
    private int W, H;
    private Integer numCoins;
    private Hero hero = null;
    private ArrayList<Element> listElements;
    private Map<Position,Element> availablePositions;

    public ArenaModel(int W, int H){
        this.W = W;
        this.H = H;
        listElements = new ArrayList<>();
        this.numCoins = 0;
        this.availablePositions = new HashMap<>();
    }

    public void addElement(Element element){
        listElements.add(element);
        if(element instanceof Hero) hero = (Hero)element;
        else if (element instanceof Coin) numCoins++;
        this.availablePositions.put(element.getPos(),element);
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

    public boolean isPositionAvailable(Position position)
    {
        Element element = this.availablePositions.get(position);
        boolean elementCanSharePosition = (element instanceof CanSharePosition);
        boolean noElementOnPosition = (element == null);
        if (elementCanSharePosition || noElementOnPosition) return true;
        else return false;
    }

    public void updateMapKey(Position oldKey, Position newKey)
    {
        Element element = this.availablePositions.get(oldKey);
        this.availablePositions.remove(oldKey);
        this.availablePositions.put(newKey,element);
    }

    public Element getElemFromPos(Position position)
    {
        return availablePositions.get(position);
    }

    public void removeElement(Element element)
    {
        if (element instanceof Coin) numCoins--;
        listElements.remove(element);
        availablePositions.remove(element.getPos());
    }

    public interface Loader {
        public ArenaModel getArenaModel();
    }
}
