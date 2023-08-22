package tda;

import java.util.Comparator;
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
        return true;
    }

    @Override
    public boolean hasVertex(V content) {
        for (int i = 0; i < vertices.length; i++) {
            V vertex = vertices[i];
            if (this.cmpVertices.compare(content, vertex) == 0) {
                return true;
            }
        }
        return false;
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

}
