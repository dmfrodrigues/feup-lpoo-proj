package com.pacman.g60.View;



import com.pacman.g60.Model.ArenaModel;

import java.io.IOException;

public interface ArenaView {
    enum COMMAND {UP, DOWN, LEFT, RIGHT, ATTACK, EXIT, PAUSE}
    void start();
    void draw(ArenaModel arena) throws IOException;
    COMMAND pollCommand() throws IOException;
}
