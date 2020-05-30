package com.pacman.g60;

import com.pacman.g60.Controller.Command;
import com.pacman.g60.Controller.CompositeCommand;
import com.pacman.g60.Controller.UpdateAllEnemyPosCommand;
import com.pacman.g60.Model.Models.ArenaModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CompositeCommandTest {
    private List<Command> commandList;

    @Before
    public void setup()
    {
        this.commandList = new ArrayList<>();
    }

    @Test
    public void addCommand()
    {
        Command command = Mockito.mock(Command.class);

        CompositeCommand compositeCommand = new UpdateAllEnemyPosCommand(Mockito.mock(ArenaModel.class));

        commandList.add(command);

        compositeCommand.addCommand(command);

        assertEquals(commandList,compositeCommand.getCommandList());
    }

    @Test
    public void execute()
    {
        CompositeCommand compositeCommand = Mockito.mock(CompositeCommand.class);
        Mockito.doCallRealMethod().when(compositeCommand).execute();
        Mockito.doCallRealMethod().when(compositeCommand).setCommandList(Mockito.any(List.class));

        Command command = Mockito.mock(Command.class);

        commandList.add(command);

        compositeCommand.setCommandList(commandList);


        compositeCommand.execute();

        verify(command,times(1)).execute();
    }
}
