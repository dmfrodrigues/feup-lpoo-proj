/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Model.Models;

import com.pacman.g60.Model.Position;
import com.pacman.g60.Model.PositionReal;
import com.pacman.g60.View.Color;

public class TextModel extends Alignable {

    public enum UNIT { ABSOLUTE, RELATIVE }
    
    private String text;
    double relativeFontSize = 1.0;
    private Color f = Color.WHITE, b = Color.BLACK;
    private PositionReal position = new PositionReal(0, 0);
    private UNIT unit = UNIT.ABSOLUTE;
    private double lineHeight = 1;
    
    public TextModel(String text) {
        this.text = text;
    }
    public void setText(String text){ this.text = text; }
    public String getText(){ return text; }

    public void setRelativeFontSize(double relativeFontSize){ this.relativeFontSize = relativeFontSize; }
    public double getRelativeFontSize(){ return relativeFontSize; }

    public final void setForegroundColor(Color f){ this.f = f; }
    public final Color getForegroundColor(){ return f; }
    public final void setBackgroundColor(Color b){ this.b = b; }
    public final Color getBackgroundColor(){ return b; }

    public void setPosition(Position position){ this.position = new PositionReal(position); unit = UNIT.ABSOLUTE; }
    public void setPosition(PositionReal position){ this.position = position; unit = UNIT.RELATIVE; }
    public PositionReal getPosition(){ return position; }
    public UNIT getUnit(){ return unit; }

    public void setLineHeight(double lineHeight) { this.lineHeight = lineHeight; }
    public double getLineHeight(){ return lineHeight; }
}
