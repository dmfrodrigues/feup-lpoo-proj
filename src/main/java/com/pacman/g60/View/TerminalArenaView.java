package com.pacman.g60.View;


import com.pacman.g60.Model.*;
import com.pacman.g60.Model.Elements.*;
import com.pacman.g60.Model.Elements.Hierarchy.OrientedElement;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerminalArenaView extends ArenaView {

    private static final Integer MILLIS_TO_SECONDS = 1000;
    
    private static final Integer HInfoBar = 5;

    private class InfoBar {
        private final Integer Wmargin = 2;
        private TextModel textModelCoin;
        private TerminalTextView textViewCoin;
        private TextModel textModelTimer;
        private TerminalTextView textViewTimer;

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
        
        long startTime;
        public void startTime(){ startTime = System.currentTimeMillis(); }
        public InfoBar() throws FileNotFoundException{
            TerminalSprite.Loader loader;
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/heart-6-3.lan"));
            heartSprite = loader.getTerminalSprite();
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/heart-dead-6-3.lan"));
            heartDeadSprite = loader.getTerminalSprite();
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/coin-6-3.lan"));
            coinSprite = loader.getTerminalSprite();
            
            textModelCoin = new TextModel("");
            textModelCoin.setVerticalAlign(Alignable.VerticalAlign.TOP);
            textModelCoin.setHorizontalAlign(Alignable.HorizontalAlign.LEFT);
            textViewCoin = new TerminalTextView(terminalGUI, font);
            textViewCoin.setTextModel(textModelCoin);
            
            textModelTimer = new TextModel("");
            textModelTimer.setVerticalAlign(Alignable.VerticalAlign.TOP);
            textModelTimer.setHorizontalAlign(Alignable.HorizontalAlign.CENTER);
            textViewTimer = new TerminalTextView(terminalGUI, font);
            textViewTimer.setTextModel(textModelTimer);

            startTime();
        }
        private void drawFrame(){
            for(Integer x = 1; x < terminalGUI.getW()-1; ++x){
                terminalGUI.drawCharacter(x, 0, '▀', Color.GREY, Color.BLACK);
                terminalGUI.drawCharacter(x, HInfoBar-1, '▄', Color.GREY, Color.BLACK);
            }
            for(Integer y = 1; y < HInfoBar-1; ++y){
                terminalGUI.drawCharacter(0, y, '█', Color.GREY, Color.BLACK);
                terminalGUI.drawCharacter(terminalGUI.getW()-1, y, '█', Color.GREY, Color.BLACK);
            }
            for(Integer x = 1; x < terminalGUI.getW()-1; ++x){
                for(Integer y = 1; y < HInfoBar-1; ++y){
                    terminalGUI.drawCharacter(x, y, ' ', Color.BLACK, Color.BLACK);
                }
            }
            terminalGUI.drawCharacter(0, 0, '█', Color.GREY, Color.BLACK); terminalGUI.drawCharacter(terminalGUI.getW()-1, 0, '█', Color.GREY, Color.BLACK);
            terminalGUI.drawCharacter(0, HInfoBar-1, '█', Color.GREY, Color.BLACK); terminalGUI.drawCharacter(terminalGUI.getW()-1, HInfoBar-1, '█', Color.GREY, Color.BLACK);
        }
        private void drawHealth(double health, double maxHealth){
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
        }
        private void drawCoins(int coins, int totalCoins){
            String coinsStr = String.format("%d/%d", coins, totalCoins);
            int coinsStrX = terminalGUI.getW()-Wmargin-textViewCoin.getStringWidth(coinsStr);
            textModelCoin.setText(coinsStr);
            textModelCoin.setPosition(new Position(coinsStrX, 1+coinSprite.getH()-textViewCoin.getStringHeight(textModelCoin.getText())));
            textViewCoin.draw();
            int coinsSpriteX = coinsStrX - coinSprite.getW() -1;
            drawSprite(coinSprite, coinsSpriteX, 1);
        }
        private void drawTimer(){
            long time = (System.currentTimeMillis() - startTime)/MILLIS_TO_SECONDS;
            long sec = time%60;
            long min = time/60;
            String sSec = String.format("%02d", sec);
            String sMin = String.format("%d", min);
            String timerString = sMin + ":" + sSec;
            textModelTimer.setText(sMin + ":" + sSec);
            textModelTimer.setPosition(new Position(terminalGUI.getW()/2, 1+coinSprite.getH()-textViewCoin.getStringHeight(textModelTimer.getText())));
            textViewTimer.draw();
        }
        public void draw(ArenaModel arenaModel){
            drawFrame();
            drawHealth(arenaModel.getHero().getHealth(), arenaModel.getHero().getMaxHealth());
            drawCoins(arenaModel.getHero().getCoins(), arenaModel.getNumCoins() + arenaModel.getHero().getCoins());
            drawTimer();
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
            int y0 = (e.getPos().getY()-heroPos.getY())*Htile + (terminalGUI.getH()-HInfoBar)/2 - Htile/2 + HInfoBar;
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
    private class OgreView extends ElementView {
        TerminalSpriteOrientable spriteOrientable;

        public OgreView() throws FileNotFoundException {
            TerminalSprite.Loader loader;
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/ogre-8-4-right.lan"));
            spriteOrientable = new TerminalSpriteOrientable(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/ogre-8-4-left.lan"));
            spriteOrientable.setSpriteLeft(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/ogre-8-4-up.lan"));
            spriteOrientable.setSpriteUp(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/ogre-8-4-down.lan"));
            spriteOrientable.setSpriteDown(loader.getTerminalSprite());
        }

        @Override
        protected TerminalSprite getSprite(Element e) {
            return spriteOrientable.getSprite(((DynamicElement) e).getDirection());
        }
    }

    private class BulletView extends ElementView {
        TerminalSpriteOrientable spriteOrientable;

        public BulletView() throws FileNotFoundException {
            TerminalSprite.Loader loader;
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/bullet-8-4-right.lan"));
            spriteOrientable = new TerminalSpriteOrientable(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/bullet-8-4-left.lan"));
            spriteOrientable.setSpriteLeft(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/bullet-8-4-up.lan"));
            spriteOrientable.setSpriteUp(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/bullet-8-4-down.lan"));
            spriteOrientable.setSpriteDown(loader.getTerminalSprite());
        }

        @Override
        protected TerminalSprite getSprite(Element e) {
            return spriteOrientable.getSprite(((OrientedElement) e).getDirection());
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
    private class HealthPotionView extends ElementView {
        TerminalSprite sprite;

        public HealthPotionView() throws FileNotFoundException{
            TerminalSprite.Loader loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/healthpotion-8-4.lan"));
            sprite = loader.getTerminalSprite();
        }

        @Override
        protected TerminalSprite getSprite(Element e) {
            return sprite;
        }
    }


    private class SwordView extends ElementView
    {
        TerminalSprite sprite;

        public SwordView() throws FileNotFoundException {
            TerminalSprite.Loader loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/sword-8-4.lan"));
            sprite = loader.getTerminalSprite();
        }

        @Override
        protected TerminalSprite getSprite(Element e)
        {
            return sprite;
        }

    }

    private class MummyView extends ElementView
    {
        TerminalSprite sprite;

        public MummyView() throws FileNotFoundException
        {
            TerminalSprite.Loader loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/mummy.lan"));
            sprite = loader.getTerminalSprite();
        }

        @Override
        protected TerminalSprite getSprite(Element e) {
            return sprite;
        }
    }

    private class GuardView extends ElementView
    {
        TerminalSprite sprite;

        public GuardView() throws FileNotFoundException
        {
            TerminalSprite.Loader loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/guard.lan"));
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
        private OgreView ogreView;
        private CoinView coinView;
        private HealthPotionView healthPotionView;
        private SwordView swordView;
        private BulletView bulletView;
        private MummyView mummyView;
        private GuardView guardView;

        public ElementViewFactory() throws FileNotFoundException{
            wallView = new WallView();
            heroView = new HeroView();
            ghostView = new GhostView();
            ogreView = new OgreView();
            coinView = new CoinView();
            healthPotionView = new HealthPotionView();
            swordView = new SwordView();
            bulletView = new BulletView();
            mummyView = new MummyView();
            guardView = new GuardView();
        }

        public void setHeroPos(Position heroPos) {
            this.heroPos = heroPos;
            wallView.setHeroPos(heroPos);
            heroView.setHeroPos(heroPos);
            ghostView.setHeroPos(heroPos);
            ogreView.setHeroPos(heroPos);
            coinView.setHeroPos(heroPos);
            healthPotionView.setHeroPos(heroPos);
            swordView.setHeroPos(heroPos);
            bulletView.setHeroPos(heroPos);
            mummyView.setHeroPos(heroPos);
            guardView.setHeroPos(heroPos);
        }

        public ElementView factoryMethod(Element e){
            ElementView res = null;
            if     (e instanceof Wall) res = wallView;
            else if(e instanceof Hero) res = heroView;
            else if(e instanceof Ghost) res = ghostView;
            else if(e instanceof Ogre) res = ogreView;
            else if(e instanceof Coin) res = coinView;
            else if(e instanceof HealthPotion) res = healthPotionView;
            else if(e instanceof Sword) res = swordView;
            else if(e instanceof Bullet) res = bulletView;
            else if(e instanceof Mummy) res = mummyView;
            else if(e instanceof Guard) res = guardView;
            return res;
        }
    }
    
    final Map<Class, Integer> drawPriority = Map.of(
            Hero .class, 5,
            Ogre.class , 4,
            Mummy.class, 4,
            Guard.class, 4,
            Ghost.class, 2,
            Wall .class, 1,
            HealthPotion.class, 0,
            Coin .class, 0,
            Sword.class, 0,
            Bullet.class, 0
    );
    
    private TerminalGUI terminalGUI;
    private ElementViewFactory elementViewFactory;
    private InfoBar infoBar;
    private TerminalFont font;
    
    public TerminalArenaView(TerminalGUI terminalGUI, TerminalFont font) throws FileNotFoundException{
        super(terminalGUI);
        this.terminalGUI = terminalGUI;
        this.font = font;
        elementViewFactory = new ElementViewFactory();
        infoBar = new InfoBar();
    }

    @Override
    public void start() {
        infoBar.startTime();
    }
    
    @Override
    public void draw() {
        ArenaModel arena = getArenaModel();
        
        List<Element> listElements = arena.getElements();
        
        Map<Position, Element> drawBuffer = new HashMap<>();
        for(final Element e : listElements){
            final Element e_ = drawBuffer.get(e.getPos());
            if(e_ == null || drawPriority.get(e.getClass()) > drawPriority.get(e_.getClass())) drawBuffer.put(e.getPos(), e);
        }

        elementViewFactory.setHeroPos(arena.getHero().getPos());
        for(final Element e : drawBuffer.values()){
            ElementView elementView = elementViewFactory.factoryMethod(e);
            if(elementView != null) elementView.draw(e);
        }
        
        infoBar.draw(arena);
    }
}
