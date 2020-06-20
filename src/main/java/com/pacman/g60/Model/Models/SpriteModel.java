/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Model.Models;

import com.pacman.g60.Model.Position;
import com.pacman.g60.Model.PositionReal;
import com.pacman.g60.View.Sprite.TerminalSprite;

public class SpriteModel extends Alignable {

    public enum UNIT { ABSOLUTE, RELATIVE }
    
    TerminalSprite sprite;
    private PositionReal position = new PositionReal(0, 0);
    private UNIT unit = UNIT.ABSOLUTE;
    
    public SpriteModel(TerminalSprite sprite){ this.sprite = sprite; }
    public TerminalSprite getSprite(){ return sprite; }

    public void setPosition(Position position){ this.position = new PositionReal(position); unit = SpriteModel.UNIT.ABSOLUTE; }
    public void setPosition(PositionReal position){ this.position = position; unit = SpriteModel.UNIT.RELATIVE; }
    public PositionReal getPosition(){ return position; }
    public SpriteModel.UNIT getUnit(){ return unit; }
}
