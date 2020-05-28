package com.pacman.g60.View;

public class TerminalTextView{
    TerminalGUI terminalGUI;
    TerminalFont font;
    public TerminalTextView(TerminalGUI terminalGUI, TerminalFont font){
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
    public void draw(int x0, int y0, String s, Color f, Color b){
        for(int i = 0; i < s.length(); ++i){
            drawChar(x0+i*font.getW(), y0, s.charAt(i), f, b);
        }
    }
    public void draw(int x0, int y0, String s, Color f){ draw(x0, y0, s, f, Color.BLACK); }
    public void draw(int x0, int y0, String s){ draw(x0, y0, s, Color.WHITE); }
}
