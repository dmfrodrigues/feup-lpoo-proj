package com.pacman.g60;

import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.ArenaModelLoaderStream;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Ghost;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Path_Calculation.AdjacencyGraph;
import com.pacman.g60.Model.Path_Calculation.Graph;
import com.pacman.g60.Model.Position;
import com.pacman.g60.Model.Elements.Wall;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

import static org.junit.Assert.*;

public class ArenaModelLoaderStreamTest {

    @Test
    public void ctor(){
        String string = "11 10\n"+
                        "WWWWWWWWWW \n"+
                        "W W      W \n"+
                        "W W WWW  W \n"+
                        "W W   W  W \n"+
                        "W WWWWW  W \n"+
                        "WH W W   W \n"+
                        "W  W W WWW \n"+
                        "W  W W   W \n"+
                        "W  G   G W \n"+
                        "WWWWWWWWWW \n";
        Graph<Position> G = new AdjacencyGraph<>();
        List<Integer> nodePositions = new ArrayList<>(Arrays.asList(
                                                                            10, 0,
                      1, 1,       3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1,       10, 1,
                      1, 2,       3, 2,                   7, 2, 8, 2,       10, 2,
                      1, 3,       3, 3, 4, 3, 5, 3,       7, 3, 8, 3,       10, 3,
                      1, 4,                               7, 4, 8, 4,       10, 4,
                      1, 5, 2, 5,       4, 5,       6, 5, 7, 5, 8, 5,       10, 5,
                      1, 6, 2, 6,       4, 6,       6, 6,                   10, 6,
                      1, 7, 2, 7,       4, 7,       6, 7, 7, 7, 8, 7,       10, 7,
                      1, 8, 2, 8, 3, 8, 4, 8, 5, 8, 6, 8, 7, 8, 8, 8,       10, 8,
                                                                            10, 9
        ));
        Set<Position> posSet = new HashSet<>();
        for(int i = 0; i < nodePositions.size(); i += 2){
            posSet.add(new Position(nodePositions.get(i), nodePositions.get(i+1)));
            G.addNode(new Position(nodePositions.get(i), nodePositions.get(i+1)));
        }
        for(final Position pos: posSet){
            Position left  = new Position(pos.getX()-1, pos.getY());
            Position right = new Position(pos.getX()+1, pos.getY());
            Position up    = new Position(pos.getX(), pos.getY()-1);
            Position down  = new Position(pos.getX(), pos.getY()+1);
            if(posSet.contains(left )) G.addEdge(pos, left );
            if(posSet.contains(right)) G.addEdge(pos, right);
            if(posSet.contains(up   )) G.addEdge(pos, up   );
            if(posSet.contains(down )) G.addEdge(pos, down );
        }
        
        
        List<Integer> wallsPositions = new ArrayList<>(Arrays.asList(
                0, 0, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0, 7, 0, 8, 0, 9, 0,
                0, 1,       2, 1,                                     9, 1,
                0, 2,       2, 2,       4, 2, 5, 2, 6, 2,             9, 2,
                0, 3,       2, 3,                   6, 3,             9, 3,
                0, 4,       2, 4, 3, 4, 4, 4, 5, 4, 6, 4,             9, 4,
                0, 5,             3, 5,       5, 5,                   9, 5,
                0, 6,             3, 6,       5, 6,      7, 6, 8, 6,  9, 6,
                0, 7,             3, 7,       5, 7,                   9, 7,
                0, 8,                                                 9, 8,
                0, 9, 1, 9, 2, 9, 3, 9, 4, 9, 5, 9, 6, 9, 7, 9, 8, 9, 9, 9
        ));
        List<Integer> heroPositions = new ArrayList<>(Arrays.asList(1, 5));
        List<Integer> ghostPositions = new ArrayList<>(Arrays.asList(3, 8, 7, 8));

        List<Element> listElements = new ArrayList<>();
        for(int i = 0; i < wallsPositions.size(); i += 2){
            listElements.add(new Wall(new Position(wallsPositions.get(i), wallsPositions.get(i+1))));
        }
        for(int i = 0; i < heroPositions.size(); i += 2){
            listElements.add(new Hero(new Position(heroPositions.get(i), heroPositions.get(i+1))));
        }
        for(int i = 0; i < ghostPositions.size(); i += 2){
            listElements.add(new Ghost(new Position(ghostPositions.get(i), ghostPositions.get(i+1))));
        }

        InputStream inputStream = new ByteArrayInputStream(string.getBytes());
        ArenaModel.Loader arenaModelLoader = new ArenaModelLoaderStream(inputStream);
        ArenaModel arenaModel = arenaModelLoader.getArenaModel();
        assertEquals(arenaModel.getW(), 11);
        assertEquals(arenaModel.getH(), 10);
        assertEquals(arenaModel.getHero(), new Hero(new Position(heroPositions.get(0), heroPositions.get(1))));
        List<Element> listElementsArena = arenaModel.getElements();

        assertEquals(listElementsArena.size(), listElements.size());
        for(final Element e: listElements){
            assertTrue(listElements.contains(e));
        }
        
        Graph<Position> Gnew = arenaModel.getGraph();
        assertEquals(Gnew, G);
    }
    @Test
    public void ctor_exception(){
        String string = "10 10\n"+
                "WWWWWWWWWW\n"+
                "W W   A  W\n"+
                "W W WWW  W\n"+
                "W W   W  W\n"+
                "W WWWWW  W\n"+
                "WH W W   W\n"+
                "W  W W WWW\n"+
                "W  W W   W\n"+
                "W  G   G W\n"+
                "WWWWWWWWWW\n";
        InputStream inputStream = new ByteArrayInputStream(string.getBytes());
        try {
            ArenaModel.Loader arenaModelLoader = new ArenaModelLoaderStream(inputStream);
            fail();
        } catch(Exception e){
            System.out.println(e.getClass().toString());
            assertEquals(e.getClass().toString(), "class java.lang.IllegalArgumentException");
            assertEquals(e.getMessage(), "Unknown character 'A'");
        }
    }
}
