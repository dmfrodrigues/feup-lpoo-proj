import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.List;

public class ArenaViewTerminal implements ArenaView {
    private TerminalGUI terminalGUI;
    public ArenaViewTerminal(TerminalGUI terminalGUI){
        this.terminalGUI = terminalGUI;
    }

    public class ElementView {
        private final static int Wscreen = 8;
        private final static int Hscreen = 4;

        public abstract class ElementDraw {}

        public abstract class StaticElementDraw extends ElementDraw{
            abstract char[][] getChars();
            abstract Color[][] getForegroundColors();
            abstract Color[][] getBackgroundColors();
            public void draw(StaticElement e){
                final char[][] chars = getChars();
                final Color[][] fore = getForegroundColors();
                final Color[][] back = getBackgroundColors();
                int x0 = Wscreen*e.getPos().getX();
                int y0 = Hscreen*e.getPos().getY();
                for(int x = 0; x < Wscreen; ++x){
                    for(int y = 0; y < Hscreen; ++y){
                        terminalGUI.drawCharacter(x0+x, y0+y, chars[y][x], fore[y][x], back[y][x]);
                    }
                }
            }
        }

        public class WallDraw extends StaticElementDraw{
            private final Color ge = new Color(90, 90, 90);
            private final Color or = new Color(232, 126, 61);
            /*
            private final char[][] chars = {
                    "╦╩╦╩╦╩╦╩".toCharArray(),
                    "╩╦╩╦╩╦╩╦".toCharArray(),
                    "╦╩╦╩╦╩╦╩".toCharArray(),
                    "╩╦╩╦╩╦╩╦".toCharArray()
            };
             */
            private final char[][] chars = {
                "┳┻┳┻┳┻┳┻".toCharArray(),
                "┻┳┻┳┻┳┻┳".toCharArray(),
                "┳┻┳┻┳┻┳┻".toCharArray(),
                "┻┳┻┳┻┳┻┳".toCharArray()
            };
            private final Color[][] fore = {
                    {ge, ge, ge, ge, ge, ge, ge, ge},
                    {ge, ge, ge, ge, ge, ge, ge, ge},
                    {ge, ge, ge, ge, ge, ge, ge, ge},
                    {ge, ge, ge, ge, ge, ge, ge, ge}
            };
            private final Color[][] back = {
                    {or, or, or, or, or, or, or, or},
                    {or, or, or, or, or, or, or, or},
                    {or, or, or, or, or, or, or, or},
                    {or, or, or, or, or, or, or, or}
            };
            @Override
            char[][] getChars() { return chars; }

            @Override
            Color[][] getForegroundColors() { return fore; }

            @Override
            Color[][] getBackgroundColors() { return back; }
        }

        public abstract class DynamicElementDraw extends ElementDraw{
            abstract char[][] getChars();
            abstract Color[][] getForegroundColors();
            abstract Color[][] getBackgroundColors();
            public void draw(DynamicElement e){
                final char[][] chars = getChars();
                final Color[][] fore = getForegroundColors();
                final Color[][] back = getBackgroundColors();
                int x0 = Wscreen*e.getPos().getX();
                int y0 = Hscreen*e.getPos().getY();
                for(int x = 0; x < Wscreen; ++x){
                    for(int y = 0; y < Hscreen; ++y){
                        terminalGUI.drawCharacter(x0+x, y0+y, chars[y][x], fore[y][x], back[y][x]);
                    }
                }
            }
        }

        public class HeroDraw extends DynamicElementDraw {
            private final Color wh = new Color(240, 240, 240); //(Almost) white
            private final Color bl = new Color(0, 0, 0); //Black
            private final Color br = new Color(150, 75, 0); //Brown
            private final Color dg = new Color(100, 100, 100); //Dark grey
            private final Color gr = new Color(150, 150, 150); //Grey
            private final Color lg = new Color(200, 200, 200); //Light grey
            private final Color gl = new Color(255, 215, 0); //Gold
            private final Color rd = new Color(200, 50, 50); //Red
            /*
            private final char[][] chars = {
                " ▄▄▄▄▖╻ ".toCharArray(),
                "▐█▬█▬▌┣█".toCharArray(),
                "▐█▛▜█▌┃ ".toCharArray(),
                "▝▀▘▝▀▘╹ ".toCharArray()
            };
             */
            private final char[][] chars = {
                    " ▄▄▄▄▖╻ ".toCharArray(),
                    "▐█▬█▬▌┣█".toCharArray(),
                    "▐█▛▜█▚▚ ".toCharArray(),
                    "▝▀▘▝▀▝▘ ".toCharArray()
            };
            private final Color[][] fore = {
                { gr, gr, gr, gr, gr, gr, dg, dg},
                { gr, gr, bl, gr, bl, gr, dg, dg},
                { wh, wh, wh, wh, wh, lg, lg, dg},
                { br, br, br, br, br, rd, lg, dg}
            };
            private final Color[][] back = {
                { bl, bl, bl, bl, bl, bl, bl, bl},
                { bl, bl, gr, bl, gr, bl, bl, bl},
                { bl, bl, bl, bl, bl, rd, rd, bl},
                { bl, bl, bl, bl, bl, bl, bl, bl}
            };

            @Override
            char[][] getChars() { return chars; }

            @Override
            Color[][] getForegroundColors() { return fore; }

            @Override
            Color[][] getBackgroundColors() { return back; }
        }

        public void draw(Element e){
            WallDraw wallDraw = new WallDraw();
            HeroDraw heroDraw = new HeroDraw();

            if(e instanceof Wall) wallDraw.draw((StaticElement) e);
            else if(e instanceof Hero) heroDraw.draw((DynamicElement)e);
        }

    }

    @Override
    public void draw(ArenaModel arena) throws IOException {
        terminalGUI.clear();
        List<Element> listElements = arena.getElements();
        ElementView elementViewTerminal = new ElementView();
        for(final Element e : listElements){
            elementViewTerminal.draw(e);
        }
        terminalGUI.refresh();
    }

    @Override
    public COMMAND pollCommand() throws IOException{
        KeyStroke key = terminalGUI.pollKey();
        if(key == null) return null;
        if(key.getKeyType() == KeyType.ArrowUp   ) return COMMAND.UP;
        if(key.getKeyType() == KeyType.ArrowDown ) return COMMAND.DOWN;
        if(key.getKeyType() == KeyType.ArrowLeft ) return COMMAND.LEFT;
        if(key.getKeyType() == KeyType.ArrowRight) return COMMAND.RIGHT;
        if(key.getKeyType() == KeyType.Escape    ) return COMMAND.EXIT;
        return null;
    }

    @Override
    public void close() throws IOException{
        terminalGUI.close();
    }
}
