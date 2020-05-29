package com.pacman.g60.View;

import com.pacman.g60.Model.TextModel;

public class TerminalTextView extends TextView{
    TerminalGUI terminalGUI;
    TerminalFont font;
    public TerminalTextView(TerminalGUI terminalGUI, TerminalFont font){
        super(terminalGUI);
        this.terminalGUI = terminalGUI;
        this.font = font;
    }
    public Integer getStringWidth(String s){ return s.length()*font.getW(); }
    public Integer getStringHeight(String s){ return font.getH(); }
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
        for(int i = 0; i < text.getText().length(); ++i){
            drawChar(x0+i*font.getW(), y0, text.getText().charAt(i), text.getForegroundColor(), text.getBackgroundColor());
        }
    }
}
