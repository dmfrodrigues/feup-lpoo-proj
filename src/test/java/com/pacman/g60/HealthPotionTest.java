package com.pacman.g60;

import com.pacman.g60.Model.Elements.HealthPotion;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class HealthPotionTest {
    private Position position;

    @Before
    public void setup()
    {
        this.position = new Position(1,1);
    }

    @Test
    public void beCollected()
    {
        Hero hero = Mockito.mock(Hero.class);
        ArenaModel arenaModel = Mockito.mock(ArenaModel.class);
        Mockito.when(arenaModel.getHero()).thenReturn(hero);
        Mockito.doCallRealMethod().when(hero).updateHealth(Mockito.anyInt());
        Mockito.doCallRealMethod().when(hero).setHealth(Mockito.anyInt());
        Mockito.when(hero.getHealth()).thenCallRealMethod();

        hero.setHealth(1);

        HealthPotion potion = new HealthPotion(position);
        potion.beCollected(arenaModel);
        assertEquals(Integer.valueOf(6),hero.getHealth());
    }
}
