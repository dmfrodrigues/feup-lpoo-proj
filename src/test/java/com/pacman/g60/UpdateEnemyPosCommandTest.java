package com.pacman.g60;

import com.pacman.g60.Controller.UpdateEnemyPosCommand;
import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.Elements.DynamicElement;
import com.pacman.g60.Model.Elements.Ghost;
import com.pacman.g60.Model.Position;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class UpdateEnemyPosCommandTest {
    @Test
    public void execute()
    {
        Position oldPos = new Position(5,5);
        Position newPos = new Position(6,5);
        DynamicElement dynamicElement = new Ghost(new Position(10,10));
        ArenaModel arenaModel = new ArenaModel(10,10);
        arenaModel.addElement(new Ghost(oldPos));


        UpdateEnemyPosCommand command = new UpdateEnemyPosCommand(arenaModel,dynamicElement,oldPos,newPos);
        command.execute();
        assertEquals(dynamicElement.getPos(),newPos);
    }
}
