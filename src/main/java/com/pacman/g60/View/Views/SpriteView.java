package com.pacman.g60.View.Views;

import com.pacman.g60.Model.Models.SpriteModel;
import com.pacman.g60.View.GUI.GUI;

public abstract class SpriteView extends GUIView {
    SpriteModel spriteModel;
    
    public SpriteView(GUI gui){ super(gui); }
    
    public final void setSpriteModel(SpriteModel spriteModel){ this.spriteModel = spriteModel; }
    public final SpriteModel getSpriteModel(){ return spriteModel; }
}
