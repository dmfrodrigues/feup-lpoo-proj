package com.pacman.g60.View.Views;

import com.pacman.g60.Model.Models.TextModel;
import com.pacman.g60.View.GUI.GUI;

import java.lang.reflect.InvocationTargetException;

public abstract class TextView extends GUIView {
    TextModel textModel = null;

    public TextView(TextView textView){
        super(textView.getGUI());
        setTextModel(textView.getTextModel());
    }
    
    public TextView(GUI gui){ super(gui); }

    public final void setTextModel(TextModel textModel){ this.textModel = textModel; }
    public final TextModel getTextModel(){ return textModel; }

    @Override
    public TextView clone() {
        System.out.println("Called TextView.clone()");
        try {
            return getClass().getDeclaredConstructor(TextView.class).newInstance(this);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
