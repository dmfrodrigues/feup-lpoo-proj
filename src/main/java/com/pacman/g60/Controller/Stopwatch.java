/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Controller;

import java.time.Duration;
import java.time.Instant;

public class Stopwatch {
    Instant start;
    boolean running = false;
    private Duration time = Duration.ZERO;
    public void reset(){
        time = Duration.ZERO;
        running = false;
    }
    public void resume(){
        if(!running){
            start = Instant.now();
            running = true;
        }
    }
    public void start(){
        reset();
        resume();
    }
    public void stop(){
        time = poll();
        running = false;
    }
    public Duration poll() {
        if(running){
            Instant now = Instant.now();
            return time.plus(Duration.between(start, now));
        } else return time;
    }
}
