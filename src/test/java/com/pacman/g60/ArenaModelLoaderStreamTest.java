package com.pacman.g60;

import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.ArenaModelLoaderStream;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Ghost;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Position;
import com.pacman.g60.Model.Elements.Wall;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArenaModelLoaderStreamTest {

    @Test
    public void ctor(){
        String string = "10 10\n"+
                        "WWWWWWWWWW\n"+
                        "W W      W\n"+
                        "W W WWW  W\n"+
                        "W W   W  W\n"+
                        "W WWWWW  W\n"+
                        "WH W W   W\n"+
                        "W  W W WWW\n"+
                        "W  W W   W\n"+
                        "W  G   G W\n"+
                        "WWWWWWWWWW\n";
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
        List<Element> listElementsArena = arenaModel.getElements();

        assertEquals(listElementsArena.size(), listElements.size());
        for(final Element e: listElements){
            assertTrue(listElements.contains(e));
        }

    }
}
