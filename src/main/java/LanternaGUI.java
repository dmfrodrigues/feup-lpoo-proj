import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class LanternaGUI implements TerminalGUI {
    private Screen screen;
    public LanternaGUI(){
        // Initialize screen
        try{
            DefaultTerminalFactory factory = new DefaultTerminalFactory();
            factory.setInitialTerminalSize(new TerminalSize(233, 57));
            Terminal terminal = factory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void drawCharacter(int x, int y, char c, Color f, Color b) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(new TextColor.RGB(f.getR(), f.getG(), f.getB()));
        graphics.setBackgroundColor(new TextColor.RGB(b.getR(), b.getG(), b.getB()));
        graphics.putString(x, y, String.valueOf(c));
    }

    @Override
    public void clear(){
        screen.doResizeIfNecessary();
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }
}
