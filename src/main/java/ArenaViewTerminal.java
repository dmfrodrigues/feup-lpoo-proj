import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArenaViewTerminal implements ArenaView {
    private TerminalGUI terminalGUI;

    private Map<String, TerminalSprite> spriteMap;
    private Map<String, TerminalSpriteOrientable> spriteOrientableMap;

    public ArenaViewTerminal(TerminalGUI terminalGUI){
        this.terminalGUI = terminalGUI;
        spriteMap = new HashMap<>();
        spriteOrientableMap = new HashMap<>();

        try {
            TerminalSprite.Loader loader;
            TerminalSpriteOrientable spriteOrientable;
            //Wall
            loader = new TerminalSpriteLoaderFile(new FileInputStream("src/main/resources/lanterna-sprites/wall-8-4.lan"));
            spriteMap.put("class Wall", loader.getTerminalSprite());
            //Hero
            loader = new TerminalSpriteLoaderFile(new FileInputStream("src/main/resources/lanterna-sprites/hero-8-4-right.lan"));
            spriteOrientable = new TerminalSpriteOrientable(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderFile(new FileInputStream("src/main/resources/lanterna-sprites/hero-8-4-left.lan"));
            spriteOrientable.setSpriteLeft(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderFile(new FileInputStream("src/main/resources/lanterna-sprites/hero-8-4-up.lan"));
            spriteOrientable.setSpriteUp(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderFile(new FileInputStream("src/main/resources/lanterna-sprites/hero-8-4-down.lan"));
            spriteOrientable.setSpriteDown(loader.getTerminalSprite());
            spriteOrientableMap.put("class Hero", spriteOrientable);
            //Ghost
            loader = new TerminalSpriteLoaderFile(new FileInputStream("src/main/resources/lanterna-sprites/ghost-8-4-right.lan"));
            spriteOrientable = new TerminalSpriteOrientable(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderFile(new FileInputStream("src/main/resources/lanterna-sprites/ghost-8-4-left.lan"));
            spriteOrientable.setSpriteLeft(loader.getTerminalSprite());
            spriteOrientableMap.put("class Ghost", spriteOrientable);
        } catch(FileNotFoundException e){
            System.err.println("Failed to find file");
        }

    }

    public class ElementView {
        private final static int Wscreen = 8;
        private final static int Hscreen = 4;

        public void draw(Element e){
            Position pos = e.getPos();
            TerminalSprite sprite;

            if(e instanceof StaticElement){
                sprite = spriteMap.get(e.getClass().toString());
            } else if(e instanceof DynamicElement) {
                sprite = spriteOrientableMap.get(e.getClass().toString()).getSprite(((DynamicElement) e).getDirection());
            } else return;

            int W = sprite.getW();
            int H = sprite.getH();
            int x0 = pos.getX()*W;
            int y0 = pos.getY()*H;
            for(int x = 0; x < Wscreen; ++x) {
                for (int y = 0; y < Hscreen; ++y) {
                    terminalGUI.drawCharacter(x0 + x, y0 + y,
                            sprite.getChar(x, y),
                            sprite.getForegroundColor(x, y),
                            sprite.getBackgroundColor(x, y));
                }
            }
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
