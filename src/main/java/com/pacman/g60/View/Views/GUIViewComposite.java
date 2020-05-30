package com.pacman.g60.View.Views;

import com.pacman.g60.View.GUI.GUI;
import com.pacman.g60.View.Views.GUIView;

import java.util.ArrayList;
import java.util.List;

public class GUIViewComposite extends GUIView {
    List<GUIView> views = new ArrayList<>();
    public GUIViewComposite(GUI gui){ super(gui); }
    
    public void addView(GUIView view){ views.add(view); }
    
    @Override
    public void draw() {
        for(final GUIView view: views) view.draw();
    }
}
