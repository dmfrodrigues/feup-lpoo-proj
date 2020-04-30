package com.pacman.g60;

import com.pacman.g60.Model.Path_Calculation.AdjacencyGraph;
import com.pacman.g60.Model.Path_Calculation.Graph;
import com.pacman.g60.Model.Position;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class AdjacencyGraphTest {
    
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
}
