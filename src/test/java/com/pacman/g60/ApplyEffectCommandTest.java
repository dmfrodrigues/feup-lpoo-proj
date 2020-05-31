package com.pacman.g60;

import com.pacman.g60.Controller.ApplyEffectCommand;
import com.pacman.g60.Model.DamageEffect;
import com.pacman.g60.Model.Elements.Hero;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class ApplyEffectCommandTest {
    private DamageEffect effect;
    private Hero hero;

    @Before
    public void setup()
    {
        effect = Mockito.mock(DamageEffect.class);
        hero   = Mockito.mock(Hero.class);
        Mockito.doCallRealMethod().when(hero).getHealth();
        Mockito.doCallRealMethod().when(hero).setHealth(Mockito.anyInt());
    }

    @Test
    public void test1()
    {
        hero.setHealth(10);

        Mockito.doAnswer(invocation -> {
            hero.setHealth(5);
            return null;
        }).when(effect).apply(hero);

        ApplyEffectCommand command = new ApplyEffectCommand(effect,hero);
        command.execute();
        assertEquals(Integer.valueOf(5),hero.getHealth());
    }

}
