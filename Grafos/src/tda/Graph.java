package tda;

import java.util.List;

public interface Graph<V, E> {
    
    public boolean addVertex(V content);
    
    public boolean hasVertex(V content);   
    
    public boolean removeVertex(V content);

    public boolean connect(V source, V target);

    public boolean connect(V source, V target, int weight);

    public boolean connect(V source, V target, int weight, E metadata);
    
    public boolean removeEdge(V source, V target);

    // recorrido en anchura
    public List<V> breadthTraversal(V start);

    // recorrido en profundida
    public List<V> depthTraversal(V start);
    
    public boolean getConnectedComponents();
}
