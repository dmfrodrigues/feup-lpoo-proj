package com.pacman.g60;

import com.pacman.g60.Model.DamageEffect;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Elements.Knife;
import com.pacman.g60.Model.Elements.Sword;
import com.pacman.g60.Model.Elements.Weapon;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WeaponTest {
    private Hero hero;
    private ArenaModel arenaModel;

    @Before
    public void setup()
    {
        arenaModel = Mockito.mock(ArenaModel.class);
        hero = Mockito.mock(Hero.class);
        Mockito.when(arenaModel.getHero()).thenReturn(hero);
        Mockito.doCallRealMethod().when(hero).setWeapon(Mockito.any(Weapon.class));
        Mockito.when(hero.getWeapon()).thenCallRealMethod();
    }

    @Test
    public void knife()
    {
        Weapon weapon = new Knife();
        assertEquals(new DamageEffect(1),weapon.getEffect());

        weapon.beCollected(arenaModel);
        assertTrue(weapon.beCollected(arenaModel));
        assertEquals(weapon,hero.getWeapon());
    }

    @Test
    public void sword()
    {
        Weapon weapon = new Sword(new Position(1,1));
        assertEquals(new DamageEffect(2),weapon.getEffect());

        weapon.beCollected(arenaModel);
        assertTrue(weapon.beCollected(arenaModel));
        assertEquals(weapon,hero.getWeapon());
    }

}
