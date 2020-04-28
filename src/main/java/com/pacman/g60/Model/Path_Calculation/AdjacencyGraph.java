package com.pacman.g60.Model.Path_Calculation;

import java.util.*;

public class AdjacencyGraph<T> implements Graph<T> {
    Map<T,List<T>> adj;

    public AdjacencyGraph()
    {
        this.adj = new HashMap<>();
    }

    @Override
    public void addNode(T node) {
        adj.put(node,new ArrayList<>());
    }

    @Override
    public void addEdge(T source, T dest) throws IllegalArgumentException {
        if  (!adj.containsKey(source)) throw new IllegalArgumentException("Node " + source.toString() + " does not exist.\n");
        if  (!adj.containsKey(dest)) throw new IllegalArgumentException("Node " + dest.toString() + " does not exist.\n");
        adj.get(source).add(dest);
        adj.get(dest).add(source);
    }

    @Override
    public List getAdj(T node) {
        return adj.get(node);
    }

    @Override
    public List<T> getNodes() {
        List<T> res = new ArrayList<>();

        Iterator it = adj.entrySet().iterator();

        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            res.add((T) pair.getKey());
        }
        return res;
    }
}
