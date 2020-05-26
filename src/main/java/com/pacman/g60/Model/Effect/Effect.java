package com.pacman.g60.Model.Effect;

import com.pacman.g60.Model.Elements.Element;

;

public interface Effect extends Cloneable{
    void apply(Element target);
    Object clone();
}
