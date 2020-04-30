package com.pacman.g60.Model.Path_Calculation;

import java.util.*;

public class AdjacencyGraph<T> extends Graph<T> {
    Map<T,Set<T>> adj;

    public AdjacencyGraph()
    {
        this.adj = new HashMap<>();
    }

    @Override
    public void addNode(T node) throws IllegalArgumentException {
        if(adj.get(node) != null) throw new IllegalArgumentException("Node already exists");
        adj.put(node,new HashSet<>());
    }

    @Override
    public void addEdge(T source, T dest) throws IllegalArgumentException {
        if  (!adj.containsKey(source)) throw new IllegalArgumentException("Node " + source.toString() + " does not exist.\n");
        if  (!adj.containsKey(dest)) throw new IllegalArgumentException("Node " + dest.toString() + " does not exist.\n");
        adj.get(source).add(dest);
        adj.get(dest).add(source);
    }

    @Override
    public Set<T> getAdj(T node) {
        return adj.get(node);
    }

    @Override
    public Set<T> getNodes() { return adj.keySet(); }
}
