//Santa Claus lives in Lapland. In his free time when it is not around New Year, while the elves are working on toys for next year, Santa Claus has a hobby. He likes to raise fish. 
//But he does this in natural lakes. The ponds are interconnected by creeks, and the creeks flow from one pond to another. Fish from one lake can freely go to another lake through the streams. 
//Every spring Santa Claus likes to stock the ponds with new fish. Your task is to tell Santa Claus, if he releases new fishes into pond X, how many other ponds the fishes will be able to reach on their own, 
//so that there is no need for him to stock those ponds himself.

//Input: In the first line of the input, the number N < 15 is given, the number of ponds. In the second line, the number U < 20 is given, the number of rivers between the lakes. 
//In the following U lines, pairs of 2 numbers R and Q are given, which means there is a river flowing from R to Q, where R and Q are pond numbers. 
//In the last line, the number L is given, in which pond Santa Claus will release the fish.

//Output: The number of ponds that will be stocked except the initial one is printed out.
//Example:
//Input: 
//11 
//19 
//3 3 
//7 8 
//7 3 
//1 7 
//0 0 
//7 2 
//6 3 
//2 0 
//0 9 
//6 10 
//1 2 
//2 8 
//5 7 
//4 3 
//10 4 
//3 9 
//7 10 
//9 4 
//4 10 
//7 
//Output: 
//7


import java.io.IOException;
import java.util.*;

class AdjacencyListGraph<T> {
    private Map<T, Set<T>> adjacencyList;

    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashSet<>());
        }
    }

    // Remove a vertex from the graph
    public void removeVertex(T vertex) {
        // Remove the vertex from all adjacency lists
        for (Set<T> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        // Remove the vertex's own entry in the adjacency list
        adjacencyList.remove(vertex);
    }

    // Add an edge to the graph
    public void addEdge(T source, T destination) {
        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).add(destination);
    }

    // Remove an edge from the graph
    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }
    }

    // Get all neighbors of a vertex
    public Set<T> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashSet<>());
    }

    public int DFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        DFSUtil(startVertex, visited);
        return visited.size() - 1;
    }

    private void DFSUtil(T vertex, Set<T> visited) {
        // Mark the current node as visited and print it
        visited.add(vertex);
        //System.out.print(vertex + " ");

        // Recur for all the vertices adjacent to this vertex
        for (T neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                DFSUtil(neighbor, visited);
            }
        }
    }


    public void DFSNonRecursive(T startVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(startVertex);
        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                System.out.print(vertex + " ");
                for (T neighbor : getNeighbors(vertex)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public void printPath(T source, T destination) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(source);
        visited.add(source);
        while (!stack.isEmpty() && !stack.peek().equals(destination)) {
            T vertex = stack.peek();

            boolean f = true;
            for(T neighbor: getNeighbors(vertex)) {
                if(!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                    f = false;
                    break;
                }
            }

            if(f) {
                stack.pop();
            }
        }

        Stack<T> path = new Stack<>();

        while(!stack.isEmpty()) {
            path.push(stack.pop());
        }

        while(!path.isEmpty()) {
            System.out.print(path.pop() + " ");
        }
    }

    public void BFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            System.out.print(vertex + " ");

            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    // DFS utility function used for topological sorting
    private void topologicalSortUtil(T vertex, Set<T> visited, Stack<T> stack) {
        visited.add(vertex);
        for (T neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                topologicalSortUtil(neighbor, visited, stack);
            }
        }
        stack.push(vertex);
    }

    public List<T> topologicalSort() {
        Stack<T> stack = new Stack<>();
        Set<T> visited = new HashSet<>();

        for (T vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                topologicalSortUtil(vertex, visited, stack);
            }
        }

        List<T> order = new ArrayList<>();
        while (!stack.isEmpty()) {
            order.add(stack.pop());
        }
        return order;
    }


    @Override
    public String toString() {
        String ret = new String();
        for (Map.Entry<T, Set<T>> vertex : adjacencyList.entrySet())
            ret += vertex.getKey() + ": " + vertex.getValue() + "\n";
        return ret;
    }



}

public class SantaClaus{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int U = sc.nextInt();

        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();

        for(int i = 0; i < U; i++){
            int R = sc.nextInt();
            int Q = sc.nextInt();
            graph.addEdge(R, Q);
        }

        int L = sc.nextInt();

        System.out.println(graph.DFS(L));
        //System.out.println(graph);
    }
}
