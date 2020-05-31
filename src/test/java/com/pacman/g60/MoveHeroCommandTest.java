package com.pacman.g60;

import com.pacman.g60.Controller.*;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MoveHeroCommandTest {
    private ArenaModel arenaModel;

    @Before
    public void setup()
    {
        Position initialPos = new Position(5, 5);
        Hero hero = new Hero(initialPos);
        this.arenaModel = new ArenaModel(10,10);
        this.arenaModel.addElement(hero);
    }

    @Test
    public void moveUp()
    {
        Command command = new MoveHeroUpCommand(this.arenaModel);
        command.execute();
        Position expected = new Position(5,4);
        assertEquals(expected,this.arenaModel.getHero().getPos());
    }

    @Test
    public void moveDown()
    {
        Command command = new MoveHeroDownCommand(this.arenaModel);
        command.execute();
        Position expected = new Position(5,6);
        assertEquals(expected,this.arenaModel.getHero().getPos());
    }

    @Test
    public void moveLeft()
    {
        Command command = new MoveHeroLeftCommand(this.arenaModel);
        command.execute();
        Position expected = new Position(4,5);
        assertEquals(expected,this.arenaModel.getHero().getPos());
    }

    @Test
    public void moveRight()
    {
        Command command = new MoveHeroRightCommand(this.arenaModel);
        command.execute();
        Position expected = new Position(6,5);
        assertEquals(expected,this.arenaModel.getHero().getPos());
    }

}
