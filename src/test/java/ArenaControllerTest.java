import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class ArenaControllerTest {
    private ArenaModel arenaModel;
    private ArenaView arenaView;
    private ArenaController arenaController;

    @Before
    public void setup()
    {
        arenaModel = Mockito.mock(ArenaModel.class);
        arenaView = Mockito.mock(ArenaView.class);
        arenaController = new ArenaController(arenaModel,arenaView);
        Effect effect = Mockito.mock(Effect.class);

        ArrayList<Element> elements = new ArrayList<Element>();

        for (int i = 0; i < 5; i++)
        {
            for (int i2 = 0; i2 < 5; i2++)
            {
                if (i == 0 || i == 4 || i2 == 0 || i2 == 4) elements.add(new Wall(new Position(i,i2)));
                if (i == 2 && i2 == 2) elements.add(new Wall(new Position(i,i2)));
                if (i == 1 && i2 == 3) elements.add(new Wall(new Position(i,i2)));
                if (i == 1 && i2 == 1) elements.add(new RangedEnemy(new Position(i,i2),effect)); //it's not Ranged for any particular reason, Enemy is abstract so a subclass had to be used
                if (i == 3 && i2 == 3) elements.add(new Hero(new Position(i,i2)));
            }
        }
        Mockito.when(arenaModel.getElements()).thenReturn(elements);
    }

    @Test
    public void generateMatrix()
    {
        Node[][] expected = new Node[5][5];
        for (int i = 0; i < 5; i++)
        {
            for (int i2 = 0; i2 < 5; i2++)
            {
                if (i == 0 || i == 4 || i2 == 0 || i2 == 4) expected[i][i2] = new Node(Node.CellType.OBSTACLE,null,Integer.MAX_VALUE,new Position(i,i2));
                if (i == 2 || i2 == 2) expected[i][i2] = new Node(Node.CellType.OBSTACLE,null,Integer.MAX_VALUE,new Position(i,i2));
                if (i == 1 || i2 == 3) expected[i][i2] = new Node(Node.CellType.OBSTACLE,null,Integer.MAX_VALUE,new Position(i,i2));

            }
        }
    }
}
