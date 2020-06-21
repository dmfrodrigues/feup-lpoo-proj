/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Model;

import java.io.*;
import java.time.Duration;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;

public class GameProgressFileIO {
    public GameProgress read(File file) throws FileNotFoundException {
        GameProgress res = new GameProgress();
        
        InputStream is = new FileInputStream(file);
        Scanner scanner = new Scanner(is);
        Integer N = Integer.parseInt(scanner.nextLine());
        for(Integer i = 0; i < N; ++i){
            String[] line = scanner.nextLine().split(" ");
            res.addProgress(new GameProgress.LevelProgress(
                    Integer.parseInt(line[0]),
                    Integer.parseInt(line[1]),
                    Duration.ZERO.plusNanos(Long.parseLong(line[2])),
                    new Date(Long.parseLong(line[3]))
            ));
        }
        
        return res;
    }
    public void write(File file, GameProgress progress) throws IOException {
        FileWriter fr = new FileWriter(file);
        Set<Integer> levels = progress.getLevels();
        fr.write(levels.size() + "\n");
        for(final Integer l : levels){
            final GameProgress.LevelProgress level = progress.getProgress(l);
            String data =
                    level.getLevel() + " " +
                    level.getCoins() + " " +
                    level.getTime().toNanos() + " " +
                    level.getWhen().getTime() + "\n";
            fr.write(data);
        }
        fr.close();
    }
}
