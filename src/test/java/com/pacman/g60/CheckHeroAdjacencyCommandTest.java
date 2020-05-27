package com.pacman.g60;

import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.Effect.DamageEffect;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Ghost;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Elements.Ogre;
import com.pacman.g60.Model.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

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
        hero.setHealth(10);

        DamageEffect effect1 = Mockito.mock(DamageEffect.class);
        DamageEffect effect2 = Mockito.mock(DamageEffect.class);

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                hero.s
                return null;
            }
        }).when(effect1).apply(hero);
    }
}
