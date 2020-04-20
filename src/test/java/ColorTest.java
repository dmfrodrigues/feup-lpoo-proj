import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ColorTest {
    final int MAX_COLOR = 255;
    final int GETTERS_NUM_TESTS = 100;
    @Test
    public void getters(){
        for(int i = 0; i < GETTERS_NUM_TESTS; ++i){
            int r = (int)Math.random()*MAX_COLOR;
            int g = (int)Math.random()*MAX_COLOR;
            int b = (int)Math.random()*MAX_COLOR;
            Color c = new Color(r, g, b);
            assertEquals(r, c.getR());
            assertEquals(g, c.getG());
            assertEquals(b, c.getB());
        }
    }
}
