package com.pacman.g60;

import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.ArenaModelLoaderStream;
import com.pacman.g60.Model.Path_Calculation.*;
import com.pacman.g60.Model.Position;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class BFSShortestPathStrategyTest {
    
    @Test
    public void default_tiebreaker(){
        Graph<Integer> G = new AdjacencyGraph<>();
        G.addNode(0); G.addNode(1); G.addNode(2); G.addNode(3); G.addNode(4); G.addNode(5);
        G.addEdge(0, 1); G.addEdge(1, 4);
        G.addEdge(0, 2); G.addEdge(3, 2);
        G.addEdge(4, 3); G.addEdge(1, 2);
        G.addEdge(3, 1); G.addEdge(1, 3);
        ShortestPathStrategy<Integer> shortestPath = new BFSShortestPathStrategy<>();
        shortestPath.setGraph(G);
        shortestPath.calcPaths(0);
        assertEquals(0, (int) shortestPath.getPrev(0));
        assertEquals(0, (int) shortestPath.getPrev(1));
        assertEquals(0, (int) shortestPath.getPrev(2));
        assertTrue(shortestPath.getPrev(3) == 1 || shortestPath.getPrev(3) == 2);
        assertEquals(1, (int) shortestPath.getPrev(4));
    }

    @Test
    public void default_tiebreaker2(){
        Graph<Integer> G = new AdjacencyGraph<>();
        G.addNode(0); G.addNode(1); G.addNode(2); G.addNode(3); G.addNode(4); G.addNode(5);
        G.addEdge(0, 1); G.addEdge(1, 4);
        G.addEdge(0, 2); G.addEdge(3, 2);
        G.addEdge(4, 3); G.addEdge(1, 2);
        G.addEdge(3, 1); G.addEdge(1, 3);
        ShortestPathStrategy<Integer> shortestPath = new BFSShortestPathStrategy<>();
        shortestPath.setGraph(G);
        shortestPath.calcPaths(null);
        assertEquals(null, shortestPath.getPrev(0));
        assertEquals(null, shortestPath.getPrev(1));
        assertEquals(null, shortestPath.getPrev(2));
        assertEquals(null, shortestPath.getPrev(3));
        assertEquals(null, shortestPath.getPrev(4));
        assertNotEquals((Integer)1, shortestPath.getPrev(1));
    }

    @Test
    public void diag_tiebreaker(){
        String string = "10 10\n"+
                "WWWWWWWWWW\n"+
                "W        W\n"+
                "W        W\n"+
                "W        W\n"+
                "W        W\n"+
                "W        W\n"+
                "W        W\n"+
                "W        W\n"+
                "W        W\n"+
                "WWWWWWWWWW\n";
        InputStream inputStream = new ByteArrayInputStream(string.getBytes());
        ArenaModel.Loader arenaModelLoader = new ArenaModelLoaderStream(inputStream);
        ArenaModel arenaModel = arenaModelLoader.getArenaModel();
        Graph<Position> G = arenaModel.getGraph();
        
        Position source = new Position(5, 5);
        ShortestPathStrategy<Position> shortestPath = new BFSShortestPathStrategy<>(new BFSTieBreakerDiag(source));
        shortestPath.setGraph(G);
        shortestPath.calcPaths(source);
        assertEquals(new Position(4, 4), shortestPath.getPrev(new Position(3, 4)));
        assertEquals(new Position(6, 4), shortestPath.getPrev(new Position(7, 4)));
        assertEquals(new Position(4, 6), shortestPath.getPrev(new Position(3, 6)));
        assertEquals(new Position(6, 6), shortestPath.getPrev(new Position(7, 6)));
    }
}
