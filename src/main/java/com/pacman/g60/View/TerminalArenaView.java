package com.pacman.g60.View;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.Elements.DynamicElement;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.StaticElement;
import com.pacman.g60.Model.Position;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerminalArenaView implements ArenaView {
    private TerminalGUI terminalGUI;

    private Map<String, TerminalSprite> spriteMap;
    private Map<String, TerminalSpriteOrientable> spriteOrientableMap;

    public TerminalArenaView(TerminalGUI terminalGUI){
        this.terminalGUI = terminalGUI;
        spriteMap = new HashMap<>();
        spriteOrientableMap = new HashMap<>();

        try {
            TerminalSprite.Loader loader;
            TerminalSpriteOrientable spriteOrientable;
            //Wall
            loader = new TerminalSpriteLoaderFile(new FileInputStream("src/main/resources/lanterna-sprites/wall-8-4.lan"));
            spriteMap.put("class com.pacman.g60.Model.Wall", loader.getTerminalSprite());
            //Hero
            loader = new TerminalSpriteLoaderFile(new FileInputStream("src/main/resources/lanterna-sprites/hero-8-4-right.lan"));
            spriteOrientable = new TerminalSpriteOrientable(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderFile(new FileInputStream("src/main/resources/lanterna-sprites/hero-8-4-left.lan"));
            spriteOrientable.setSpriteLeft(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderFile(new FileInputStream("src/main/resources/lanterna-sprites/hero-8-4-up.lan"));
            spriteOrientable.setSpriteUp(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderFile(new FileInputStream("src/main/resources/lanterna-sprites/hero-8-4-down.lan"));
            spriteOrientable.setSpriteDown(loader.getTerminalSprite());
            spriteOrientableMap.put("class com.pacman.g60.Model.Hero", spriteOrientable);
            //Ghost
            loader = new TerminalSpriteLoaderFile(new FileInputStream("src/main/resources/lanterna-sprites/ghost-8-4-right.lan"));
            spriteOrientable = new TerminalSpriteOrientable(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderFile(new FileInputStream("src/main/resources/lanterna-sprites/ghost-8-4-left.lan"));
            spriteOrientable.setSpriteLeft(loader.getTerminalSprite());
            spriteOrientableMap.put("class com.pacman.g60.Model.Ghost", spriteOrientable);
        } catch(FileNotFoundException e){
            System.err.println("Failed to find file");
        }

    }

    public class ElementView {
        private final static int Wtile = 8;
        private final static int Htile = 4;
        private final Position heroPos;
        
        public ElementView(Position heroPos){
            this.heroPos = heroPos;
        }

        public void draw(Element e){
            Position pos = e.getPos();
            TerminalSprite sprite;
            
            if(e instanceof StaticElement){
                sprite = spriteMap.get(e.getClass().toString());
            } else if(e instanceof DynamicElement) {
                sprite = spriteOrientableMap.get(e.getClass().toString()).getSprite(((DynamicElement) e).getDirection());
            } else return;
            
            int x0 = (pos.getX()-heroPos.getX())*Wtile + terminalGUI.getW()/2;
            int y0 = (pos.getY()-heroPos.getY())*Htile + terminalGUI.getH()/2;
            for(int x = 0; x < sprite.getW(); ++x) {
                for (int y = 0; y < sprite.getH(); ++y) {
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
        ElementView elementViewTerminal = new ElementView(arena.getHero().getPos());
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
}
