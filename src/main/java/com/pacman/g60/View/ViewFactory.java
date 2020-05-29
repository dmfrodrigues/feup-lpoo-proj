package com.pacman.g60.View;

import java.io.FileNotFoundException;

public interface ViewFactory {
    ArenaView createArenaView() throws FileNotFoundException;
    MenuView createMenuView();
    TextView createTextView();
}
