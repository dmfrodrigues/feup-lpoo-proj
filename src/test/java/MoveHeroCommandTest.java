import com.pacman.g60.Application;
import com.pacman.g60.Controller.Command;
import com.pacman.g60.Controller.MoveHeroCommand;
import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class MoveHeroCommandTest {
    private ArenaModel arenaModel;

    @Before
    public void setup()
    {
        Position initialPos = new Position(5, 5);
        this.arenaModel = Mockito.mock(ArenaModel.class);
        Hero hero = new Hero(initialPos);
        Mockito.when(arenaModel.getHero()).thenReturn(hero);
    }

    @Test
    public void moveUp()
    {
        Command command = new MoveHeroCommand(this.arenaModel, Application.Direction.UP);
        command.execute();
        Position expected = new Position(5,4);
        assertEquals(expected,this.arenaModel.getHero().getPos());
    }

    @Test
    public void moveDown()
    {
        Command command = new MoveHeroCommand(this.arenaModel, Application.Direction.DOWN);
        command.execute();
        Position expected = new Position(5,6);
        assertEquals(expected,this.arenaModel.getHero().getPos());
    }

    @Test
    public void moveLeft()
    {
        Command command = new MoveHeroCommand(this.arenaModel, Application.Direction.LEFT);
        command.execute();
        Position expected = new Position(4,5);
        assertEquals(expected,this.arenaModel.getHero().getPos());
    }

    @Test
    public void moveRight()
    {
        Command command = new MoveHeroCommand(this.arenaModel, Application.Direction.RIGHT);
        command.execute();
        Position expected = new Position(6,5);
        assertEquals(expected,this.arenaModel.getHero().getPos());
    }

}
