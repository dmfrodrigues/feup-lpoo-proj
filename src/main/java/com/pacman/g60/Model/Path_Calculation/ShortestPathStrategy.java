/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Model.Path_Calculation;

public interface ShortestPathStrategy<T> {

    void setGraph(Graph<T> G);
    void calcPaths(T source);
    T getPrev(T u);
}
