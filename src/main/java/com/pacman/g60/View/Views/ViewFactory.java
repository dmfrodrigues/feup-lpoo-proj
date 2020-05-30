package com.pacman.g60.View.Views;

import com.pacman.g60.View.Views.ArenaView;
import com.pacman.g60.View.Views.MenuView;
import com.pacman.g60.View.Views.TextView;

import java.io.FileNotFoundException;

public interface ViewFactory {
    ArenaView createArenaView() throws FileNotFoundException;
    MenuView createMenuView();
    TextView createTextView();
    SpriteView createSpriteView();
}
