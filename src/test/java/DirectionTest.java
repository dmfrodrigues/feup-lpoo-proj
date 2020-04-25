import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class DirectionTest {
    DynamicElement element;
    Direction direction;

    @Before
    public void setup()
    {
        this.element = Mockito.mock(DynamicElement.class);
        Mockito.doCallRealMethod().when(element).changeDirection(any(Direction.class));
    }

    @Test
    public void rotateRight()
    {
        this.direction = new SouthDirection(this.element);
        direction.rotateRight();
        assertEquals(WestDirection.class,element.direction.getClass());
    }

    @Test
    public void rotateRight2()
    {
        this.direction = new WestDirection(this.element);
        direction.rotateRight();
        assertEquals(NorthDirection.class,element.direction.getClass());
    }
    @Test
    public void rotateRight3()
    {
        this.direction = new NorthDirection(this.element);
        direction.rotateRight();
        assertEquals(EastDirection.class,element.direction.getClass());
    }

    @Test
    public void rotateRight4()
    {
        this.direction = new EastDirection(this.element);
        direction.rotateRight();
        assertEquals(SouthDirection.class,element.direction.getClass());
    }

    @Test
    public void rotateLeft()
    {
        this.direction = new SouthDirection(this.element);
        direction.rotateLeft();
        assertEquals(EastDirection.class,element.direction.getClass());
    }

    @Test
    public void rotateLeft2()
    {
        this.direction = new EastDirection(this.element);
        direction.rotateLeft();
        assertEquals(NorthDirection.class,element.direction.getClass());
    }

    @Test
    public void rotateLeft3()
    {
        this.direction = new NorthDirection(this.element);
        direction.rotateLeft();
        assertEquals(WestDirection.class,element.direction.getClass());
    }

    @Test
    public void rotateLeft4()
    {
        this.direction = new WestDirection(this.element);
        direction.rotateLeft();
        assertEquals(SouthDirection.class,element.direction.getClass());
    }
}
