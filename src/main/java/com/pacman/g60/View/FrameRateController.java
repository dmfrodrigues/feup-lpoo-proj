package com.pacman.g60.View;

import java.util.LinkedList;
import java.util.Queue;

public class FrameRateController {
    private static final long SECONDS_TO_MILLIS = 1000L;
    private static final long SECONDS_TO_NANOS = 1000000000L;
    private static final long MILLIS_TO_NANOS = 1000000L;
    private Integer framesPerSecond;
    public FrameRateController(){ this(60); }
    public FrameRateController(Integer framesPerSecond){ setFramesPerSecond(framesPerSecond); }
    public void setFramesPerSecond(Integer framesPerSecond){ 
        this.framesPerSecond = framesPerSecond;
        //nanosleep = getNanosPerFrame();
    }
    private long getNanosPerFrame(){
        return SECONDS_TO_NANOS/ framesPerSecond;
    }
    private Queue<Long> times = new LinkedList<>();
    private final static long Dt = SECONDS_TO_MILLIS; //one second
    private long nanosleep = 0;
    private long correctionFactor = 200;
    public void startFrame() {
        long now = System.currentTimeMillis();
        times.add(now);
        while(times.peek() != null && times.peek() < now-Dt) times.poll();
        long nanoelapsed = Dt*MILLIS_TO_NANOS / times.size();
        long nanosleepcorrection = getNanosPerFrame() - nanoelapsed;
        nanosleep += nanosleepcorrection/correctionFactor;
        nanosleep = Math.max(nanosleep, 0);
        try {
            Thread.sleep(nanosleep/MILLIS_TO_NANOS, (int) (nanosleep % MILLIS_TO_NANOS));
        } catch (InterruptedException e) {
            System.err.println("Interrupted: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
