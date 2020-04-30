package com.pacman.g60.Model.Path_Calculation;

import java.util.*;

public class BFSShortestPathStrategy<T> implements ShortestPathStrategy<T> {
    public interface TieBreaker<T> {
        boolean untie(T u, T v);
    }
    private class DefaultTieBreaker implements TieBreaker<T>{
        @Override
        public boolean untie(T u, T v) {
            return true;
        }
    }
    
    private Graph<T> G;
    private Map<T,T> prev;
    private TieBreaker<T> tieBreaker;
    
    public BFSShortestPathStrategy(TieBreaker<T> tieBreaker){ this.tieBreaker = tieBreaker; }
    public BFSShortestPathStrategy(){ this.tieBreaker = new DefaultTieBreaker(); }

    @Override
    public void setGraph(Graph G) {
        this.G = G;
    }

    @Override
    public void calcPaths(T source) {
        prev = new HashMap<>();
        Map<T,Integer> dist = new HashMap<>();

        List<T> nodes = G.getNodes();

        for (final T u : nodes)
        {
            prev.put(u,null);
            dist.put(u,Integer.MAX_VALUE);
        }

        Queue<T> queue = new LinkedList<>();
        if(nodes.contains(source)) {
            dist.put(source, 0);
            prev.put(source, source);
            queue.add(source);
        }

        while (!queue.isEmpty())
        {
            T u = queue.poll();
            List<T> adj = G.getAdj(u);
            for (T v : adj)
            {
                if (dist.get(v) == Integer.MAX_VALUE || (dist.get(v) == dist.get(u)+1 && tieBreaker.untie(u, prev.get(v))))
                {
                    dist.put(v,dist.get(u) + 1);
                    prev.put(v,u);
                    queue.add(v);
                }
            }
        }
    }

    @Override
    public T getPrev(T u) {
        return prev.get(u);
    }
}
