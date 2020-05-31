package com.pacman.g60;

import com.pacman.g60.Model.Effect.DamageEffect;
import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.Enemy;
import com.pacman.g60.Model.Elements.Mummy;
import com.pacman.g60.Model.Elements.Ogre;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class EnemyTest {

    @Test
    public void ogre()
    {
        Enemy enemy = new Ogre(new Position(1,1));
        assertEquals(Integer.valueOf(8),enemy.getHealth());

        enemy.setHealth(10);
        assertEquals(Integer.valueOf(10),enemy.getHealth());

        assertEquals(new DamageEffect(2),enemy.getEffect());

        enemy.updateHealth(-2);
        assertEquals(Integer.valueOf(8),enemy.getHealth());

        assertTrue(enemy.isAlive());
        assertFalse(enemy.beCollected(Mockito.mock(ArenaModel.class)));
    }

    @Test
    public void mummy()
    {
        Enemy enemy = new Mummy(new Position(1,1));
        assertEquals(Integer.valueOf(8),enemy.getHealth());

        enemy.setHealth(10);
        assertEquals(Integer.valueOf(10),enemy.getHealth());

        assertEquals(new DamageEffect(1),enemy.getEffect());

        enemy.updateHealth(-2);
        assertEquals(Integer.valueOf(8),enemy.getHealth());

        assertTrue(enemy.isAlive());
        assertFalse(enemy.beCollected(Mockito.mock(ArenaModel.class)));
    }
}
