package com.pacman.g60.Model.Path_Calculation;

public interface ShortestPath<T> {

    void setGraph(Graph<T> G);
    void calcPaths(T source);
    T getPrev(T u);
}
