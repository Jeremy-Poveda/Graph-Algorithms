package tda;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class GraphAM<V, E> implements Graph<V, E> {

    private V[] vertices;
    private int[][] adjacencyMatrix;
    private E[][] edgesData;
    private int capacity;
    private int effectiveSize;
    private boolean isDirected;
    private Comparator<V> cmpVertices;
    private Comparator<E> cmpEdges;
    
    public GraphAM(boolean isDirected, Comparator<V> cmpVertices, Comparator<E> cmpEdges) {
        this.isDirected = isDirected;
        this.cmpVertices = cmpVertices;
        this.cmpEdges = cmpEdges;
        this.capacity = 100;
        this.effectiveSize = 0;
        this.vertices = (V[]) new Object[capacity];
        this.adjacencyMatrix = new int[capacity][capacity];
        this.edgesData = (E[][]) new Object[capacity][capacity];
    }

    public boolean connect(V source, V target) {
        return connect(source, target, 1);
    }

    public boolean connect(V source, V target, int weight) {
        return connect(source, target, weight, null);
    }

    private int findVertex(V v) {
        for (int i = 0; i < effectiveSize; i++) {
            if (this.cmpVertices.compare(v, vertices[i]) == 0) {
                return i;
            }
        }
        return -1;
    }

    public boolean connect(V source, V target, int weight, E connectionData) {
        if (source == null || target == null) {
            return false;
        }
        int i1 = findVertex(source);
        int i2 = findVertex(target);
        if (i1 != -1 && i2 != -1) {
            this.adjacencyMatrix[i1][i2] = weight;
            this.edgesData[i1][i2] = connectionData;
            if (!this.isDirected) {
                this.adjacencyMatrix[i2][i1] = weight;
                this.edgesData[i2][i1] = connectionData;
            }
            return true;
        }
        return false;
    }

    @Override
    public List<V> breadthTraversal(V start) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<V> depthTraversal(V start) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addVertex(V content) {
        if (content == null || this.hasVertex(content)) {
            return false;
        }
        this.vertices[effectiveSize] = content;
        effectiveSize++;

        return true;
    }

    public boolean isEmpty(){
        return this.effectiveSize == 0;
    }
    
    @Override
    public boolean hasVertex(V content) {
        if(this.isEmpty()){
            return false;
        }
        
        return findVertex(content) != -1;
    }

    @Override
    public boolean removeVertex(V content) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean removeEdge(V source, V target) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean getConnectedComponents() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private int returnMinIndex(List<Double> distances){
        int minIndex = 0;
        for(int i = 0; i < distances.size(); i++){
            if(distances.get(i) < minIndex){
                minIndex = i;
            }
        }
        return minIndex;
    }
    
//    @Override
//    public List dijktra(V content){
//        
//        if (content == null) {
//            return null;
//        }
//
//        List<Double> distances = new LinkedList<>();
//        boolean[] visited = new boolean[effectiveSize];
//        for (int i = 0; i < effectiveSize; i++) {
//            distances.add(Double.POSITIVE_INFINITY);
//        }
//        int actualIndex = findVertex(content);
//        if(actualIndex == -1){
//            return null;
//        }
//        
//        
//        
//        return distances;
//    }
    
    @Override
    public List<Double> dijktra(V content) {
        if (content == null) {
            return null;
        }

        List<Double> distances = new LinkedList<>();
        boolean[] visited = new boolean[effectiveSize];

        // Initialize distances with positive infinity
        for (int i = 0; i < effectiveSize; i++) {
            distances.add(Double.POSITIVE_INFINITY);
        }

        // Find the index of the starting vertex
        int startIndex = findVertex(content);
        if (startIndex == -1) {
            return null;
        }

        // Set the distance to the starting vertex as 0
        distances.set(startIndex, 0.0);

        for (int i = 0; i < effectiveSize; i++) {
            // Find the vertex with the minimum distance
            int minIndex = returnMinIndex(distances, visited);
            visited[minIndex] = true;

            // Update distances for adjacent vertices
            for (int j = 0; j < effectiveSize; j++) {
                if (!visited[j] && adjacencyMatrix[minIndex][j] > 0) {
                    double newDistance = distances.get(minIndex) + adjacencyMatrix[minIndex][j];
                    if (newDistance < distances.get(j)) {
                        distances.set(j, newDistance);
                    }
                }
            }
        }

        return distances;
    }

    private int returnMinIndex(List<Double> distances, boolean[] visited) {
        double minDistance = Double.POSITIVE_INFINITY;
        int minIndex = -1;

        for (int i = 0; i < distances.size(); i++) {
            if (!visited[i] && distances.get(i) < minDistance) {
                minDistance = distances.get(i);
                minIndex = i;
            }
        }

        return minIndex;
    }

}
