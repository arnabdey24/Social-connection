import java.util.List;
import java.util.Queue;
import java.util.Stack;

public interface GraphInterface<T> {


    /**
     * Adds a given vertex to this graph. If vertexLabel is null, it returns false.
     *
     * @param vertexLabel vertexLabel
     * @return true if added successfully, If vertexLabel is null, it returns false.
     */
    public boolean addVertex(T vertexLabel);


    /**
     * Removes a vertex
     * with the given vertexLabel from this graph and returns the removed
     * vertex. If vertex does not exist, it will return null.
     *
     * @param vertexLabel vertexLabel
     * @return true if successful, If vertex does not exist, it will return null.
     */
    public VertexInterface<T> removeVertex(T vertexLabel);

    /**
     * Adds a weighted
     * edge between two given distinct vertices that are currently in this
     * graph. The desired edge must not already be in the graph. The graph is
     * undirected graph.
     *
     * @param begin      a vertexLabel
     * @param end        another vertexLabel
     * @param edgeWeight Weight between them
     * @return true if successful, false otherwise
     */
    public boolean addEdge(T begin, T end, double edgeWeight);

    /**
     * Adds an unweighted edge between two
     * given distinct vertices that are currently in this graph. The desired
     * edge must not already be in the graph.
     *
     * @param begin a vertexLabel
     * @param end   another vertexLabel
     * @return true if successful, false otherwise
     */
    public boolean addEdge(T begin, T end);

    /**
     * Removes a
     * weighted edge between two given distinct vertices that are currently in
     * this graph. The desired edge must already be in the graph. It returns
     * true if the removal is successful, false otherwise.
     *
     * @param begin      a vertexLabel
     * @param end        another vertexLabel
     * @param edgeWeight Weight between them
     * @return true if successful, false otherwise
     */
    public boolean removeEdge(T begin, T end, double edgeWeight);

    /**
     * Removes a
     * weighted edge between two given distinct vertices that are currently in
     * this graph. The desired edge must already be in the graph. It returns
     * true if the removal is successful, false otherwise.
     *
     * @param begin a vertexLabel
     * @param end   another vertexLabel
     * @return true if successful, false otherwise
     */
    public boolean removeEdge(T begin, T end);

    /**
     * Sees whether an undirected edge
     * exists between two given vertices.
     *
     * @param begin a vertexLabel
     * @param end   another vertexLabel
     * @return true if there is an edge between them, false otherwise
     */
    public boolean hasEdge(T begin, T end);

    /**
     * This method returns the number of
     * Vertices in this graph.
     *
     * @return number of Vertices in this graph.
     */
    public int getNumberOfVertices();

    /**
     * This method returns the number of
     * undirected Edges in this graph.
     *
     * @return the number of undirected Edges in this graph.
     */
    public int getNumberOfEdges();

    /**
     * This method returns true, if this graph is
     * empty, false otherwise.
     *
     * @return true, if this graph is empty, false otherwise.
     */
    public boolean isEmpty();

    /**
     * This method returns the
     * list of all vertices in the graph. If the graph is empty, it returns
     * null.
     *
     * @return the list of all vertices in the graph. If the graph is empty, it returns null.
     */
    public List<VertexInterface<T>> getVertices();

    /**
     * clears the graph.
     */
    public void clear();

    /**
     * Performs a breadthFirst traversal of a graph and returns the queue that contains the result.
     *
     * @param origin source vertex
     * @return the queue that contains the result.
     */
    public Queue<T> getBreadthFirstTraversal(T origin);

    /**
     * returns the shortest distance between the origin and destination. If a
     * path does not exist, it returns the maximum integer (to simulate
     * infinity).
     *
     * @param origin      source vertex
     * @param destination destination vertex
     * @param path        stack that contain the path between them
     * @return the shortest distance between the origin and destination.
     * If a path does not exist, it returns the maximum integer (to simulate infinity).
     */
    public int getShortestPath(T origin, T destination, Stack<T> path);

}
