package com.pacman.g60;

import com.pacman.g60.Controller.Stopwatch;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StopwatchTest {
    
    @Test
    public void ctor(){
        Stopwatch watch = new Stopwatch();
    }
    
    @Test
    public void reset() throws InterruptedException {
        Stopwatch watch = new Stopwatch();
        watch.reset();
        assertEquals(watch.poll(), Duration.ZERO);
        Thread.sleep(1000);
        assertEquals(watch.poll(), Duration.ZERO);
    }
    
    @Test
    public void resume_stop() throws InterruptedException {
        int epsilon = 10;
        
        Stopwatch watch = new Stopwatch();
        watch.reset();
        assertTrue(Math.abs(watch.poll().toMillis() - 0) < epsilon);
        watch.resume();
        Thread.sleep(1000);
        assertTrue(Math.abs(watch.poll().toMillis() - 1000) < epsilon);
        Thread.sleep(1000);
        assertTrue(Math.abs(watch.poll().toMillis() - 2000) < epsilon);
        watch.stop();
        Thread.sleep(1000);
        assertTrue(Math.abs(watch.poll().toMillis() - 2000) < epsilon);
        watch.resume();
        Thread.sleep(1000);
        assertTrue(Math.abs(watch.poll().toMillis() - 3000) < epsilon);
    }
    
    @Test
    public void start() throws InterruptedException {
        int epsilon = 10;
        
        Stopwatch watch = new Stopwatch();
        watch.start();
        Thread.sleep(1000);
        assertTrue(Math.abs(watch.poll().toMillis() - 1000) < epsilon);
        Thread.sleep(1000);
        assertTrue(Math.abs(watch.poll().toMillis() - 2000) < epsilon);
        Thread.sleep(1000);
        assertTrue(Math.abs(watch.poll().toMillis() - 3000) < epsilon);
        Thread.sleep(1000);
        assertTrue(Math.abs(watch.poll().toMillis() - 4000) < epsilon);
    }
}
