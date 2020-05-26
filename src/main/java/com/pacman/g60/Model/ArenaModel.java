package com.pacman.g60.Model;

import com.pacman.g60.Model.Elements.*;
import com.pacman.g60.Model.Elements.Hierarchy.CanSharePosition;
import com.pacman.g60.Model.Path_Calculation.AdjacencyGraph;
import com.pacman.g60.Model.Path_Calculation.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArenaModel {
    private int W, H;
    private Integer numCoins, numEnemies;
    private Hero hero = null;
    private boolean shouldGameContinue;
    private ArrayList<Element> listElements;
    private Map<Position, List<Element>> availablePositions;

    public ArenaModel(int W, int H){
        this.W = W;
        this.H = H;
        listElements = new ArrayList<>();
        this.numCoins = 0;
        this.numEnemies = 0;
        this.shouldGameContinue = true;
        this.availablePositions = new HashMap<>();
    }

    public ArenaModel(ArenaModel arenaModel) {
        this(arenaModel.W, arenaModel.H);
        for(Element e: arenaModel.listElements)
            addElement((Element)e.clone());
    }

    public void addElement(Element element){
        listElements.add(element);
        if(element instanceof Hero) hero = (Hero)element;
        else if (element instanceof Coin) numCoins++;
        else if (element instanceof Enemy) numEnemies++;

        List<Element> elemsInPos = this.availablePositions.get(element.getPos());
        if (elemsInPos == null)
        {
            List<Element> elems = new ArrayList<>();
            elems.add(element);
            this.availablePositions.put(element.getPos(),elems);
        }
        else
        {
            elemsInPos.add(element);
        }
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

    public boolean isPositionAvailable(Position position, Element elemWhichWantsToBeMoved)
    {
        List<Element> elemsInDesiredPos = this.availablePositions.get(position);
        boolean isMovementPossible = false;
        if (elemsInDesiredPos == null) return true;
        else
        {
            for (Element elem : elemsInDesiredPos)
            {
                if (elem instanceof Hero) return false;
                if (elem instanceof CanSharePosition && elemWhichWantsToBeMoved instanceof CanSharePosition) isMovementPossible = true;
            }
        }
        return isMovementPossible;
/*
        Element element = this.availablePositions.get(position);
        if (element instanceof Hero) return false;
        if (elemWhichWantsToBeMoved instanceof Hero && element != null) return false;
        boolean elementCanSharePosition = (element instanceof CanSharePosition);
        boolean noElementOnPosition = (element == null);
        if (elementCanSharePosition || noElementOnPosition) return true;
        else return false;
*/    }

    public void updateMapKey(Position oldKey, Position newKey, Element elemBeingMoved)
    {
        List<Element> elemsInOldPos = this.availablePositions.get(oldKey);
        elemsInOldPos.remove(elemBeingMoved);

        List<Element> elemsInNewPos = this.availablePositions.get(newKey);
        if (elemsInNewPos == null)
        {
            List<Element> newElemList = new ArrayList<>();
            newElemList.add(elemBeingMoved);
            this.availablePositions.put(newKey,newElemList);
        }
        else
        {
            elemsInNewPos.add(elemBeingMoved);
        }
    }

    public Element getElemFromPos(Position position)
    {
        return availablePositions.get(position);
    }

    public void removeElement(Element element)
    {
        if (element instanceof Coin) numCoins--;
        else if (element instanceof Enemy) numEnemies--;
        if (numEnemies == 0) shouldGameContinue = false;
        listElements.remove(element);
        availablePositions.remove(element.getPos());
    }

    public boolean getShouldGameContinue()
    {
        return this.shouldGameContinue;
    }

    public interface Loader {
        public ArenaModel getArenaModel();
    }
}
