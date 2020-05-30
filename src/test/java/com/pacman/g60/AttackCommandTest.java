package com.pacman.g60;

import com.pacman.g60.Controller.AttackCommand;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Elements.*;
import com.pacman.g60.Model.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AttackCommandTest {
    private ArenaModel arenaModel;

    @Before
    public void setup()
    {
        arenaModel = Mockito.mock(ArenaModel.class);
    }

    @Test
    public void test1()
    {
        Hero hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getPos()).thenReturn(new Position(5,5));

        Mockito.when(arenaModel.getHero()).thenReturn(hero);

        Ghost ghost = Mockito.mock(Ghost.class);
        Mockito.doCallRealMethod().when(ghost).setPos(Mockito.any(Position.class));
        Mockito.doCallRealMethod().when(ghost).setHealth(Mockito.anyInt());
        Mockito.doCallRealMethod().when(ghost).getHealth();
        ghost.setPos(new Position(6,5));
        ghost.setHealth(5);
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ghost.setHealth(4);
                return null;
            }
        }).when(ghost).updateHealth(Mockito.anyInt());

        Knife knife = new Knife();

        Mockito.doCallRealMethod().when(hero).setWeapon(knife);

        Mockito.when(hero.getWeapon()).thenReturn(knife);

        List<Element> elems = new ArrayList<>();
        elems.add(ghost);

        Mockito.when(arenaModel.getElemFromPos(new Position(6,5))).thenReturn(elems);

        AttackCommand command = new AttackCommand(arenaModel);
        command.execute();

        assertEquals(Integer.valueOf(4),ghost.getHealth());
    }
}
