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
        int currentNode = root;
        int smallestDistance = Integer.MAX_VALUE;
        int smallestDistanceIndex = 0;
        int distanceSoFar = 0;

        // loop whilst we have unvisited Vertices
        while(!vertexNotVisited.isEmpty()){

            //update table
            updateTables(currentNode, distanceSoFar);

            //select the shortest distance in our table (skipping our root node)
            smallestDistanceIndex = findUnvisitedNeighbour(currentNode, smallestDistance, smallestDistanceIndex);

            //keep track of the smallest distance to update table next
            smallestDistance = distances[smallestDistanceIndex];
            distanceSoFar += smallestDistance;

            //Put our current node in Visited and remove it from notVisited
            vertexVisited.add(currentNode);
            vertexNotVisited.remove(currentNode);

            //move to our new node
            currentNode = smallestDistanceIndex;

            //reset smallestDistance
            smallestDistance = Integer.MAX_VALUE;

        }

        return distances;
    }


    public void updateTables(int currentNode, int distanceSoFar){ //rewrite over old node distance

        //get distances of all unvisited neighbours and update table
        for(int i=0; i<graph.length; i++){

            //find the distance to the unvisited neighbour
            if(graph[currentNode][i] != 0 && vertexNotVisited.contains(i)){

                //compared to stored distance in table
                //if the new distance is smaller than the previously recorded one, update table.
                if(graph[currentNode][i] < distances[i]){
                    distances[i] = graph[currentNode][i] + distanceSoFar;
                    previousNode[i] = currentNode;
                }
            }
        }

    }


    public int findUnvisitedNeighbour(int currentNode, int smallestDistance, int smallestDistanceIndex){

        //select the shortest distance in our table (skipping our root node)
        for(int j=0; j<distances.length; j++){

            //is node is unvisited, is not the root, update
            if(vertexNotVisited.contains(j) && distances[j] < smallestDistance && j != currentNode && distances[j] != 0){
                smallestDistance = distances[j];
                smallestDistanceIndex = j;
            }
        }

        return smallestDistanceIndex;
    }


}
