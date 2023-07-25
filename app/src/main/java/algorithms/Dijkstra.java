package algorithms;

import java.util.ArrayList;
import java.util.Arrays;

/**
 This class is to serve as an implementation of Dijkstra's shortest path algorithm.
 The following link provides a video tutorial of the process and a visual of the first graph over
 which Dijkstra's algorithm will be tested:
 https://youtu.be/bZkzH5x0SKU

The following link provides an example of an implemented Dijkstra's algorithm:
 https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/

 Remember that the shortest path calculated will be a tree. This fact can be used to check the output of your algorithm.
 */
public class Dijkstra {
    private int[][] graph;
    private ArrayList<Integer> vertexVisited = new ArrayList<>();
    private ArrayList<Integer> vertexNotVisited = new ArrayList<>();
    private int[] distances;
    private int[] previousNode;


    /**
     * Constructor for the Dijkstra object.
     * @param graph The graph at which you would like to calculate the shortest path for.
     */
    public Dijkstra(int[][] graph)
    {
        this.graph = graph;
        distances = new int[graph.length];
        previousNode = new int[graph.length];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previousNode, -1);

        for(int i=0; i<graph.length; i++){
            vertexNotVisited.add(i);
        }
    }

    /**
    This method  calculates the shortest distance from the root node to all other nodes in the graph using Dijkstra's algorithm.
     @param root The root node from which the shortest distance to all other nodes must be calculated.
     */
    public int[] findShortestPath(int root)
    {
        distances[root] = 0;
        previousNode[root] = 0;
        int currentNode = root;
        int distanceSoFar = 0;

        //Loop whilst we have unvisited Vertices
        while(!vertexNotVisited.isEmpty()){

            //Get the list of the unvisited neighbours
            ArrayList<Integer> unvisitedNeighbours = getUnvisitedNeighbours(currentNode);

            //Update table
            updateTables(currentNode, unvisitedNeighbours, distanceSoFar);

            //Update our Visited and NotVisited lists
            vertexVisited.add(currentNode);
            vertexNotVisited.remove((Integer) currentNode);

            //Move to new node
            currentNode = nextUnvisitedVertex(currentNode);

            //Update our distanceSoFar variable
            if(currentNode != -1){
                distanceSoFar = distances[currentNode];
            }

        }

        return distances;
    }

    public ArrayList<Integer> getUnvisitedNeighbours(int currentNode){
        ArrayList<Integer> unvisitedNeighbours = new ArrayList<>();

        //If a node is a neighbour and is not visited, add it to the list of unvisited neighbours.
        for(int i=0; i< graph.length; i++){
            if(graph[i][currentNode] != 0 && vertexNotVisited.contains(i) == true){
                unvisitedNeighbours.add(i);
            }
        }

        return unvisitedNeighbours;
    }


    public void updateTables(int currentNode, ArrayList<Integer> unvisitedNeighbours, int distanceSoFar){

        //Loop through the unvisited neighbours.
        for(int i=0; i<unvisitedNeighbours.size(); i++){
            Integer neighbour = unvisitedNeighbours.get(i);
            int newDistance = distanceSoFar + graph[currentNode][neighbour]; //Calculate the distance to the new node
                                                                             //By keeping track of previous distance
                                                                             //And adding the new one to it

            //If the new calculated distance is smaller than the one currently stored in our table: update it.
            if(newDistance < distances[neighbour]){
                distances[neighbour] = newDistance; //Update distances table with our new distance.
                previousNode[neighbour] = currentNode; //Update previousNode table with our new and closer PreviousNode.
            }
        }
    }


    public int nextUnvisitedVertex(int currentNode){

        int smallestDistance = Integer.MAX_VALUE;
        int nextUnvisitedVertex = -1;

        //select the shortest distance in our table (skipping our root node)
        for(int j=0; j<distances.length; j++){

            // Loop through our distances to find the shortest path to the next unvisited node.
            // Set it as the new node.
            if(vertexNotVisited.contains(j) && distances[j] < smallestDistance && j != currentNode){
                smallestDistance = distances[j]; //Keep track of what the smallest distance to root is.
                nextUnvisitedVertex = j; //Keeps track of which UnvisitedVertex has the smallest distance to root.
            }
        }

        return nextUnvisitedVertex;
    }


}
