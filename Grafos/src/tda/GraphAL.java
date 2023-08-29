package tda;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class GraphAL<V, E> implements Graph<V, E> {

    private List<Vertex<V, E>> vertices;
    private boolean isDirected;
    private Comparator<V> cmpVertices;
    private Comparator<E> cmpEdges;

    public GraphAL(boolean isDirected, Comparator<V> cmpVertices, Comparator<E> cmpEdges) {
        this.isDirected = isDirected;
        this.cmpVertices = cmpVertices;
        this.cmpEdges = cmpEdges;
        this.vertices = new LinkedList<>();
    }
    
    private void resetVisited() {
        for (Vertex<V, E> v : vertices) {
            v.setVisited(false);
            v.setShortestDistance(Double.POSITIVE_INFINITY);
        }
    }
    
    private boolean edgeExist(Vertex<V, E> vertex, Edge<E, V> edge) {
        if (vertex == null || edge == null || vertex.getEdges().isEmpty()) {
            return false;
        }

        for (Edge<E, V> e : vertex.getEdges()) {
            if (e != null && e.getMetadata() != null && edge.getMetadata() != null &&
                cmpEdges.compare(e.getMetadata(), edge.getMetadata()) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addVertex(V content) {
        Vertex<V, E> newVertex = new Vertex<>(content);
        return (content == null || this.hasVertex(content)) ? false : vertices.add(newVertex);
    }

    @Override
    public boolean hasVertex(V content) {
        for (Vertex<V, E> v : vertices) {
            if (this.cmpVertices.compare(content, v.getContent()) == 0) {
                return true;
            }
        }
        return false;
    }

    private Vertex<V, E> findVertex(V content) {
        for (Vertex<V, E> v : vertices) {
            if (this.cmpVertices.compare(content, v.getContent()) == 0) {
                return v;
            }
        }
        return null;
    }

    @Override
    public boolean removeVertex(V content) {
        if (content == null) {
            return false;
        }
        Vertex<V, E> v = findVertex(content);
        if (v == null) {
            return false;
        }
        for (Vertex<V, E> vertex : vertices) {
            Iterator<Edge<E, V>> edgesIterator = vertex.getEdges().iterator();
            while (edgesIterator.hasNext()) {
                Edge<E, V> currentEdge = edgesIterator.next();
                Vertex<V, E> currentSource = currentEdge.getSource();
                Vertex<V, E> currentTarget = currentEdge.getTarget();
                if (this.cmpVertices.compare(currentSource.getContent(), v.getContent()) == 0
                        || this.cmpVertices.compare(currentTarget.getContent(), v.getContent()) == 0) {
                    edgesIterator.remove();
                }
            }
        }

        v.setContent(null);
        v.setEdges(null);
        vertices.remove(v);
        return true;

    }

    @Override
    public boolean connect(V source, V target) {
        return connect(source, target, 1, null);
    }

    @Override
    public boolean connect(V source, V target, int weight) {
        return connect(source, target, weight, null);
    }

    @Override
    public boolean connect(V source, V target, int weight, E metadata) {
        if(this.vertices.isEmpty() || !hasVertex(source)|| !hasVertex(target)){
           return false; 
        }
        Vertex<V,E> vertexSource = findVertex(source);
        Vertex<V,E> vertexTarget = findVertex(target);

        // ya tenemos los vertices, ahora añadimos los arcos
        
        Edge<E,V> edge = new Edge<>(vertexSource ,vertexTarget, weight, metadata);
        
        if(this.edgeExist(vertexSource, edge)){
            System.out.println("Ya existe una conexion, sobrescribiendo...");
        }
        vertexSource.getEdges().add(edge);
        if(!this.isDirected){
            Edge<E, V> reverseEdge = new Edge<>(vertexTarget, vertexSource, weight, metadata);
            vertexTarget.getEdges().add(reverseEdge);
        }
        return true;
    }

    @Override
    public boolean removeEdge(V source, V target) {
        if (source == null || target == null) {
            return false;
        }
        Vertex<V, E> vSource = findVertex(source);
        Vertex<V, E> vTarget = findVertex(target);
        if (vSource == null || vTarget == null) {
            return false;
        }
        List<Edge<E, V>> edges = vSource.getEdges();
        Iterator<Edge<E, V>> edgesIterator = edges.iterator();
        while (edgesIterator.hasNext()) {
            Edge<E, V> e = edgesIterator.next();
            if (this.cmpVertices.compare(e.getTarget().getContent(), vTarget.getContent()) == 0) {
                edgesIterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder v = new StringBuilder();
        v.append(" vertices = {");

        StringBuilder a = new StringBuilder();
        a.append(" edges = {");

        for (Vertex<V, E> vertex : vertices) {
            v.append(vertex.getContent());
            v.append(", ");
            for (Edge<E, V> e : vertex.getEdges()) {
                a.append(e.toString());
                a.append(", ");
            }
        }
        if (!vertices.isEmpty()) {
            v.delete(v.length() - 2, v.length());
        }
        if (a.length() > 4) {
            a.delete(a.length() - 2, a.length());
        }

        v.append("}");
        a.append("}");
        return v.toString() + "\n" + a.toString();
    }

    @Override
    public List<V> breadthTraversal(V start) {
        if (!hasVertex(start)) {
            System.out.println("El vértice de inicio no existe en el grafo.");
            return null;
        }

        List<V> bfsList = new LinkedList<>();

        Queue<Vertex<V, E>> queue = new ArrayDeque<>(); // Cola de vertices

        Vertex<V, E> startVertex = findVertex(start);

        queue.add(startVertex);

        while (!queue.isEmpty()) {
            Vertex<V, E> currentVertex = queue.poll();

            // Marcar el vértice actual como visitado antes de agregarlo a la lista
            currentVertex.setVisited(true);

            bfsList.add(currentVertex.getContent());

            for (Edge<E, V> edge : currentVertex.getEdges()) {
                Vertex<V, E> adjacent = edge.getTarget();
                if (!adjacent.isVisited()) {
                    queue.add(adjacent);
                    adjacent.setVisited(true);
                }
            }
        }

        // Se restaura los valores para futuros recorridos
        resetVisited();

        return bfsList;
    }

    @Override
    public List<V> depthTraversal(V start) {
        if (!hasVertex(start)) {
            System.out.println("El vértice de inicio no existe en el grafo.");
            return null;
        }

        List<V> dfsList = new LinkedList<>();

        Stack<Vertex<V, E>> stack = new Stack<>(); // Cola de vertices

        Vertex<V, E> startVertex = findVertex(start);

        stack.add(startVertex);

        while (!stack.isEmpty()) {
            Vertex<V, E> currentVertex = stack.pop();

            // Marcar el vértice actual como visitado antes de agregarlo a la lista
            currentVertex.setVisited(true);

            dfsList.add(currentVertex.getContent());

            for (Edge<E, V> edge : currentVertex.getEdges()) {
                Vertex<V, E> adjacent = edge.getTarget();
                if (!adjacent.isVisited()) {
                    stack.add(adjacent);
                    adjacent.setVisited(true);
                }
            }
        }

        // Se restaura los valores para futuros recorridos
        resetVisited();

        return dfsList;
    }
    
    public boolean getConnectedComponents() { 
        List<List<V>> components = new LinkedList<>();

        for (Vertex<V, E> vertex : vertices) {
            if (!vertex.isVisited()) {
                List<V> component = new LinkedList<>();
                Stack<Vertex<V, E>> stack = new Stack<>();

                stack.push(vertex);
                vertex.setVisited(true);

                while (!stack.isEmpty()) {
                    Vertex<V, E> currentVertex = stack.pop();
                    component.add(currentVertex.getContent());

                    for (Edge<E, V> edge : currentVertex.getEdges()) {
                        Vertex<V, E> adjacent = edge.getTarget();
                        if (!adjacent.isVisited()) {
                            stack.push(adjacent);
                            adjacent.setVisited(true);
                        }
                    }
                }

                components.add(component);
            }
        }

        resetVisited();
        return components.size() == 1;
    }
    
    public List<List<Vertex<V, E>>> dijktra(V content) {
        Vertex<V, E> actualVertex = findVertex(content);
        if (actualVertex == null) {
            return null;
        }

        PriorityQueue<Vertex<V, E>> pq = new PriorityQueue<>(Comparator.comparingDouble(Vertex::getShortestDistance));
        pq.offer(actualVertex);

        while (!pq.isEmpty()) {
            actualVertex = pq.poll();
            actualVertex.setVisited(true);
            if(cmpVertices.compare(actualVertex.getContent(), content) == 0){ // Si el nodo que sacamos es el primero, seteamos su distancia minima en 0
                actualVertex.setShortestDistance(0);
                actualVertex.setPredecesor(actualVertex);
            }

            for (Edge<E, V> edge : actualVertex.getEdges()) {
                Vertex<V, E> adjacent = edge.getTarget();

                double newDistance = actualVertex.getShortestDistance() + edge.getWeight();
                if (newDistance < adjacent.getShortestDistance()) {
                    adjacent.setShortestDistance(newDistance);
                    adjacent.setPredecesor(actualVertex);
                    if (!adjacent.isVisited()) {
                        pq.add(adjacent);
                    }
                }
            }
        }

        List<List<Vertex<V, E>>> shortestPaths = new LinkedList<>();
        // Almacena los resultados de vértices
        for (int i = 0; i < vertices.size(); i++) {
            Vertex<V, E> currentVertex = vertices.get(i);
            List<Vertex<V, E>> path = new LinkedList<>();
            while (cmpVertices.compare(currentVertex.getContent(), content) != 0 && currentVertex.getPredecesor() != null) {
                path.add(currentVertex);
                currentVertex = currentVertex.getPredecesor();
            }
            if(currentVertex.getPredecesor() != null){
                path.add( findVertex(content));
            }
            Collections.reverse(path);

            shortestPaths.add(path);
        }

        resetVisited();
        return shortestPaths;
    }

    public List<Integer> computeBetweennessCentrality(){
        List<Integer> centralities = new LinkedList<>();
        List<List<List<Vertex<V,E>>>> vertexsPaths = new LinkedList<>();
        for(Vertex<V, E> v : vertices){
            vertexsPaths.add(this.dijktra(v.getContent()));
        }
        // Remover caminos menores a 2
        for(List<List<Vertex<V,E>>> vp : vertexsPaths){
            
            for(List<Vertex<V,E>> p : vp){
                if(vertexsPaths.contains(Collections.reverse(p))){
                    
                }
                if(p.size() <= 2){
                    vp.remove(p);
                }
            }
        }
        
        
        
        return centralities;
    }

}
