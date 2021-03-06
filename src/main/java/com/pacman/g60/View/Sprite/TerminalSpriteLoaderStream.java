/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.View.Sprite;


import com.pacman.g60.View.Color;

import java.io.InputStream;
import java.util.Scanner;

public class TerminalSpriteLoaderStream implements TerminalSprite.Loader{
    private final TerminalSprite terminalSprite;
    public TerminalSpriteLoaderStream(InputStream inputStream){
        Scanner scanner = new Scanner(inputStream);
        String firstLine = scanner.nextLine();
        int W = Integer.parseInt(firstLine.split(" ")[0]);
        int H = Integer.parseInt(firstLine.split(" ")[1]);
        terminalSprite = new TerminalSprite(W, H);
        for(int y = 0; y < H; ++y){
            String[] colors = scanner.nextLine().split(" ");
            if(colors.length != W) throw new IllegalArgumentException("Invalid file content");
            for(int x = 0; x < W; ++x)
                terminalSprite.setBackgroundColor(x, y, new Color(colors[x]));
        }
        for(int y = 0; y < H; ++y){
            String line = scanner.nextLine();
            if(line.length() != W) throw new IllegalArgumentException("Invalid file content");
            for(int x = 0; x < W; ++x)
                terminalSprite.setChar(x, y, line.charAt(x));
        }
        for(int y = 0; y < H; ++y){
            String[] colors = scanner.nextLine().split(" ");
            if(colors.length != W) throw new IllegalArgumentException("Invalid file content");
            for(int x = 0; x < W; ++x)
                terminalSprite.setForegroundColor(x, y, new Color(colors[x]));
        }
    }

    @Override
    public TerminalSprite getTerminalSprite() {
        return terminalSprite;
    }
}
