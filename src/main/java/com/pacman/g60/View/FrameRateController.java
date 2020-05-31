package com.pacman.g60.View;

public class FrameRateController {
    private static final Integer SECONDS_TO_MILLIS = 1000;
    private Integer framesPerSecond;
    public FrameRateController(){ this(60); }
    public FrameRateController(Integer framesPerSecond){ setFramesPerSecond(framesPerSecond); }
    public void setFramesPerSecond(Integer framesPerSecond){ this.framesPerSecond = framesPerSecond; }
    private Integer getMillisPerFrame(){
        return SECONDS_TO_MILLIS/ framesPerSecond;
    }
    private long previous = 0;
    private long sleep = 0;
    public void start(){ previous = System.currentTimeMillis(); }
    public void startFrame() throws InterruptedException {
        long current = System.currentTimeMillis();
        long elapsed = current-previous;
        previous = current;
        if(elapsed < getMillisPerFrame()) ++sleep;
        else if(elapsed > getMillisPerFrame()) sleep = Math.max(0, sleep-1);
        Thread.sleep(sleep);
    }
}
