/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.View.Font;

import java.io.InputStream;
import java.util.Scanner;

public class TerminalFontLoaderStream implements TerminalFont.Loader {
    private final TerminalFont terminalFont;
    public TerminalFontLoaderStream(InputStream inputStream){
        Scanner scanner = new Scanner(inputStream);
        String line = scanner.nextLine();
        int W = Integer.parseInt(line.split(" ")[0]);
        int H = Integer.parseInt(line.split(" ")[1]);
        terminalFont = new TerminalFont(W, H);
        line = scanner.nextLine();
        int N = Integer.parseInt(line);
        for(int n = 0; n < N; ++n) {
            line = scanner.nextLine();
            char c = line.charAt(0);
            TerminalFont.TerminalCharacter tchar = new TerminalFont.TerminalCharacter(W, H);
            for (Integer y = 0; y < H; ++y) {
                line = scanner.nextLine();
                if (line.length() != W) throw new IllegalArgumentException("Invalid file content (on reading character '" + c + "')");
                for (int x = 0; x < W; ++x)
                    tchar.setChar(x, y, line.charAt(x));
            }
            terminalFont.setCharacter(c, tchar);
        }
    }

    @Override
    public TerminalFont getTerminalFont() {
        return terminalFont;
    }
}
