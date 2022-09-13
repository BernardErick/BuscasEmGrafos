import java.util.ArrayList;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        Vertice a = new Vertice("a");
        Vertice b = new Vertice("b");
        Vertice c = new Vertice("c");
        Vertice d = new Vertice("d");
        Vertice e = new Vertice("e");
        Vertice f = new Vertice("f");

        grafo.addVertex(a);
        grafo.addVertex(b);
        grafo.addVertex(c);
        grafo.addVertex(d);
        grafo.addVertex(e);
        grafo.addVertex(f);

        grafo.addEdge(a,b);
        grafo.addEdge(a,c);
        grafo.addEdge(a,d);
        grafo.addEdge(b,e);
        grafo.addEdge(c,e);
        grafo.addEdge(c,f);
        grafo.addEdge(d,f);
        grafo.addEdge(b,c);
        grafo.addEdge(c,d);

        grafo.getVertex("a").printNeighbors();
        grafo.BFS("c");
        System.out.println("<------------Usando BFS------------->");
        grafo.getVertex("e").print();
    }

}
class Grafo {
    private ArrayList<Vertice> vertex;
    private ArrayList<Vertice> Q = new ArrayList<>();
    Grafo(){
        this.vertex = new ArrayList<Vertice>();
    }
    public void addVertex(Vertice vertex){
        this.vertex.add(vertex);
    }
    public void addEdge(Vertice vertex, Vertice neighbor){
        for(Vertice v : this.vertex){
            if(v.name == vertex.name)
                v.addNeighbor(neighbor);
        }
    }
    public Vertice getVertex(String name){
        for(Vertice v : this.vertex){
            if(v.name == name)
                return v;
        }
        return null;
    }
    public int size(){
        return this.vertex.size();
    }
    public void BFS(String s){
        Vertice origin = this.getVertex(s);
        origin.color = Color.GRAY;
        origin.d = 0f;
        Q.add(origin); //enfileirando
        while(Q.size() != 0){
            Vertice u = Q.remove(0); //desenfileirando
            for(Vertice v : u.neighbors){
                if(v.color == Color.WHITE){
                    v.color = Color.GRAY;
                    v.d = u.d + 1f;
                    v.parent = u;
                    Q.add(v); //enfileirando
                    System.out.println("Quem descobriu o "+v.name+ " foi o "+u.name);
                }
            }
            u.color = Color.BLACK;
        }
        Q = new ArrayList<>();
    }
}
class Vertice {
    public String name;
    public ArrayList<Vertice> neighbors;
    public Color color = Color.WHITE;
    public Vertice parent = null;
    public Float d = Float.POSITIVE_INFINITY;
    Vertice(String name){
        this.neighbors = new ArrayList<Vertice>();
        this.name = name;
    }
    public void addNeighbor(Vertice neighbor){
        this.neighbors.add(neighbor);
    }
    public int size(){
        return this.neighbors.size();
    }
    public void print(){
        System.out.println("Vertíce["+this.name+"]");
        System.out.println("Cor["+this.color+"]");
        System.out.println("Parent["+this.parent+"]");
        System.out.println("Distancia["+this.d+"]");
    }
    public void printNeighbors(){
        for(Vertice n : this.neighbors){
            System.out.println("<----VIZINHO---->");
            System.out.println("Vertíce["+n.name+"]");
            System.out.println("Cor["+n.color+"]");
            System.out.println("Parent["+n.parent+"]");
            System.out.println("Distancia["+n.d+"]");
        }
    }
}