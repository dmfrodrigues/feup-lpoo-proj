package com.pacman.g60.View.Views;

import com.pacman.g60.Model.Models.MenuModel;
import com.pacman.g60.View.GUI.GUI;

public abstract class MenuView extends GUIView {
    MenuModel menuModel = null;
    
    public MenuView(GUI gui){ super(gui); }
    
    public final void setMenuModel(MenuModel menuModel){ this.menuModel = menuModel; }
    public final MenuModel getMenuModel(){ return menuModel; }
}
