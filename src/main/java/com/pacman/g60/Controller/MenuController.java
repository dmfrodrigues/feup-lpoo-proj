package com.pacman.g60.Controller;

import com.pacman.g60.Model.Models.MenuModel;
import com.pacman.g60.View.Views.GUIView;

import java.io.IOException;

public class MenuController {
    private MenuModel menuModel;
    private GUIView view;
    
    public MenuController(MenuModel menuModel, GUIView view){
        this.menuModel = menuModel;
        this.view = view;
    }
    
    public int run() throws IOException {
        while(true){
            GUIView.COMMAND cmd = view.pollCommand();
            if(!(cmd == null)) {
                switch (cmd) {
                    case ESC:
                        return -1;
                    case UP:
                        menuModel.selectAbove();
                        break;
                    case DOWN:
                        menuModel.selectBelow();
                        break;
                    case ENTER:
                        return menuModel.getSelectedItem().getId();
                }
            }
            view.clear();
            view.draw();
            view.refresh();
        }
    }
}
