package grafos;

import java.util.Comparator;
import tda.Graph;
import tda.GraphAL;
import tda.GraphAM;

public class Grafos {
    public static void main(String[] args) {
        
        Comparator<String> cmpVertices = (s1, s2) -> {
            return s1.compareTo(s2);
        };
        
        Comparator<String> cmpEdges = (s1, s2) -> {
            return s1.compareTo(s2);
        };
        
        Graph<String, String> grafo = new GraphAL<>(true, cmpVertices, cmpEdges);
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addVertex("C");
        grafo.addVertex("D");
        grafo.addVertex("E");
        grafo.addVertex("F");

        grafo.connect("A", "E", 8);
        grafo.connect("A", "B", 3);
        grafo.connect("A", "C", 4);
        grafo.connect("C", "E", 3);
        grafo.connect("B", "E", 5);
        grafo.connect("E", "F", 3);
        grafo.connect("E", "D", 7);
        grafo.connect("F", "D", 2);
        System.out.println(grafo.dijktra("B"));
        System.out.println(grafo);
    
    
//        
//        Graph<String, String> g1 = new GraphAL<>(true, cmpVertices, cmpEdges);
//        g1.addVertex("A");
//        g1.addVertex("B");
//        g1.addVertex("C");
//        g1.addVertex("D");
//        g1.addVertex("H");
//        g1.addVertex("T");
//        g1.addVertex("R");
//        
//        g1.connect("H", "A");
//        
//        g1.connect("H", "D");
//        g1.connect("C", "R");
//        g1.connect("H", "T");
//        g1.connect("B", "H");
//        g1.connect("R", "H");
//        g1.connect("D", "B");
//        g1.connect("D", "C");
//        
//        
//        
//        
//        System.out.println(g1);
//        System.out.println("Busqueda a lo ancho BFS: ");
//        System.out.println(g1.breadthTraversal("D"));
//        System.out.println("Busqueda en profundidad: ");
//        System.out.println(g1.depthTraversal("D"));
//        
//        
//        Graph<String, String> grafo1 = new GraphAL<>(false, cmpVertices, cmpEdges);
//
//        grafo1.addVertex("A");
//        grafo1.addVertex("B");
//        grafo1.addVertex("C");
//        grafo1.addVertex("D");
//        grafo1.addVertex("E");
//
//        grafo1.connect("A", "B", 0, null);
//        grafo1.connect("A", "D", 0, null);
//        grafo1.connect("D", "C", 0, null);
//        grafo1.connect("C", "E", 0, null);
//
//        System.out.println("GRAFO 1");
//        System.out.println(grafo1);
//        System.out.println("Busqueda a lo ancho BFS: ");
//        System.out.println(grafo1.breadthTraversal("A"));
//        System.out.println("Busqueda en profundidad: ");
//        System.out.println(grafo1.depthTraversal("A"));
//        System.out.println("Es conexo:");
//        System.out.println(grafo1.getConnectedComponents());
//        
//        Graph<String, String> grafo2 = new GraphAL<>(false, cmpVertices, cmpEdges);
//
//        grafo2.addVertex("A");
//        grafo2.addVertex("B");
//        grafo2.addVertex("C");
//        grafo2.addVertex("D");
//        grafo2.addVertex("E");
//
//        grafo2.connect("A", "B");
//        grafo2.connect("C", "E");
//        grafo2.connect("C", "D");
//        
//        System.out.println("GRAFO 2");
//        System.out.println(grafo2);
//        System.out.println("Busqueda a lo ancho BFS: ");
//        System.out.println(grafo2.breadthTraversal("E"));
//        System.out.println("Busqueda en profundidad: ");
//        System.out.println(grafo2.depthTraversal("E"));
//        System.out.println("Es conexo:");
//        System.out.println(grafo2.getConnectedComponents());
//        
//        Graph<String, String> grafo3 = new GraphAL<>(true, cmpVertices, cmpEdges);
//        grafo3.addVertex("B");
//        grafo3.addVertex("C");
//        grafo3.addVertex("F");
//        grafo3.addVertex("H");
//        grafo3.addVertex("S");
//        
//        
//        grafo3.connect("C", "B");
//        grafo3.connect("B", "S");
//        grafo3.connect("S", "C");
//        grafo3.connect("F", "C");
//        grafo3.connect("F", "S");
//        grafo3.connect("H", "B");
//        grafo3.connect("H", "F");
//        
//        System.out.println("GRAFO 3");
//        System.out.println(grafo3);
//        System.out.println("Busqueda a lo ancho BFS: ");
//        System.out.println(grafo3.breadthTraversal("C"));
//        System.out.println("Busqueda en profundidad: ");
//        System.out.println(grafo3.depthTraversal("C"));
//        System.out.println("Es conexo:");
//        System.out.println(grafo3.getConnectedComponents());
//        
//        Graph<String, String> grafo4 = new GraphAL<>(true, cmpVertices, cmpEdges);
//
//        grafo4.addVertex("4");
//        grafo4.addVertex("8");
//        grafo4.addVertex("6");
//        grafo4.addVertex("5");
//
//        grafo4.connect("4", "8");
//        grafo4.connect("8", "4");
//        grafo4.connect("8", "6");
//        grafo4.connect("6", "8");
//        grafo4.connect("6", "5");
//        grafo4.connect("5", "6");
//        grafo4.connect("5", "4");
//        grafo4.connect("4", "5");
//                
//         System.out.println("GRAFO 4");
//        System.out.println(grafo4);
//        System.out.println("Busqueda a lo ancho BFS: ");
//        System.out.println(grafo4.breadthTraversal("4"));
//        System.out.println("Busqueda en profundidad: ");
//        System.out.println(grafo4.depthTraversal("4"));
//        System.out.println("Es conexo:");
//        System.out.println(grafo4.getConnectedComponents());
//        
        
        
    }
}

