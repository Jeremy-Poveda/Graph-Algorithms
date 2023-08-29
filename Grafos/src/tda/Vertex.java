package tda;

import java.util.LinkedList;
import java.util.List;

class Vertex<V, E> {

    private V content;
    private List<Edge<E, V>> edges;
    private boolean visited;

  
    private double shortestDistance;
    private Vertex<V, E> predecesor;
    private int centrality;

  
    public Vertex(V content) {
        this.content = content;
        this.edges = new LinkedList<>();
        this.visited = false;
        this.shortestDistance = Double.POSITIVE_INFINITY;
        this.predecesor = null;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public V getContent() {
        return content;
    }

    public void setContent(V content) {
        this.content = content;
    }

    public List<Edge<E, V>> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge<E, V>> edges) {
        this.edges = edges;
    }

    @Override
    public String toString() {
        return content.toString();
    }
    
    public double getShortestDistance() {
       return shortestDistance;
    }

    public void setShortestDistance(double shortestDistance) {
        this.shortestDistance = shortestDistance;
    }
    
    public Vertex<V,E> getPredecesor() {
        return predecesor;
    }

    public void setPredecesor(Vertex predecesor) {
        this.predecesor = predecesor;
    }


}
