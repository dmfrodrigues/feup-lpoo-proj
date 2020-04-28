public interface ShortestPath<T> {

    void setGraph(Graph<T> G);
    void calcPaths(T source);
    T getPrev(T u);
}
