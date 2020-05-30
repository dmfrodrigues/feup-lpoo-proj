package com.pacman.g60;

import com.pacman.g60.Controller.CheckForDeathCommand;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Ghost;
import com.pacman.g60.Model.Elements.Hero;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CheckForDeathCommandTest {
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
        Mockito.when(hero.isAlive()).thenReturn(true);

        Ghost ghost = Mockito.mock(Ghost.class);
        Mockito.when(ghost.isAlive()).thenReturn(false);

        ArrayList<Element> elems = new ArrayList<>();
        elems.add(hero);
        elems.add(ghost);

        Mockito.when(arenaModel.getElements()).thenReturn(elems);
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object obj = invocation.getArgument(0);
                elems.remove(obj);
                return null;
            }
        }).when(arenaModel).removeElement(Mockito.any(Element.class));

        ArrayList<Element> expected = new ArrayList<>();
        expected.add(hero);

        CheckForDeathCommand command = new CheckForDeathCommand(arenaModel);
        command.execute();

        assertEquals(expected,arenaModel.getElements());
    }
}
