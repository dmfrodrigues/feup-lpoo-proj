/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.View.GUI;

import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public interface GUI {
    void clear();
    void refresh() throws IOException;
    KeyStroke pollKey() throws IOException;
    void close() throws IOException;
}
