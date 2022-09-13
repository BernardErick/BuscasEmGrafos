import java.util.ArrayList;
import java.util.HashMap;

public class DFS {
    public static void main(String[] args) {
        Graph graph = new Graph();

        Vertex u = new Vertex("u");
        Vertex v = new Vertex("v");
        Vertex w = new Vertex("w");
        Vertex x = new Vertex("x");
        Vertex y = new Vertex("y");
        Vertex z = new Vertex("z");

        graph.addVertex(u);
        graph.addVertex(v);
        graph.addVertex(w);
        graph.addVertex(x);
        graph.addVertex(y);
        graph.addVertex(z);

        graph.addEdge(u,v);
        graph.addEdge(u,x);
        graph.addEdge(v,y);
        graph.addEdge(y,x);
        graph.addEdge(x,v);
        graph.addEdge(w,y);
        graph.addEdge(w,z);
        graph.addEdge(z,z);

        System.out.println("Grafo de "+graph.size()+" vertices!");
        System.out.println("Exibindo o vertice U:");
        graph.getVertex("u").print();
        System.out.println("Exibindo os vizinhos de U:");
        graph.getVertex("u").printNeighbors();

        System.out.println("<------------ Usando DFS ------------>");
        graph.DFS();
        System.out.println("Exibindo o vertice qualquer após o DFS:");
        graph.getVertex("z").print();
    }
}

class Graph {
    private ArrayList<Vertex> vertex;
    private int time = 0;
    Graph(){
        this.vertex = new ArrayList<Vertex>();
    }
    public void addVertex(Vertex vertex){
        this.vertex.add(vertex);
    }
    public void addEdge(Vertex vertex, Vertex neighbor){
        for(Vertex v : this.vertex){
            if(v.name == vertex.name)
                v.addNeighbor(neighbor);
        }
    }
    public Vertex getVertex(String name){
        for(Vertex v : this.vertex){
            if(v.name == name)
                return v;
        }
        return null;
    }
    public int size(){
        return this.vertex.size();
    }
    public void DFS(){
        for(Vertex u : this.vertex){
            if(u.color == Color.WHITE){
                DFS_VISIT(u);
            }
        }
        time = 0;
    }
    public void DFS_VISIT(Vertex u){
        u.color = Color.GRAY;
        time++;
        u.d = time;
        for(Vertex v : u.neighbors){
            if(v.color == Color.GRAY){ //EXTRA (PROCURANDO CICLO)
                System.out.println("Encontrado uma aresta de retorno no DFS!");
                System.out.println("["+u.name+"] -> ["+v.name+"]");
                System.out.println("Ciclo encontrado!");
            }
            if(v.color == Color.WHITE){
                v.parent = u;
                DFS_VISIT(v);
            }
        }
        u.color = Color.BLACK;
        time++;
        u.f = time;
    }
}
class Vertex {
    public String name;
    public ArrayList<Vertex> neighbors;
    public Color color = Color.WHITE;
    public Vertex parent = null;
    public int d;
    public int f;
    Vertex(String name){
        this.neighbors = new ArrayList<Vertex>();
        this.name = name;
    }
    public void addNeighbor(Vertex neighbor){
        this.neighbors.add(neighbor);
    }
    public int size(){
        return this.neighbors.size();
    }
    public void print(){
        System.out.println("Vertíce["+this.name+"]");
        System.out.println("Cor["+this.color+"]");
        System.out.println("Parent["+this.parent+"]");
        System.out.println("tempo descoberto["+this.d+"]");
        System.out.println("tempo fechamento["+this.f+"]");
    }
    public void printNeighbors(){
        for(Vertex n : this.neighbors){
            System.out.println("<----VIZINHO---->");
            System.out.println("Vertíce["+n.name+"]");
            System.out.println("Cor["+n.color+"]");
            System.out.println("Parent["+n.parent+"]");
            System.out.println("tempo descoberto["+n.d+"]");
            System.out.println("tempo fechamento["+n.f+"]");
        }
    }
}
enum Color{
    WHITE,
    GRAY,
    BLACK;
}

