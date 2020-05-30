package com.pacman.g60.Controller;

public class UpdateRateController {
    private static final Integer SECONDS_TO_MILLIS = 1000;
    private Integer fps;
    public UpdateRateController(){ this(60); }
    public UpdateRateController(Integer fps){ this.fps = fps; }
    public void setFPS(Integer fps){ 
        this.fps = fps;
    }
    private Integer getMillisPerFrame(){
        return SECONDS_TO_MILLIS/fps;
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
