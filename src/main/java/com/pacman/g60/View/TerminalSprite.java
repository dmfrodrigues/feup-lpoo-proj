package com.pacman.g60.View;


public class TerminalSprite {
    private final Integer W, H;
    private char[][] chars = null;
    private Color[][] back = null;
    private Color[][] fore = null;
    public TerminalSprite(Integer W, Integer H){
        this.W = W;
        this.H = H;
        chars = new char [H][W];
        back  = new Color[H][W];
        fore  = new Color[H][W];
    }
    public void  setChar           (Integer x, Integer y, char  c){ chars[y][x] = c; }
    public void  setBackgroundColor(Integer x, Integer y, Color c){ back [y][x] = c; }
    public void  setForegroundColor(Integer x, Integer y, Color c){ fore [y][x] = c; }
    public char  getChar           (Integer x, Integer y){ return chars[y][x]; }
    public Color getBackgroundColor(Integer x, Integer y){ return back [y][x]; }
    public Color getForegroundColor(Integer x, Integer y){ return fore [y][x]; }

    public Integer getW() { return W; }

    public Integer getH() { return H; }

    public interface Loader {
        TerminalSprite getTerminalSprite();
    }
}
