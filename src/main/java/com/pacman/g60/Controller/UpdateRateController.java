/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Controller;

public class UpdateRateController {
    private static final Integer SECONDS_TO_MILLIS = 1000;
    private Integer updatesPerSecond;
    public UpdateRateController(){ this(60); }
    public UpdateRateController(Integer updatesPerSecond){ this.updatesPerSecond = updatesPerSecond; }
    public void setUpdatesPerSecond(Integer updatesPerSecond){ 
        this.updatesPerSecond = updatesPerSecond;
    }
    private Integer getMillisPerFrame(){
        return SECONDS_TO_MILLIS/ updatesPerSecond;
    }
    private long previous = 0;
    private long lag = 0;
    public void start(){
        previous = System.currentTimeMillis();
    }
    public void startFrame(){
        long current = System.currentTimeMillis();
        long elapsed = current - previous;
        previous = current;
        lag += elapsed;
    }
    public long numberUpdatesInThisFrame(){
        long res = (lag/getMillisPerFrame());
        
        lag -= getMillisPerFrame()*res;
        return res;
    }
}
