/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.View.Views;

import com.pacman.g60.Model.Models.SpriteModel;
import com.pacman.g60.View.GUI.GUI;

import java.lang.reflect.InvocationTargetException;

public abstract class SpriteView extends GUIView {
    SpriteModel spriteModel;
    
    public SpriteView(SpriteView spriteView){
        super(spriteView.getGUI());
        setSpriteModel(spriteView.getSpriteModel());
    }
    
    public SpriteView(GUI gui){ super(gui); }
    
    public final void setSpriteModel(SpriteModel spriteModel){ this.spriteModel = spriteModel; }
    public final SpriteModel getSpriteModel(){ return spriteModel; }

    @Override
    public SpriteView clone() {
        try {
            return getClass().getDeclaredConstructor(SpriteView.class).newInstance(this);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
