import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class DirectionTest {
    DynamicElement element;

    @Before
    public void setup()
    {
        this.element = Mockito.mock(DynamicElement.class);

    }

    @Test
    public void rotateRight()
    {
        element.direction.rotateRight();
        assertEquals(WestDirection.class,element.direction.getClass());
    }
}
