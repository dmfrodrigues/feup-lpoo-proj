import org.junit.Test;

import static org.junit.Assert.*;

public class ElementTest {
    public class NotAWall extends StaticElement{
        public NotAWall(Position pos){ super(pos); }
    }
    @Test
    public void wallOrNotAWall(){
        Wall wall = new Wall(new Position(5, 7));
        Wall eqWall = new Wall(new Position(5, 7));
        Wall difWall = new Wall(new Position(5, 6));
        NotAWall eqNotAWall = new NotAWall(new Position(5, 7));
        NotAWall difNotAWall = new NotAWall(new Position(5, 6));
        assertEquals(wall, eqWall);
        assertNotEquals(wall, difWall);
        assertNotEquals(wall, eqNotAWall);
        assertNotEquals(wall, difNotAWall);
    }
}