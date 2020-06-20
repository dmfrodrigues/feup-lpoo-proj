/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Controller;

public abstract class Controller {
    boolean terminated = false;
    
    public void terminate(){ terminated = true; }
    public boolean wasTerminated(){ return terminated; }
}
