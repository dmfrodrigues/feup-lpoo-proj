import java.io.InputStream;

public class TerminalSpriteOrientable{
    private TerminalSprite spriteRight, spriteLeft, spriteUp, spriteDown;
    public TerminalSpriteOrientable(TerminalSprite sprite){
        spriteRight = sprite;
    }
    public void setSpriteRight(TerminalSprite sprite){ this.spriteRight = sprite; }
    public void setSpriteLeft(TerminalSprite sprite){ this.spriteLeft = sprite; }
    public void setSpriteUp(TerminalSprite sprite){ this.spriteUp = sprite; }
    public void setSpriteDown(TerminalSprite sprite){ this.spriteDown = sprite; }
    public TerminalSprite getSprite(Direction dir){
        String s = dir.getClass().toString();
        if(s.equals("class NorthDirection") && spriteUp    != null) return spriteUp;
        if(s.equals("class SouthDirection") && spriteDown  != null) return spriteDown;
        if(s.equals("class WestDirection" ) && spriteLeft  != null) return spriteLeft;
        if(s.equals("class EastDirection" ) && spriteRight != null) return spriteRight;
        return spriteRight;
    }
}
