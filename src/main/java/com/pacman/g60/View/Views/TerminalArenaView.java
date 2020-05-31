package com.pacman.g60.View.Views;


import com.googlecode.lanterna.terminal.Terminal;
import com.pacman.g60.Model.*;
import com.pacman.g60.Model.Elements.*;

import com.pacman.g60.Model.Elements.Hierarchy.OrientedElement;
import com.pacman.g60.Model.Models.Alignable;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Models.SpriteModel;
import com.pacman.g60.Model.Models.TextModel;
import com.pacman.g60.View.*;
import com.pacman.g60.View.Font.TerminalFont;
import com.pacman.g60.View.GUI.TerminalGUI;
import com.pacman.g60.View.Sprite.TerminalSprite;
import com.pacman.g60.View.Sprite.TerminalSpriteLoaderStream;
import com.pacman.g60.View.Sprite.TerminalSpriteOrientable;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerminalArenaView extends ArenaView {

    private static final Integer HInfoBar = 5;

    private class InfoBar {
        private final Integer Wmargin = 2;
        
        private Duration time = Duration.ZERO;
        private final GUIViewComposite view;
        
        private final TerminalSprite heartSprite;
        private final TerminalSprite heartDeadSprite;

        private final TextModel textModelTimer;

        private final TerminalSprite damageSprite;
        private final SpriteModel damageModel;
        private final TextModel textModelDamage;
        private final TerminalTextView textViewDamage;

        private final TerminalSprite bulletSprite;
        private final SpriteModel bulletModel;
        private final TextModel textModelBullet;
        private final TerminalTextView textViewBullet;
        
        private final TerminalSprite coinSprite;
        private final SpriteModel coinModel;
        private final TextModel textModelCoin;
        private final TerminalTextView textViewCoin;
        
        public InfoBar() throws FileNotFoundException{
            TerminalSprite.Loader loader;
            // Hearts
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/heart-6-3.lan"));
            heartSprite = loader.getTerminalSprite();
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/heart-dead-6-3.lan"));
            heartDeadSprite = loader.getTerminalSprite();
            // Timer
            textModelTimer = new TextModel("");
            textModelTimer.setVerticalAlign(Alignable.VerticalAlign.TOP);
            textModelTimer.setHorizontalAlign(Alignable.HorizontalAlign.CENTER);
            TerminalTextView textViewTimer = new TerminalTextView(terminalGUI, font);
            textViewTimer.setTextModel(textModelTimer);

            // Damage
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/sword-6-3.lan"));
            damageSprite = loader.getTerminalSprite();
            SpriteView damageView = new TerminalSpriteView(terminalGUI);
            damageModel = new SpriteModel(damageSprite);
            damageModel.setVerticalAlign(Alignable.VerticalAlign.TOP);
            damageModel.setHorizontalAlign(Alignable.HorizontalAlign.RIGHT);
            damageView.setSpriteModel(damageModel);

            textModelDamage = new TextModel("");
            textModelDamage.setVerticalAlign(Alignable.VerticalAlign.TOP);
            textModelDamage.setHorizontalAlign(Alignable.HorizontalAlign.LEFT);
            textViewDamage = new TerminalTextView(terminalGUI, font);
            textViewDamage.setTextModel(textModelDamage);
            // Bullet
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/bullet-6-3.lan"));
            bulletSprite = loader.getTerminalSprite();
            SpriteView bulletView = new TerminalSpriteView(terminalGUI);
            bulletModel = new SpriteModel(bulletSprite);
            bulletModel.setVerticalAlign(Alignable.VerticalAlign.TOP);
            bulletModel.setHorizontalAlign(Alignable.HorizontalAlign.RIGHT);
            bulletView.setSpriteModel(bulletModel);

            textModelBullet = new TextModel("");
            textModelBullet.setVerticalAlign(Alignable.VerticalAlign.TOP);
            textModelBullet.setHorizontalAlign(Alignable.HorizontalAlign.LEFT);
            textViewBullet = new TerminalTextView(terminalGUI, font);
            textViewBullet.setTextModel(textModelBullet);
            // Coin
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/coin-6-3.lan"));
            coinSprite = loader.getTerminalSprite();
            SpriteView coinView = new TerminalSpriteView(terminalGUI);
            coinModel = new SpriteModel(coinSprite);
            coinModel.setVerticalAlign(Alignable.VerticalAlign.TOP);
            coinModel.setHorizontalAlign(Alignable.HorizontalAlign.RIGHT);
            coinView.setSpriteModel(coinModel);

            textModelCoin = new TextModel("");
            textModelCoin.setVerticalAlign(Alignable.VerticalAlign.TOP);
            textModelCoin.setHorizontalAlign(Alignable.HorizontalAlign.RIGHT);
            textViewCoin = new TerminalTextView(terminalGUI, font);
            textViewCoin.setTextModel(textModelCoin);
            // View
            view = new GUIViewComposite(terminalGUI);
            view.addView(textViewTimer);
            view.addView(textViewBullet);
            view.addView(textViewDamage);
            view.addView(damageView);
            view.addView(bulletView);
            view.addView(textViewCoin);
            view.addView(coinView);
        }
        
        public void setTime(Duration time) { this.time = time; }
        
        private void drawFrame(){
            for(int x = 1; x < terminalGUI.getW()-1; ++x){
                terminalGUI.drawCharacter(x, 0, '▀', Color.GREY, Color.BLACK);
                terminalGUI.drawCharacter(x, HInfoBar-1, '▄', Color.GREY, Color.BLACK);
            }
            for(int y = 1; y < HInfoBar-1; ++y){
                terminalGUI.drawCharacter(0, y, '█', Color.GREY, Color.BLACK);
                terminalGUI.drawCharacter(terminalGUI.getW()-1, y, '█', Color.GREY, Color.BLACK);
            }
            for(int x = 1; x < terminalGUI.getW()-1; ++x){
                for(int y = 1; y < HInfoBar-1; ++y){
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
                    TerminalSprite sprite = (x < health*heartSprite.getW() ? heartSprite : heartDeadSprite);
                    terminalGUI.drawCharacter(x0 + x, y0 + y,
                            sprite.getChar(x % sprite.getW(), y),
                            sprite.getForegroundColor(x % sprite.getW(), y),
                            sprite.getBackgroundColor(x % sprite.getW(), y));
                }
            }
        }
        private void updateTimer(){
            long sec = time.getSeconds()%60;
            long min = time.getSeconds()/60;
            textModelTimer.setText(String.format("%2d:%02d", min, sec));
            textModelTimer.setPosition(new Position(terminalGUI.getW()/2, 1+coinSprite.getH()-textViewCoin.getStringHeight(textModelTimer.getText())));
        }
        private void updateDamage(Integer damage){
            textModelDamage.setText(damage.toString());
            Position pos = new Position((int)(0.75*terminalGUI.getW()), 1);
            textModelDamage.setPosition(pos);
            damageModel.setPosition(pos);
        }
        private void updateBullet(Integer ammo){
            textModelBullet.setText(ammo.toString());
            Position pos = new Position((int) (0.85*terminalGUI.getW()), 1);
            textModelBullet.setPosition(pos);
            bulletModel.setPosition(pos);
        }
        private void updateCoins(int coins, int totalCoins){
            textModelCoin.setText(String.format("%d/%d", coins, totalCoins));
            textModelCoin.setPosition(new Position(terminalGUI.getW()-1, 1));
            coinModel.setPosition(new Position(
                    terminalGUI.getW()-1-textViewCoin.getStringWidth(textModelCoin.getText()),
                    1
            ));
        }

        public void draw(ArenaModel arenaModel){
            drawFrame();
            drawHealth(arenaModel.getHero().getHealth(), arenaModel.getHero().getMaxHealth());
            updateTimer();
            updateBullet(arenaModel.getHero().getAmmo());
            updateDamage(arenaModel.getHero().getWeapon().getEffect().getDamage());
            updateCoins(arenaModel.getHero().getCoins(), arenaModel.getNumCoins() + arenaModel.getHero().getCoins());
            view.draw();
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
        TerminalSpriteOrientable spriteOrientable;

        public MummyView() throws FileNotFoundException
        {
            TerminalSprite.Loader loader;
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/mummy-8-4-right.lan"));
            spriteOrientable = new TerminalSpriteOrientable(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/mummy-8-4-left.lan"));
            spriteOrientable.setSpriteLeft(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/mummy-8-4-up.lan"));
            spriteOrientable.setSpriteUp(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/mummy-8-4-down.lan"));
            spriteOrientable.setSpriteDown(loader.getTerminalSprite());
        }

        @Override
        protected TerminalSprite getSprite(Element e) {
            return spriteOrientable.getSprite(((DynamicElement) e).getDirection());
        }
    }

    private class GuardView extends ElementView
    {
        TerminalSpriteOrientable spriteOrientable;

        public GuardView() throws FileNotFoundException
        {
            TerminalSprite.Loader loader;
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/guard-8-4-right.lan"));
            spriteOrientable = new TerminalSpriteOrientable(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/guard-8-4-left.lan"));
            spriteOrientable.setSpriteLeft(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/guard-8-4-up.lan"));
            spriteOrientable.setSpriteUp(loader.getTerminalSprite());
            loader = new TerminalSpriteLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/guard-8-4-down.lan"));
            spriteOrientable.setSpriteDown(loader.getTerminalSprite());
        }

        @Override
        protected TerminalSprite getSprite(Element e) {
            return spriteOrientable.getSprite(((DynamicElement) e).getDirection());
        }
    }
    
    private class ElementViewFactory {
        private final WallView wallView;
        private final HeroView heroView;
        private final GhostView ghostView;
        private final OgreView ogreView;
        private final CoinView coinView;
        private final HealthPotionView healthPotionView;
        private final SwordView swordView;
        private final BulletView bulletView;
        private final MummyView mummyView;
        private final GuardView guardView;

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
    
    private final TerminalGUI terminalGUI;
    private final ElementViewFactory elementViewFactory;
    private final InfoBar infoBar;
    private final TerminalFont font;
    
    public TerminalArenaView(TerminalGUI terminalGUI, TerminalFont font) throws FileNotFoundException{
        super(terminalGUI);
        this.terminalGUI = terminalGUI;
        this.font = font;
        elementViewFactory = new ElementViewFactory();
        infoBar = new InfoBar();
    }

    @Override
    public void setTime(Duration time) {
        infoBar.setTime(time);
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
