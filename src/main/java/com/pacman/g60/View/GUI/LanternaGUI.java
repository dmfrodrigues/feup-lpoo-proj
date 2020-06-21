/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.View.GUI;


import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import com.pacman.g60.View.Color;

import java.awt.*;
import java.io.EOFException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class LanternaGUI implements TerminalGUI {
    
    private final static Integer SECONDS_TO_MILLIS = 1000;
    
    private Screen screen;
    private static final String LANTERNA_FONT = "Monospaced";
    private static final int LANTERNA_FONT_SIZE = 10;
    public LanternaGUI(){
        // Initialize screen
        try{
            Font font = new Font(LANTERNA_FONT, Font.PLAIN, LANTERNA_FONT_SIZE);
            SwingTerminalFontConfiguration cfg = SwingTerminalFontConfiguration.newInstance(font);

            DefaultTerminalFactory factory = new DefaultTerminalFactory();
            factory.setInitialTerminalSize(new TerminalSize(320, 86));
            factory.setTerminalEmulatorFontConfiguration(cfg);
            Terminal terminal = factory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void drawCharacter(Integer x, Integer y, Character c, Color f, Color b) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(new TextColor.RGB(f.getR(), f.getG(), f.getB()));
        graphics.setBackgroundColor(new TextColor.RGB(b.getR(), b.getG(), b.getB()));
        graphics.putString(x, y, String.valueOf(c));
    }

    @Override
    public void drawString(Integer x, Integer y, String s, Color f, Color b) {
        for(Integer xadd = 0; xadd < s.length(); ++xadd){
            drawCharacter(x+xadd, y, s.charAt(xadd), f, b);
        }
    }

    boolean shouldRefreshAll = false;
    
    @Override
    public void clear(){
        if(screen.doResizeIfNecessary() != null) shouldRefreshAll = true;
        screen.clear();
    }
    
    boolean statistics = false;
    private static final Integer Dt = SECONDS_TO_MILLIS; // 1 second
    private final Queue<Long> times = new LinkedList<>();
    public void setStatistics(boolean statistics){ this.statistics = statistics; }
    public boolean getStatistics(){ return statistics; }
    
    @Override
    public void refresh() throws IOException {
        long now = System.currentTimeMillis();
        times.add(now);
        while(times.peek() != null && times.peek() < now-Dt) times.remove();
        
        if(statistics){
            drawString(0, getH()-1, "FPS: " + SECONDS_TO_MILLIS * times.size() / Dt, Color.WHITE, Color.BLACK);
        }
        
        screen.refresh((shouldRefreshAll ? Screen.RefreshType.COMPLETE : Screen.RefreshType.DELTA));
        shouldRefreshAll = false;
    }

    @Override
    public KeyStroke pollKey() throws IOException {
        KeyStroke key = screen.pollInput();
        if(key != null) {
            if (key.getKeyType() == KeyType.EOF) throw new EOFException("");
            if(key.getKeyType() == KeyType.Character && Character.toUpperCase(key.getCharacter()) == 'D') setStatistics(!getStatistics());
        }
        
        return key;
    }

    @Override
    public void close() throws IOException{
        screen.close();
    }

    @Override
    public Integer getW() { return screen.getTerminalSize().getColumns(); }

    @Override
    public Integer getH() { return screen.getTerminalSize().getRows(); }
}
