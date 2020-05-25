package com.pacman.g60.View;

import com.pacman.g60.Model.MenuModel;

import java.io.IOException;

public interface MenuView {
    enum COMMAND {UP, DOWN, ENTER, EXIT}
    void draw(MenuModel menu) throws IOException;
    COMMAND pollCommand() throws IOException;
}
