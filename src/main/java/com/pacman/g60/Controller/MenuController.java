package com.pacman.g60.Controller;

import com.pacman.g60.Model.Models.MenuModel;
import com.pacman.g60.View.FrameRateController;
import com.pacman.g60.View.Views.GUIView;

import java.io.IOException;

public class MenuController {
    private final MenuModel menuModel;
    private final GUIView view;
    private final FrameRateController  frameRateController  = new FrameRateController (60);
    public MenuController(MenuModel menuModel, GUIView view){
        this.menuModel = menuModel;
        this.view = view;
    }
    
    public int run() throws IOException {
        frameRateController.start();
        while(true){
            frameRateController.startFrame();
            
            while(true){
                GUIView.COMMAND cmd = view.pollCommand();
                if(cmd == null) break;
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
                        if(menuModel.getSelectedItem().getEnabled())
                            return menuModel.getSelectedItem().getId();
                        break;
                }
            }
            
            view.clear();
            view.draw();
            view.refresh();
        }
    }
}
