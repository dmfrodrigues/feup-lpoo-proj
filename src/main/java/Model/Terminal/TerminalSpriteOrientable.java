package Model.Terminal;

import Model.Application;

import java.io.InputStream;

public class TerminalSpriteOrientable{
    private TerminalSprite spriteRight, spriteLeft, spriteUp, spriteDown;
    public TerminalSpriteOrientable(TerminalSprite sprite) throws IllegalArgumentException{
        if(sprite == null) throw new IllegalArgumentException("Null sprite not allowed");
        spriteRight = sprite;
    }
    public void setSpriteRight(TerminalSprite sprite){ this.spriteRight = sprite; }
    public void setSpriteLeft(TerminalSprite sprite){ this.spriteLeft = sprite; }
    public void setSpriteUp(TerminalSprite sprite){ this.spriteUp = sprite; }
    public void setSpriteDown(TerminalSprite sprite){ this.spriteDown = sprite; }
    public TerminalSprite getSprite(Application.Direction dir){
        TerminalSprite res = null;
        switch(dir) {
            case UP   : res = spriteUp   ; break;
            case DOWN : res = spriteDown ; break;
            case LEFT : res = spriteLeft ; break;
            case RIGHT: res = spriteRight; break;
        }
        return (res != null ? res : spriteRight);
    }
}
