package com.pacman.g60.View;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;
import com.pacman.g60.Model.*;
import com.pacman.g60.Model.Elements.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class TerminalArenaView implements ArenaView {
    private class TextView{
        TerminalFont font;
        public TextView() throws FileNotFoundException{
            TerminalFont.Loader loader = new TerminalFontLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/numbers-4-3.lan"));
            font = loader.getTerminalFont();
        }
        public Integer getStringWidth(String s){ return s.length()*(font.getW()+1)-1; }
        public Integer getStringHeight(String s){ return font.getH(); }
        private void drawChar(int x0, int y0, Character c){
            TerminalFont.TerminalCharacter tchar = font.getCharacter(c);
            Integer W = tchar.getW();
            Integer H = tchar.getH();
            for(int x = 0; x < W; ++x){
                for(int y = 0; y < H; ++y){
                    terminalGUI.drawCharacter(x0+x, y0+y, tchar.getChar(x, y), Color.WHITE, Color.BLACK);
                }
            }
        }
        public void draw(int x0, int y0, String s){
            for(int i = 0; i < s.length(); ++i){
                drawChar(x0+i*(font.getW()+1), y0, s.charAt(i));
            }
        }
    }
    
    private class InfoBar {
        private final Integer H = 5;
        private final Integer Wmargin = 2;
        private void drawFrame(){
            for(Integer x = 1; x < terminalGUI.getW()-1; ++x){
                terminalGUI.drawCharacter(x, 0, '▀', Color.GREY, Color.BLACK);
                terminalGUI.drawCharacter(x, H-1, '▄', Color.GREY, Color.BLACK);
            }
            for(Integer y = 1; y < H-1; ++y){
                terminalGUI.drawCharacter(0, y, '█', Color.GREY, Color.BLACK);
                terminalGUI.drawCharacter(terminalGUI.getW()-1, y, '█', Color.GREY, Color.BLACK);
            }
            for(Integer x = 1; x < terminalGUI.getW()-1; ++x){
                for(Integer y = 1; y < H-1; ++y){
                    terminalGUI.drawCharacter(x, y, ' ', Color.BLACK, Color.BLACK);
                }
            }
            terminalGUI.drawCharacter(0, 0, '█', Color.GREY, Color.BLACK); terminalGUI.drawCharacter(terminalGUI.getW()-1, 0, '█', Color.GREY, Color.BLACK);
            terminalGUI.drawCharacter(0, H-1, '█', Color.GREY, Color.BLACK); terminalGUI.drawCharacter(terminalGUI.getW()-1, H-1, '█', Color.GREY, Color.BLACK);
        }
        private void drawSprite(TerminalSprite sprite, int x0, int y0){
            for(int x = 0; x < sprite.getW(); ++x) {
                for (int y = 0; y < sprite.getH(); ++y) {
                    terminalGUI.drawCharacter(x0 + x, y0 + y,
                            sprite.getChar(x, y),
                            sprite.getForegroundColor(x, y),
                            sprite.getBackgroundColor(x, y));
                }
            }
        }
        TerminalSprite heartSprite;
        TerminalSprite heartDeadSprite;
        TerminalSprite coinSprite;
        TextView textView;
        long startTime;
        public InfoBar() throws FileNotFoundException{
            TerminalSprite.Loader loader;
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/heart-6-3.lan"));
            heartSprite = loader.getTerminalSprite();
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/heart-dead-6-3.lan"));
            heartDeadSprite = loader.getTerminalSprite();
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/coin-6-3.lan"));
            coinSprite = loader.getTerminalSprite();
            textView = new TextView();
            startTime = System.currentTimeMillis();
        }
        public void draw(ArenaModel arenaModel){
            drawFrame();
            
            double health = arenaModel.getHero().getHealth()*0.75;
            double maxHealth = arenaModel.getHero().getMaxHealth();
            int coins = arenaModel.getHero().getCoins();
            int totalCoins = arenaModel.getNumCoins() + coins;
            int x0 = 2;
            int y0 = 1;
            for(int x = 0; x < maxHealth*heartSprite.getW(); ++x) {
                for (int y = 0; y < heartSprite.getH(); ++y) {
                    if(x < health*heartSprite.getW()) {
                        terminalGUI.drawCharacter(x0 + x, y0 + y,
                                heartSprite.getChar(x % heartSprite.getW(), y),
                                heartSprite.getForegroundColor(x % heartSprite.getW(), y),
                                heartSprite.getBackgroundColor(x % heartSprite.getW(), y));
                    } else {
                        terminalGUI.drawCharacter(x0 + x, y0 + y,
                                heartDeadSprite.getChar(x % heartDeadSprite.getW(), y),
                                heartDeadSprite.getForegroundColor(x % heartDeadSprite.getW(), y),
                                heartDeadSprite.getBackgroundColor(x % heartDeadSprite.getW(), y));
                    }
                }
            }
            String coinsStr = String.format("%d/%d", coins, totalCoins);
            
            int coinsStrX = terminalGUI.getW()-Wmargin-textView.getStringWidth(coinsStr);
            textView.draw(coinsStrX, 1+coinSprite.getH()-textView.getStringHeight(coinsStr), coinsStr);
            //terminalGUI.drawString(coinsStrX, coinSprite.getH(), coinsStr, new Color(255, 255, 255), new Color(0, 0, 0));
            int coinsSpriteX = coinsStrX - coinSprite.getW() -1;
            drawSprite(coinSprite, coinsSpriteX, 1);
            
        }
    }
    
    private abstract class ElementView {
        private final static int Wtile = 8;
        private final static int Htile = 4;
        
        private Position heroPos;
        public ElementView(){}
        public final void setHeroPos(Position heroPos) { this.heroPos = heroPos; }
        protected abstract TerminalSprite getSprite(Element e);
        public final void draw(Element e){
            TerminalSprite sprite = getSprite(e);
            int x0 = (e.getPos().getX()-heroPos.getX())*Wtile + terminalGUI.getW()/2 - Wtile/2;
            int y0 = (e.getPos().getY()-heroPos.getY())*Htile + terminalGUI.getH()/2 - Htile/2;
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
    private class CoinView extends ElementView {
        TerminalSprite sprite;

        public CoinView() throws FileNotFoundException{
            TerminalSprite.Loader loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/coin-8-4.lan"));
            sprite = loader.getTerminalSprite();
        }

        @Override
        protected TerminalSprite getSprite(Element e) {
            return sprite;
        }
    }
    
    private class ElementViewFactory {
        private Position heroPos;
        private WallView wallView;
        private HeroView heroView;
        private GhostView ghostView;
        private CoinView coinView;
        public ElementViewFactory() throws FileNotFoundException{
            wallView = new WallView();
            heroView = new HeroView();
            ghostView = new GhostView();
            coinView = new CoinView();
        }

        public void setHeroPos(Position heroPos) {
            this.heroPos = heroPos;
            wallView.setHeroPos(heroPos);
            heroView.setHeroPos(heroPos);
            ghostView.setHeroPos(heroPos);
            coinView.setHeroPos(heroPos);
        }

        public ElementView factoryMethod(Element e){
            ElementView res = null;
            if     (e instanceof Wall) res = wallView;
            else if(e instanceof Hero) res = heroView;
            else if(e instanceof Ghost) res = ghostView;
            else if(e instanceof Coin) res = coinView;
            return res;
        }
    }
    
    private TerminalGUI terminalGUI;
    ElementViewFactory elementViewFactory;
    InfoBar infoBar;
    
    public TerminalArenaView(TerminalGUI terminalGUI) throws FileNotFoundException{
        this.terminalGUI = terminalGUI;
        elementViewFactory = new ElementViewFactory();
        infoBar = new InfoBar();
    }

    @Override
    public void draw(ArenaModel arena) throws IOException {
        terminalGUI.clear();
        List<Element> listElements = arena.getElements();
        elementViewFactory.setHeroPos(arena.getHero().getPos());
        for(final Element e : listElements){
            ElementView elementView = elementViewFactory.factoryMethod(e);
            if(elementView != null) elementView.draw(e);
        }
        infoBar.draw(arena);
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
