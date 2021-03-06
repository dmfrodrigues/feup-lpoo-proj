/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.View.Font;

import java.util.HashMap;
import java.util.Map;

public class TerminalFont {
    public static class TerminalCharacter {
        private final Integer W, H;
        private final char[][] chars;
        public TerminalCharacter(Integer W, Integer H) {
            this.W = W;
            this.H = H;
            chars = new char[H][W];
        }
        public Integer getW(){ return W; }
        public Integer getH(){ return H; }
        public void setChar(Integer x, Integer y, char c) { chars[y][x] = c; }
        public char getChar(Integer x, Integer y) { return chars[y][x]; }
        
    }
    private final Integer W, H;
    private final Map<Character, TerminalCharacter> chars;
    public TerminalFont(Integer W, Integer H){
        this.W = W;
        this.H = H;
        chars = new HashMap<>();
    }
    public Integer getW(){ return W; }
    public Integer getH(){ return H; }
    public void setCharacter(char c, TerminalCharacter tchar){ chars.put(c, tchar); }
    public TerminalCharacter getCharacter(char c){ return chars.get(c); }
    public interface Loader { TerminalFont getTerminalFont(); }
}
