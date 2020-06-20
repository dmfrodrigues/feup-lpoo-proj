/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.View;

import java.time.Duration;
import java.time.Instant;

public class FrameRateController {
    private static final long SECONDS_TO_NANOS = 1000000000L;
    private static final long MILLIS_TO_NANOS = 1000000L;
    private Integer framesPerSecond;
    public FrameRateController(){ this(60); }
    public FrameRateController(Integer framesPerSecond){ setFramesPerSecond(framesPerSecond); }
    public void setFramesPerSecond(Integer framesPerSecond){
        this.framesPerSecond = framesPerSecond;
        nanoSleepDelta = getFrameDuration().toNanos();
    }
    private Duration getFrameDuration(){
        return Duration.ZERO.plusNanos(SECONDS_TO_NANOS/framesPerSecond);
    }
    private Instant previous = Instant.now();
    private long nanoSleep = 0;
    private long nanoSleepDelta = 100000;
    public void start(){ previous = Instant.now(); }
    public void startFrame() {
        Instant current = Instant.now();
        Duration elapsed = Duration.between(previous, current);
        previous = current;
        int cmp = elapsed.compareTo(getFrameDuration());
        if(cmp < 0) nanoSleep += nanoSleepDelta;
        else if(cmp > 0) nanoSleep = Math.max(0, nanoSleep-nanoSleepDelta);
        try {
            Thread.sleep(nanoSleep/MILLIS_TO_NANOS, (int) (nanoSleep%MILLIS_TO_NANOS));
        } catch (InterruptedException e) {
            System.err.println("Interrupted: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
