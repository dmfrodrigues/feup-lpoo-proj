/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Model.Elements.Hierarchy;

public interface LivingElement {
    public void updateHealth(Integer diff);
    public boolean isAlive();
}
