package com.pacman.g60;

import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Ghost;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
/*
public class UpdateAllEnemyPosCommandTest {
    private ArenaModel arenaModel;

    @Before
    public void setup()
    {
        this.arenaModel = Mockito.mock(ArenaModel.class);
    }

    @Test
    public void test1()
    {
        ArrayList<Element> elems = new ArrayList<>();

        Ghost ghost = Mockito.mock(Ghost.class);
        Mockito.when(ghost.getPos()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(ghost).setPos(Mockito.any(Position.class));
        ghost.setPos(new Position(7,5));

        Hero hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getPos()).thenReturn(new Position(5,5));

        Mockito.when(arenaModel.getElements()).thenReturn(elems);
        Mockito.when(arenaModel.getHero()).thenReturn(hero);


    }
}
*/