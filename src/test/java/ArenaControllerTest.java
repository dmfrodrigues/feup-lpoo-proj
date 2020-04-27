import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;

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

        ArrayList<Element> elements = new ArrayList<>();

        for (int i = 0; i < 5; i++)
        {
            for (int i2 = 0; i2 < 5; i2++)
            {
                Position currentPos = new Position(i2,i);
                boolean isEdge = (i == 0 || i == 4 || i2 == 0 || i2 == 4);
                boolean isCenter = (i == 2 && i2 == 2);
                boolean isPos3_1 = (i == 1 && i2 == 3);
                boolean isHeroPos = (i == 3 && i2 == 3);
                boolean isEnemyPos = (i == 1 && i2 == 1);
                if (isEdge) elements.add(new Wall(currentPos));
                if (isCenter) elements.add(new Wall(currentPos));
                if (isPos3_1) elements.add(new Wall(currentPos));
                if (isEnemyPos) elements.add(new RangedEnemy(currentPos,effect)); //it's not Ranged for any particular reason, Enemy is abstract so a subclass had to be used
                if (isHeroPos) elements.add(new Hero(currentPos));
            }
        }
        Mockito.when(arenaModel.getElements()).thenReturn(elements);
    }

    @Test
    public void generateMatrix()
    {
        Node[][] expected = new Node[5][5];

        Node heroNode = new Node(Node.CellType.FREE,null,0,new Position(3,3));
        Node node3_2 = new Node(Node.CellType.FREE,heroNode,1,new Position(3,2));
        Node node2_3 = new Node(Node.CellType.FREE,heroNode,1,new Position(2,3));
        Node node1_3 = new Node(Node.CellType.FREE,node2_3,2,new Position(1,3));
        Node node1_2 = new Node(Node.CellType.FREE,node1_3,3,new Position(1,2));
        Node enemyNode = new Node(Node.CellType.ENEMY,node1_2,4,new Position(1,1));
        Node node2_1 = new Node(Node.CellType.FREE,enemyNode,5,new Position(2,1));

        for (int i = 0; i < 5; i++)
        {
            for (int i2 = 0; i2 < 5; i2++)
            {
                Position currentPos = new Position(i2,i);
                boolean isEdge = (i == 0 || i == 4 || i2 == 0 || i2 == 4);
                boolean isCenter = (i == 2 && i2 == 2);
                boolean isPos3_1 = (i == 1 && i2 == 3);
                boolean isHeroPos = (i == 3 && i2 == 3);
                boolean isEnemyPos = (i == 1 && i2 == 1);
                boolean isPos3_2 = (i == 2 && i2 == 3);
                boolean isPos2_3 = (i == 3 && i2 == 2);
                boolean isPos1_3 = (i == 3 && i2 == 1);
                boolean isPos2_1 = (i == 1 && i2 == 2);
                boolean isPos1_2 = (i == 2 && i2 == 1);

                if (isEdge) expected[i][i2] = new Node(Node.CellType.OBSTACLE,null,Integer.MAX_VALUE,currentPos);
                if (isCenter) expected[i][i2] = new Node(Node.CellType.OBSTACLE,null,Integer.MAX_VALUE,currentPos);
                if (isPos3_1) expected[i][i2] = new Node(Node.CellType.OBSTACLE,null,Integer.MAX_VALUE,currentPos);
                if (isHeroPos) expected[i][i2] = heroNode;
                if (isPos3_2) expected[i][i2] = node3_2;
                if (isPos2_3) expected[i][i2] = node2_3;
                if (isPos1_3) expected[i][i2] = node1_3;
                if (isEnemyPos) expected[i][i2] = enemyNode;
                if (isPos2_1) expected[i][i2] = node2_1;
                if (isPos1_2) expected[i][i2] = node1_2;
            }
        }
        arenaController.generateMatrix();
        assertEquals(expected,arenaController.arenaMatrix);

    }
}
