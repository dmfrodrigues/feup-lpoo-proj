package com.pacman.g60.Model.Path_Calculation;

import com.pacman.g60.Model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class Graph<T> {
    public abstract void addNode(T node) throws IllegalArgumentException;
    public abstract void addEdge(T source, T dest) throws IllegalArgumentException;
    public abstract Set<T> getAdj(T node);
    public abstract Set<T> getNodes();
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        Graph<T> G = (Graph<T>)obj;
        if(!getNodes().equals(G.getNodes())) return false;
        for(final T u: getNodes()){
            if(!getAdj(u).equals(G.getAdj(u))) return false;
        }
        return true;
    }
}
