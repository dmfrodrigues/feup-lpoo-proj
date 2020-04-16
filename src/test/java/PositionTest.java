import javafx.geometry.Pos;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PositionTest {

    @Test
    public void getX()
    {
        Position position = new Position(2,4);
        assertEquals(((Integer)2),position.getX());
    }

    @Test
    public void getY()
    {
        Position position = new Position(5,6);
        assertEquals(((Integer)6),position.getY());
    }

    @Test
    public void setX()
    {
        Position position = new Position(10,32);
        position.setX(5);
        assertEquals(((Integer)5),position.getX());
    }

    @Test
    public void setY()
    {
        Position position = new Position(10,32);
        position.setY(5);
        assertEquals(((Integer)5),position.getY());
    }

    @Test
    public void equals()
    {
        Position position = new Position(10,10);
        Position position2 = new Position(5,5);
        assertNotEquals(position,position2);
    }

    @Test
    public void equals2()
    {
        Position position = new Position(10,20);
        Position position2 = new Position(10,20);
        assertEquals(position,position2);
    }
}
