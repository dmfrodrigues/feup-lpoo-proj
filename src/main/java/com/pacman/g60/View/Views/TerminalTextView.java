/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.View.Views;

import com.pacman.g60.Model.Models.TextModel;
import com.pacman.g60.View.Color;
import com.pacman.g60.View.Font.TerminalFont;
import com.pacman.g60.View.GUI.TerminalGUI;

public class TerminalTextView extends TextView {
    TerminalGUI terminalGUI;
    TerminalFont font;
    
    public TerminalTextView(TerminalTextView terminalTextView){
        super(terminalTextView.terminalGUI);
        terminalGUI = terminalTextView.terminalGUI;
        font = terminalTextView.font;
    }
    
    public TerminalTextView(TerminalGUI terminalGUI, TerminalFont font){
        super(terminalGUI);
        this.terminalGUI = terminalGUI;
        this.font = font;
    }
    public Integer getStringWidth(String s){
        int res = 0;
        int lineWidth = 0;
        for(int i = 0; i < s.length(); ++i){
            if(s.charAt(i) == '\n'){ res = Math.max(res, lineWidth); lineWidth = 0; }
            else ++lineWidth;
        }
        res = Math.max(res, lineWidth);
        return res*font.getW();
    }
    public Integer getStringHeight(String s){
        Integer numLines = 1;
        for(int i = 0; i < s.length(); ++i){
            if(s.charAt(i) == '\n') ++numLines;
        }
        return numLines * font.getH();
    }
    private void drawChar(int x0, int y0, Character c, Color f, Color b){
        TerminalFont.TerminalCharacter tchar = font.getCharacter(c);
        if(tchar == null) throw new NullPointerException("No char for '" + c + "'");
        Integer W = tchar.getW();
        Integer H = tchar.getH();
        for(int x = 0; x < W; ++x){
            for(int y = 0; y < H; ++y){
                terminalGUI.drawCharacter(x0+x, y0+y, tchar.getChar(x, y), f, b);
            }
        }
    }
    public void draw(){
        TextModel model = getTextModel();
        if(model == null) return;
        int x0, y0;
        if(model.getUnit() == TextModel.UNIT.ABSOLUTE) {
            x0 = (int) model.getPosition().getX();
            y0 = (int) model.getPosition().getY();
        } else {
            x0 = (int)(model.getPosition().getX() * terminalGUI.getW());
            y0 = (int)(model.getPosition().getY() * terminalGUI.getH());
        }
        switch(model.getHorizontalAlign()){
            case LEFT  : break;
            case CENTER: x0 -= getStringWidth(model.getText())/2; break;
            case RIGHT : x0 -= getStringWidth(model.getText())  ; break;
        }
        switch(model.getVerticalAlign()){
            case TOP   : break;
            case CENTER: y0 -= getStringHeight(model.getText())/2; break;
            case BOTTOM: y0 -= getStringHeight(model.getText())  ; break;
        }
        int i_beginline = 0;
        for(int i = 0; i < model.getText().length(); ++i){
            char c = model.getText().charAt(i);
            if(c == '\n') {
                y0 += Math.round(font.getH() * model.getLineHeight());
                i_beginline = i+1;
            } else drawChar(x0+(i-i_beginline)*font.getW(), y0, model.getText().charAt(i), model.getForegroundColor(), model.getBackgroundColor());
        }
    }

    @Override
    public TerminalTextView clone() {
        return new TerminalTextView(this);
    }
}
