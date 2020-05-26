package com.pacman.g60.Controller;

import com.pacman.g60.Model.MenuModel;
import com.pacman.g60.View.MenuView;

import java.io.IOException;

public class MenuController {
    private MenuModel menuModel;
    private MenuView menuView;
    
    public MenuController(MenuModel menuModel, MenuView menuView){
        this.menuModel = menuModel;
        this.menuView = menuView;
    }
    
    public int run() throws IOException {
        while(true){
            MenuView.COMMAND cmd = menuView.pollCommand();
            if(!(cmd == null)) {
                switch (cmd) {
                    case EXIT:
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
            menuView.draw(menuModel);
        }
    }
}
