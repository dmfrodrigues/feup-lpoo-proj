package com.pacman.g60.View.Views;

import com.pacman.g60.Model.Models.SpriteModel;
import com.pacman.g60.View.GUI.TerminalGUI;
import com.pacman.g60.View.Sprite.TerminalSprite;

public class TerminalSpriteView extends SpriteView {
    
    private final TerminalGUI terminalGUI;
    
    public TerminalSpriteView(TerminalSpriteView terminalSpriteView){
        super(terminalSpriteView.terminalGUI);
        terminalGUI = terminalSpriteView.terminalGUI;
    }
    
    public TerminalSpriteView(TerminalGUI terminalGUI){
        super(terminalGUI);
        this.terminalGUI = terminalGUI;
    }

    @Override
    public void draw() {
        SpriteModel model = getSpriteModel();
        TerminalSprite sprite = model.getSprite();
        int x0, y0;
        if(model.getUnit() == SpriteModel.UNIT.ABSOLUTE) {
            x0 = (int) model.getPosition().getX();
            y0 = (int) model.getPosition().getY();
        } else {
            x0 = (int)(model.getPosition().getX() * terminalGUI.getW());
            y0 = (int)(model.getPosition().getY() * terminalGUI.getH());
        }
        switch(model.getHorizontalAlign()){
            case LEFT  : break;
            case CENTER: x0 -= sprite.getW()/2; break;
            case RIGHT : x0 -= sprite.getW()  ; break;
        }
        switch(model.getVerticalAlign()){
            case TOP   : break;
            case CENTER: y0 -= sprite.getH()/2; break;
            case BOTTOM: y0 -= sprite.getH()  ; break;
        }
        for(int x = 0; x < sprite.getW(); ++x) {
            for (int y = 0; y < sprite.getH(); ++y) {
                terminalGUI.drawCharacter(x0 + x, y0 + y,
                        sprite.getChar(x, y),
                        sprite.getForegroundColor(x, y),
                        sprite.getBackgroundColor(x, y));
            }
        }
    }

    @Override
    public TerminalSpriteView clone() { return new TerminalSpriteView(this); }
}
