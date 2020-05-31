package com.pacman.g60.View;

public class Color {
    int r, g, b;
    public Color(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }
    public Color(String hex) throws IllegalArgumentException {
        if(hex.length() != 7) throw new IllegalArgumentException("Argument has wrong length");
        if(hex.charAt(0) != '#') throw new IllegalArgumentException("Argument has missing initial '#'");
        this.r = Integer.parseInt(hex.substring(1, 3), 16);
        this.g = Integer.parseInt(hex.substring(3, 5), 16);
        this.b = Integer.parseInt(hex.substring(5, 7), 16);
    }
    public int getR(){ return r; }
    public int getG(){ return g; }
    public int getB(){ return b; }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj.getClass() != getClass()) return false;
        Color c = (Color)obj;
        return getR() == c.getR() && getG() == c.getG() && getB() == c.getB();
    }

    @Override
    public String toString() {
        return String.format("#%02X%02X%02X", r, g, b);
    }

    public static Color BLACK = new Color(0, 0, 0);
    public static Color WHITE = new Color(255, 255, 255);
    public static Color GREY  = new Color(127, 127, 127);
    public static Color LIGHT_GREY = new Color(191, 191, 191);
    public static Color DARK_GREY = new Color(64, 64, 64);
}
