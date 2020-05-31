package com.pacman.g60;

import com.pacman.g60.Model.Elements.Coin;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CoinTest {
    @Test
    public void beCollected()
    {
        ArenaModel arenaModel = Mockito.mock(ArenaModel.class);
        Hero hero = Mockito.mock(Hero.class);

        Mockito.when(arenaModel.getHero()).thenReturn(hero);

        Mockito.doCallRealMethod().when(hero).setCoins(Mockito.anyInt());
        Mockito.doCallRealMethod().when(hero).incCoins();
        Mockito.when(hero.getCoins()).thenCallRealMethod();

        hero.setCoins(0);

        Coin coin = new Coin(new Position(1,1));

        assertTrue(coin.beCollected(arenaModel));
        assertEquals(Integer.valueOf(1),hero.getCoins());
    }
}
