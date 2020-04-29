import com.pacman.g60.View.Color;
import com.pacman.g60.View.TerminalSprite;
import com.pacman.g60.View.TerminalSpriteLoaderStream;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class TerminalSpriteLoaderStreamTest {
    
    @Test
    public void test() {
        String s =
                "8 4\n" +
                        "#000000 #000000 #000000 #000000 #000000 #000000 #000000 #000000\n" +
                        "#000000 #000000 #969696 #000000 #969696 #000000 #000000 #000000\n" +
                        "#000000 #000000 #000000 #000000 #000000 #000000 #000000 #000000\n" +
                        "#000000 #000000 #000000 #000000 #000000 #000000 #000000 #000000\n" +
                        " ▄▄▄▄▖╻ \n" +
                        "▐█▬█▬▌┣█\n" +
                        "▐█▛▜█▌┃ \n" +
                        "▝▀▘▝▀▘╹ \n" +
                        "#969696 #969696 #969696 #969696 #969696 #969696 #646464 #646464\n" +
                        "#969696 #969696 #000000 #969696 #000000 #969696 #646464 #646464\n" +
                        "#F0F0F0 #F0F0F0 #F0F0F0 #F0F0F0 #F0F0F0 #F0F0F0 #646464 #646464\n" +
                        "#964B00 #964B00 #964B00 #964B00 #964B00 #964B00 #646464 #646464\n";
        InputStream inputStream = new ByteArrayInputStream(s.getBytes());
        TerminalSprite.Loader loader = new TerminalSpriteLoaderStream(inputStream);
        TerminalSprite sprite = loader.getTerminalSprite();
        assertEquals(sprite.getW(), (Integer) 8);
        assertEquals(sprite.getH(), (Integer) 4);
        assertEquals(sprite.getBackgroundColor(0, 0), new Color(0, 0, 0));
        assertEquals(sprite.getBackgroundColor(2, 1), new Color(150, 150, 150));
        assertEquals(sprite.getBackgroundColor(3, 1), new Color(0, 0, 0));
        assertEquals(sprite.getBackgroundColor(4, 1), new Color(150, 150, 150));
        assertEquals(sprite.getChar(2, 1), '▬');
        assertEquals(sprite.getChar(3, 1), '█');
        assertEquals(sprite.getChar(4, 1), '▬');
        assertEquals(sprite.getChar(5, 1), '▌');
        assertEquals(sprite.getChar(6, 1), '┣');
        assertEquals(sprite.getChar(2, 3), '▘');
    }
}
