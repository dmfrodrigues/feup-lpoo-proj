package com.pacman.g60;

import com.pacman.g60.Controller.CheckHeroAdjacencyCommand;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Effect.DamageEffect;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Ghost;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Elements.Ogre;
import com.pacman.g60.Model.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CheckHeroAdjacencyCommandTest {
    private ArenaModel arenaModel;

    @Before
    public void setup()
    {
        arenaModel = Mockito.mock(ArenaModel.class);
    }

    @Test
    public void test1()
    {
        List<Element> elemsSixFive = new ArrayList<>();
        List<Element> elemsSixSeven = new ArrayList<>();
        Ghost ghost = Mockito.mock(Ghost.class);
        Ogre ogre = Mockito.mock(Ogre.class);
        elemsSixFive.add(ghost);
        elemsSixSeven.add(ogre);

        Mockito.when(arenaModel.getElemFromPos(new Position(6,5))).thenReturn(elemsSixFive);
        Mockito.when(arenaModel.getElemFromPos(new Position(6,7))).thenReturn(elemsSixSeven);

        Hero hero = Mockito.mock(Hero.class);
        Mockito.when(arenaModel.getHero()).thenReturn(hero);
        Mockito.when(hero.getPos()).thenReturn(new Position(6,6));
        Mockito.doCallRealMethod().when(hero).setHealth(Mockito.anyInt());
        Mockito.when(hero.getHealth()).thenCallRealMethod();
        hero.setHealth(10);

        DamageEffect effect1 = Mockito.mock(DamageEffect.class);
        DamageEffect effect2 = Mockito.mock(DamageEffect.class);

        Mockito.when(ghost.getEffect()).thenReturn(effect1);
        Mockito.when(ogre.getEffect()).thenReturn(effect2);

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                hero.setHealth(hero.getHealth() - 1);
                return null;
            }
        }).when(effect1).apply(hero);

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                hero.setHealth(hero.getHealth() - 2);
                return null;
            }
        }).when(effect2).apply(hero);

        CheckHeroAdjacencyCommand command = new CheckHeroAdjacencyCommand(arenaModel);
        command.execute();

        assertEquals(Integer.valueOf(7),hero.getHealth());
    }
}
