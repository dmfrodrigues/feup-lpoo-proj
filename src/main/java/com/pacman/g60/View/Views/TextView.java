package com.pacman.g60.View.Views;

import com.pacman.g60.Model.Models.TextModel;
import com.pacman.g60.View.GUI.GUI;

public abstract class TextView extends GUIView {
    TextModel textModel = null;

    public TextView(GUI gui){ super(gui); }

    public final void setTextModel(TextModel textModel){ this.textModel = textModel; }
    public final TextModel getTextModel(){ return textModel; }
}
