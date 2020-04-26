import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public interface TerminalGUI extends GUI{
    void drawCharacter(int x, int y, char c, Color f, Color b);

    KeyStroke pollKey() throws IOException;
}
