package com.pacman.g60;

import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.Bullet;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BulletTest {
    private ArenaModel arenaModel;

    @Before
    public void setup()
    {
        this.arenaModel = Mockito.mock(ArenaModel.class);
    }

    @Test
    public void beCollected()
    {
        Hero hero = Mockito.mock(Hero.class);
        Mockito.when(arenaModel.getHero()).thenReturn(hero);

        Mockito.doCallRealMethod().when(hero).setAmmo(Mockito.anyInt());
        hero.setAmmo(0);

        Mockito.doCallRealMethod().when(hero).incAmmo();
        Mockito.when(hero.getAmmo()).thenCallRealMethod();

        Bullet bullet = new Bullet(new Position(1,1));
        assertTrue(bullet.beCollected(arenaModel));

        assertEquals(Integer.valueOf(1),hero.getAmmo());
    }
}
