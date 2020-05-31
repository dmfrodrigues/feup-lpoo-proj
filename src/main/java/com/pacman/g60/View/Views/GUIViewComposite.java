package com.pacman.g60.View.Views;

import com.pacman.g60.View.GUI.GUI;

import java.util.ArrayList;
import java.util.List;

public class GUIViewComposite extends GUIView {
    List<GUIView> children = new ArrayList<>();
    public GUIViewComposite(GUI gui){ super(gui); }
    
    public void addView(GUIView view){ children.add(view); }
    
    @Override
    public void draw() {
        for(final GUIView view: children) view.draw();
    }
}
