package com.pacman.g60.Model.Models;

public abstract class Alignable {
    public enum VerticalAlign {
        TOP,
        CENTER,
        BOTTOM
    }
    public enum HorizontalAlign {
        LEFT,
        CENTER,
        RIGHT
    }
    
    VerticalAlign verticalAlign = VerticalAlign.TOP;
    HorizontalAlign horizontalAlign = HorizontalAlign.LEFT;

    public final void setVerticalAlign(VerticalAlign verticalAlign){ this.verticalAlign = verticalAlign; }
    public final VerticalAlign getVerticalAlign() { return verticalAlign; }

    public final void setHorizontalAlign(HorizontalAlign verticalAlign){ this.horizontalAlign = verticalAlign; }
    public final HorizontalAlign getHorizontalAlign() { return horizontalAlign; }
}
