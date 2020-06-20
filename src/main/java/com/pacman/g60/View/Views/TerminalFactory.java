/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.View.Views;

import com.pacman.g60.View.Font.TerminalFont;
import com.pacman.g60.View.Font.TerminalFontLoaderStream;
import com.pacman.g60.View.GUI.TerminalGUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TerminalFactory implements ViewFactory {
    private final TerminalGUI terminalGUI;
    private final TerminalFont font;
    
    public TerminalFactory(TerminalGUI terminalGUI) throws FileNotFoundException {
        this.terminalGUI = terminalGUI;
        TerminalFont.Loader loader = new TerminalFontLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/monospace-4-3.font"));
        font = loader.getTerminalFont();
    }
    
    @Override
    public TerminalArenaView createArenaView() throws FileNotFoundException { return new TerminalArenaView(terminalGUI, font); }

    @Override
    public MenuView createMenuView() {
        return new TerminalMenuView(terminalGUI, font);
    }

    @Override
    public TextView createTextView() {
        return new TerminalTextView(terminalGUI, font);
    }

    @Override
    public SpriteView createSpriteView() { return new TerminalSpriteView(terminalGUI); }
}
