import graph.Edge;
import graph.Graph;
import graph.GraphException;
import prim.PrimAlgorithm;
import priorityqueue.PriorityQueueException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
//percorso: data/italian_dist_graph.csv

public class Prim {
  public static void main(String[] args) throws GraphException, IOException, PriorityQueueException {
    double totWeight=0.0;
    int numEdges=0;
    Graph<String, Double> graph;

    // leggi i dati CSV del grafo dal percorso in args[0]
    System.out.println("Caricamento del file csv...");
    String csvFilePath="";
    try{
      csvFilePath = args[0];
      graph=readFromCsv(csvFilePath);
    } catch (ArrayIndexOutOfBoundsException e){
      System.out.println("Specificare il percorso del file CSV come argomento.");
      return;
    }
    System.out.println("File caricato");
    System.out.println();

    // calcola la minima foresta ricoprente con l'algoritmo di Prim
    System.out.println("Svolgimento di Prim...");
    PrimAlgorithm<String, Double> primAlgorithm=new PrimAlgorithm<>();
    Map<String, Edge<String, Double>> mst=  primAlgorithm.prim(graph, "abadia a isola", (o1, o2)->o1.getLabel().compareTo(o2.getLabel()));
    System.out.println("Prim Concluso");
    System.out.println();

    // scrivi su standard output una descrizione della foresta calcolata come CSV con formato analogo a quello in input
    // ai fini della correzione automatica, scrivere gli archi in output di modo che la partenza sia lessicograficamente minore della destinazione
    for (Map.Entry<String, Edge<String, Double>> entry : mst.entrySet()) {
      if(entry.getValue().getStart().compareTo(entry.getValue().getEnd())>1) {
        System.out.println("Arco: " + entry.getValue().getEnd()+", "+ entry.getValue().getStart()+", "+entry.getValue().getLabel());
      }
      else{
        System.out.println("Arco: " + entry.getValue().getStart()+", "+ entry.getValue().getEnd()+", "+entry.getValue().getLabel());
      }
      System.out.println();
      Edge<String, Double> edge = entry.getValue();

      if(!entry.getValue().getStart().equals(entry.getValue().getEnd()))
        numEdges ++;
      totWeight+=entry.getValue().getLabel();
    }



    //risultati finali
    System.out.println("Risultati finali:");
    System.out.println("numero totale nodi: "+ graph.numNodes());
    System.out.println("numero totale archi: "+ numEdges);
    System.out.println("peso totale: "+ totWeight/1000 + " km");

  }

  public static Graph<String, Double> readFromCsv(String path)
          throws IOException, NumberFormatException {
    final String delimiter = ",";
    String line = "";
    Graph<String, Double> graph = new Graph<>(false, true);
    FileReader file=null;
    try {
      file = new FileReader(path);
      BufferedReader buffer = new BufferedReader(file);
      while ((line = buffer.readLine()) != null) {
        String[] token = line.split(delimiter);
        graph.addEdge(token[0], token[1], Double.valueOf(token[2]));
      }
      buffer.close();
    }
    catch (FileNotFoundException e){ System.out.println("file non trovato");}
    return graph;
  }


}
