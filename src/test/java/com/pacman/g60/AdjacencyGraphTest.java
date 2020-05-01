package com.pacman.g60;

import com.pacman.g60.Model.Path_Calculation.AdjacencyGraph;
import com.pacman.g60.Model.Path_Calculation.Graph;
import com.pacman.g60.Model.Position;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class AdjacencyGraphTest {

    @Test
    public void add_node_exception(){
        Graph<Integer> G = new AdjacencyGraph<>();
        G.addNode(0);
        try{
            G.addNode(0);
            fail();
        } catch(Exception e){
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Node 0 already exists", e.getMessage());
        }
    }

    @Test
    public void add_edge_exception(){
        Graph<Integer> G = new AdjacencyGraph<>();
        G.addNode(0); G.addNode(1); G.addNode(2); G.addNode(3); G.addNode(4); G.addNode(5);
        try{
            G.addEdge(-1, 0);
            fail();
        } catch(Exception e){
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Node -1 does not exist", e.getMessage());
        }
        try{
            G.addEdge(-1, -2);
            fail();
        } catch(Exception e){
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Node -1 does not exist", e.getMessage());
        }
        try{
            G.addEdge(0, 6);
            fail();
        } catch(Exception e){
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Node 6 does not exist", e.getMessage());
        }
    }
    
    @Test
    public void addNodes_addEdges(){
        Position p1 = new Position(1, 1);
        Position p2 = new Position(1, 2);
        Position p3 = new Position(1, 1);
        Graph<Position> G = new AdjacencyGraph<>();
        G.addNode(p1);
        G.addNode(p2);
        G.addEdge(p1,p2);
        G.addEdge(p3,p2);
    }
    
    @Test
    public void adj(){
        Graph<Integer> G = new AdjacencyGraph<>();
        G.addNode(0); G.addNode(1); G.addNode(2); G.addNode(3); G.addNode(4); G.addNode(5);
        G.addEdge(0, 1); G.addEdge(1, 4);
        G.addEdge(0, 2); G.addEdge(3, 2);
        G.addEdge(4, 3); G.addEdge(1, 2);
        G.addEdge(3, 1); G.addEdge(1, 3);
        assertEquals(G.getAdj(0), new HashSet<>(Arrays.asList(1, 2)));
        assertEquals(G.getAdj(1), new HashSet<>(Arrays.asList(0, 2, 3, 4)));
        assertEquals(G.getAdj(2), new HashSet<>(Arrays.asList(0, 1, 3)));
        assertEquals(G.getAdj(3), new HashSet<>(Arrays.asList(1, 2, 4)));
        assertEquals(G.getAdj(4), new HashSet<>(Arrays.asList(3, 1)));
    }
    
    @Test
    public void test_equals(){
        Graph<Integer> G = new AdjacencyGraph<>();
        G.addNode(0); G.addNode(1); G.addNode(2); G.addNode(3); G.addNode(4); G.addNode(5);
        G.addEdge(0, 1); G.addEdge(1, 4);
        G.addEdge(0, 2); G.addEdge(3, 2);
        G.addEdge(4, 3); G.addEdge(1, 2);
        G.addEdge(3, 1); G.addEdge(1, 3);
        assertFalse(G.equals(null));
        assertFalse(G.equals(new Object()));

        Graph<Integer> G2 = new AdjacencyGraph<>();
        G2.addNode(0); G2.addNode(1); G2.addNode(2); G2.addNode(4); G2.addNode(5);
        assertFalse(G.equals(G2));

        Graph<Integer> G3 = new AdjacencyGraph<>();
        G3.addNode(0); G3.addNode(1); G3.addNode(2); G3.addNode(3); G3.addNode(4); G3.addNode(5);
        G3.addEdge(0, 1); G3.addEdge(1, 4);
        G3.addEdge(0, 2); G3.addEdge(3, 2);
        G3.addEdge(4, 3); G3.addEdge(1, 2);
        G3.addEdge(3, 1);
        assertEquals(G, G3);

        Graph<Integer> G4 = new AdjacencyGraph<>();
        G4.addNode(0); G4.addNode(1); G4.addNode(2); G4.addNode(3); G4.addNode(4); G4.addNode(5);
        G4.addEdge(0, 1); G4.addEdge(1, 4);
        G4.addEdge(0, 2); G4.addEdge(3, 2);
        G4.addEdge(4, 3); G4.addEdge(1, 2);
        assertNotEquals(G, G4);
    }
}
