package com.pacman.g60.View.Views;

import com.pacman.g60.Model.Models.TextModel;
import com.pacman.g60.View.Color;
import com.pacman.g60.View.Font.TerminalFont;
import com.pacman.g60.View.GUI.TerminalGUI;

public class TerminalTextView extends TextView {
    TerminalGUI terminalGUI;
    TerminalFont font;
    public TerminalTextView(TerminalGUI terminalGUI, TerminalFont font){
        super(terminalGUI);
        this.terminalGUI = terminalGUI;
        this.font = font;
    }
    public Integer getStringWidth(String s){
        Integer res = 0;
        Integer lineWidth = 0;
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
        TextModel text = getTextModel();
        if(text == null) return;
        int x0, y0;
        if(text.getUnit() == TextModel.UNIT.ABSOLUTE) {
            x0 = (int) text.getPosition().getX();
            y0 = (int) text.getPosition().getY();
        } else {
            x0 = (int)(text.getPosition().getX() * terminalGUI.getW());
            y0 = (int)(text.getPosition().getY() * terminalGUI.getH());
        }
        switch(text.getHorizontalAlign()){
            case LEFT  : break;
            case CENTER: x0 -= getStringWidth(text.getText())/2; break;
            case RIGHT : x0 -= getStringWidth(text.getText())  ; break;
        }
        switch(text.getVerticalAlign()){
            case TOP  : break;
            case CENTER: y0 -= getStringHeight(text.getText())/2; break;
            case BOTTOM : y0 -= getStringHeight(text.getText())  ; break;
        }
        int i_beginline = 0;
        for(int i = 0; i < text.getText().length(); ++i){
            Character c = text.getText().charAt(i);
            if(c == '\n') {
                y0 += Math.round(font.getH() * text.getLineHeight());
                i_beginline = i+1;
            } else drawChar(x0+(i-i_beginline)*font.getW(), y0, text.getText().charAt(i), text.getForegroundColor(), text.getBackgroundColor());
        }
    }
}
