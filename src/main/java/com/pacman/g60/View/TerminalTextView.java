package com.pacman.g60.View;

public class TerminalTextView{
    TerminalGUI terminalGUI;
    TerminalFont font;
    public TerminalTextView(TerminalGUI terminalGUI, TerminalFont font){
        this.terminalGUI = terminalGUI;
        this.font = font;
    }
    public Integer getStringWidth(String s){ return s.length()*(font.getW()+1)-1; }
    public Integer getStringHeight(String s){ return font.getH(); }
    private void drawChar(int x0, int y0, Character c){
        TerminalFont.TerminalCharacter tchar = font.getCharacter(c);
        Integer W = tchar.getW();
        Integer H = tchar.getH();
        for(int x = 0; x < W; ++x){
            for(int y = 0; y < H; ++y){
                terminalGUI.drawCharacter(x0+x, y0+y, tchar.getChar(x, y), Color.WHITE, Color.BLACK);
            }
        }
    }
    public void draw(int x0, int y0, String s){
        for(int i = 0; i < s.length(); ++i){
            drawChar(x0+i*(font.getW()+1), y0, s.charAt(i));
        }
    }
}
