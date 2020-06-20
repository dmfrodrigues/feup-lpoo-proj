/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.View.Views;

import java.io.FileNotFoundException;

public interface ViewFactory {
    ArenaView createArenaView() throws FileNotFoundException;
    MenuView createMenuView();
    TextView createTextView();
    SpriteView createSpriteView();
}
