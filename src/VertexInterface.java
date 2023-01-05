import java.util.Iterator;

public interface VertexInterface<T> {

    /**
     * Gets this vertex’s label.
     *
     * @return label
     */
    public T getLabel();

    /**
     * Returns the number of neighbors of this vertex.
     *
     * @return NumberOfNeighbors
     */
    public int getNumberOfNeighbors();

    /**
     * Marks this vertex as visited
     */
    public void visit();

    /**
     * Removes this vertex’s visited mark
     */
    public void unvisit();

    /**
     * Check visited or not.
     *
     * @return Returns true if the vertex is visited, false otherwise.
     */
    public boolean isVisited();


    /**
     * Connects this vertex and endVertex with a weighted edge. The two
     * vertices cannot be the same, and must not already have this edge between
     * them. Two vertices are equal (same)if their labels are equal (same).
     * Returns true if the connection is successful, false otherwise.
     *
     * @param endVertex  vertex to connect .
     * @param edgeWeight weight between them.
     * @return true if the connection is successful, false otherwise.
     */
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight);

    /**
     * Connects this vertex and endVertex with a unweighted edge. The two vertices
     * cannot be the same, and must not already have this edge between them. Two
     * vertices are equal (same)if their labels are equal (same). Returns true if
     * the connection is successful, false otherwise.
     *
     * @param endVertex vertex to connect .
     * @return true if the connection is successful, false otherwise.
     */
    public boolean connect(VertexInterface<T> endVertex);

    /**
     * Disconnects this vertex from a given vertex with a weighted edge, i.e.,
     * removes the edge. The Edge should exist in order to be disconnected. Returns
     * true if the disconnection is successful, false otherwise
     *
     * @param endVertex  vertex to connect .
     * @param edgeWeight weight between them.
     * @return true if the disconnection
     * is successful, false otherwise.
     */
    public boolean disconnect(VertexInterface<T> endVertex, double edgeWeight);

    /**
     * Disconnects this vertex from a given vertex with an unweighted edge. The Edge
     * should exist in order to be disconnected. Returns true if the disconnection
     * is successful, false otherwise.
     *
     * @param endVertex vertex to connect .
     * @return true if the disconnection
     * is successful, false otherwise.
     */
    public boolean disconnect(VertexInterface<T> endVertex);

    /**
     * creates an iterator of this vertex's neighbors by following all edges that
     * begin at this vertex.
     *
     * @return Iterator of the Neighbor list
     */
    public Iterator<VertexInterface<T>> getNeighborIterator();

    /**
     * creates an iterator of the weights of the edges this vertex's neighbors by
     * following all edges that begin at this vertex.
     *
     * @return Iterator of the Weight list
     */
    public Iterator<Double> getWeightIterator();

    /**
     * Sees whether this vertex has at least one neighbor.
     *
     * @return true if it has at least one neighbor, false otherwise.
     */
    public boolean hasNeighbor();

    /**
     * Gets an unvisited neighbor, if any, of this vertex.
     *
     * @return Unvisited Neighbor vertex if have any otherwise, null.
     */
    public VertexInterface<T> getUnvisitedNeighbor();

    /**
     * Records the previous vertex on a path to this vertex.
     *
     * @param predecessor Predecessor vertex
     */
    public void setPredecessor(VertexInterface<T> predecessor);

    /**
     * Gets the recorded predecessor of this vertex.
     *
     * @return Predecessor vertex
     */
    public VertexInterface<T> getPredecessor();

    /**
     * Sees whether a predecessor was recorded for this vertex
     *
     * @return true if it has a predecessor.
     */
    public boolean hasPredecessor();

    /**
     * Records the cost of a path to this vertex.
     *
     * @param newCost new cost to set
     */
    public void setCost(double newCost);

    /**
     * Returns the cost of a path to this vertex.
     *
     * @return cost
     */
    public double getCost();

}
