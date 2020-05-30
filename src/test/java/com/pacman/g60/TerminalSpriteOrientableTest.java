package com.pacman.g60;

import com.pacman.g60.View.Sprite.TerminalSprite;
import com.pacman.g60.View.Sprite.TerminalSpriteOrientable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TerminalSpriteOrientableTest {
    
    public class TerminalSpriteDesc extends TerminalSprite {
        private final Integer id;

        public TerminalSpriteDesc(Integer id){ 
            super(0, 0);
            this.id = id;
        }

        public Integer getId() {
            return id;
        }
    }
    
    @Test
    public void ctor(){
        
        try{
            new TerminalSpriteOrientable(null);
            fail();
        } catch(Exception e){
            assertEquals(NullPointerException.class, e.getClass());
        }
    }
    
    @Test
    public void getSprite(){
        Integer upId    = 0; TerminalSprite up    = new TerminalSpriteDesc(upId   );
        Integer downId  = 1; TerminalSprite down  = new TerminalSpriteDesc(downId );
        Integer leftId  = 2; TerminalSprite left  = new TerminalSpriteDesc(leftId );
        Integer rightId = 3; TerminalSprite right = new TerminalSpriteDesc(rightId);
        TerminalSpriteOrientable spriteOrientable = new TerminalSpriteOrientable(right);
        assertEquals(rightId, ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.UP)).getId());
        assertEquals(rightId, ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.DOWN)).getId());
        assertEquals(rightId, ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.LEFT)).getId());
        assertEquals(rightId, ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.RIGHT)).getId());
        assertEquals(rightId, ((TerminalSpriteDesc)spriteOrientable.getSprite(null)).getId());
        spriteOrientable.setSpriteLeft(left);
        assertEquals(rightId, ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.UP)).getId());
        assertEquals(rightId, ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.DOWN)).getId());
        assertEquals(leftId , ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.LEFT)).getId());
        assertEquals(rightId, ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.RIGHT)).getId());
        assertEquals(rightId, ((TerminalSpriteDesc)spriteOrientable.getSprite(null)).getId());
        spriteOrientable.setSpriteUp(up);
        assertEquals(upId   , ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.UP)).getId());
        assertEquals(rightId, ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.DOWN)).getId());
        assertEquals(leftId , ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.LEFT)).getId());
        assertEquals(rightId, ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.RIGHT)).getId());
        assertEquals(rightId, ((TerminalSpriteDesc)spriteOrientable.getSprite(null)).getId());
        spriteOrientable.setSpriteDown(down);
        assertEquals(upId   , ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.UP)).getId());
        assertEquals(downId , ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.DOWN)).getId());
        assertEquals(leftId , ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.LEFT)).getId());
        assertEquals(rightId, ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.RIGHT)).getId());
        assertEquals(rightId, ((TerminalSpriteDesc)spriteOrientable.getSprite(null)).getId());
        spriteOrientable.setSpriteRight(down);
        assertEquals(upId   , ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.UP)).getId());
        assertEquals(downId , ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.DOWN)).getId());
        assertEquals(leftId , ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.LEFT)).getId());
        assertEquals(downId , ((TerminalSpriteDesc)spriteOrientable.getSprite(Application.Direction.RIGHT)).getId());
        assertEquals(downId , ((TerminalSpriteDesc)spriteOrientable.getSprite(null)).getId());
    }
}
