package com.pacman.g60.View;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.pacman.g60.Model.*;
import com.pacman.g60.Model.Elements.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class TerminalArenaView implements ArenaView {
    
    private abstract class ElementView {
        private final static int Wtile = 8;
        private final static int Htile = 4;
        
        private Position heroPos;
        public ElementView(){}
        public final void setHeroPos(Position heroPos) { this.heroPos = heroPos; }
        protected abstract TerminalSprite getSprite(Element e);
        public final void draw(Element e){
            TerminalSprite sprite = getSprite(e);
            int x0 = (e.getPos().getX()-heroPos.getX())*Wtile + terminalGUI.getW()/2;
            int y0 = (e.getPos().getY()-heroPos.getY())*Htile + terminalGUI.getH()/2;
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
    private class WallView extends ElementView {
        TerminalSprite sprite;
        
        public WallView() throws FileNotFoundException{
            TerminalSprite.Loader loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/wall-8-4.lan"));
            sprite = loader.getTerminalSprite();
        }
        
        @Override
        protected TerminalSprite getSprite(Element e) {
            return sprite;
        }
    }
    private class HeroView extends ElementView {
        TerminalSpriteOrientable spriteOrientable;
        
        public HeroView() throws FileNotFoundException {
            TerminalSprite.Loader loader;
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/hero-8-4-right.lan"));
            spriteOrientable = new TerminalSpriteOrientable(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/hero-8-4-left.lan"));
            spriteOrientable.setSpriteLeft(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/hero-8-4-up.lan"));
            spriteOrientable.setSpriteUp(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/hero-8-4-down.lan"));
            spriteOrientable.setSpriteDown(loader.getTerminalSprite());
        }

        @Override
        protected TerminalSprite getSprite(Element e) {
            return spriteOrientable.getSprite(((DynamicElement) e).getDirection());
        }
    }
    private class GhostView extends ElementView {
        TerminalSpriteOrientable spriteOrientable;

        public GhostView() throws FileNotFoundException {
            TerminalSprite.Loader loader;
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/ghost-8-4-right.lan"));
            spriteOrientable = new TerminalSpriteOrientable(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/ghost-8-4-left.lan"));
            spriteOrientable.setSpriteLeft(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/ghost-8-4-up.lan"));
            spriteOrientable.setSpriteUp(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/ghost-8-4-down.lan"));
            spriteOrientable.setSpriteDown(loader.getTerminalSprite());
        }

        @Override
        protected TerminalSprite getSprite(Element e) {
            return spriteOrientable.getSprite(((DynamicElement) e).getDirection());
        }
    }
    
    private class ElementViewFactory {
        private Position heroPos;
        private WallView wallView;
        private HeroView heroView;
        private GhostView ghostView;
        public ElementViewFactory() throws FileNotFoundException{
            wallView = new WallView();
            heroView = new HeroView();
            ghostView = new GhostView();
        }

        public void setHeroPos(Position heroPos) {
            this.heroPos = heroPos;
            wallView.setHeroPos(heroPos);
            heroView.setHeroPos(heroPos);
            ghostView.setHeroPos(heroPos);
        }

        public ElementView factoryMethod(Element e){
            ElementView res = null;
            if     (e instanceof Wall) res = wallView;
            else if(e instanceof Hero) res = heroView;
            else if(e instanceof Ghost) res = ghostView;
            return res;
        }
    }
    
    private TerminalGUI terminalGUI;
    ElementViewFactory elementViewFactory;

    public TerminalArenaView(TerminalGUI terminalGUI) throws FileNotFoundException{
        this.terminalGUI = terminalGUI;
        elementViewFactory = new ElementViewFactory();
    }

    @Override
    public void draw(ArenaModel arena) throws IOException {
        terminalGUI.clear();
        List<Element> listElements = arena.getElements();
        elementViewFactory.setHeroPos(arena.getHero().getPos());
        for(final Element e : listElements){
            ElementView elementView = elementViewFactory.factoryMethod(e);
            elementView.draw(e);
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
