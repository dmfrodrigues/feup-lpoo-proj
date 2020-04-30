package com.pacman.g60;

import com.pacman.g60.Controller.UpdateEnemyPosCommand;
import com.pacman.g60.Model.Elements.DynamicElement;
import com.pacman.g60.Model.Elements.Ghost;
import com.pacman.g60.Model.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UpdateEnemyPosCommandTest {
    @Test
    public void execute()
    {
        Position position = new Position(5,5);
        DynamicElement dynamicElement = new Ghost(new Position(10,10));
        UpdateEnemyPosCommand command = new UpdateEnemyPosCommand(dynamicElement,position);
        command.execute();
        assertEquals(dynamicElement.getPos(),position);
    }
}
