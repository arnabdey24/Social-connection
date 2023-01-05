import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Vertex<T> implements VertexInterface<T> {
    // Represents the label of the vertex.
    private T label;
    // Stores if the vertex is visited or not, true if visited.
    private Boolean visited;
    // on path to this vertex
    private VertexInterface<T> previousVertex;
    // of path to this vertex
    private double cost;
    // list of edges to neighbors. Note that there is an Edge class used.
    private List<Edge<T>> edgeList;

    /**
     * initializes label to the given value, visited -> false, cost -> 0.0,
     * previousVertex ->null, and the edgeList to a default list.
     */
    public Vertex(T vertexLabel) {
        label = vertexLabel;
        visited = false;
        cost = 0.0;
        previousVertex = null;
        edgeList = new ArrayList<>();
    }

    @Override
    public T getLabel() {
        return label;
    }

    @Override
    public int getNumberOfNeighbors() {
        // edgeList size also refer to number of neighbors
        return edgeList.size();
    }

    @Override
    public void visit() {
        visited = true;
    }

    @Override
    public void unvisit() {
        visited = false;
    }

    @Override
    public boolean isVisited() {
        return visited;
    }

    @Override
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight) {
        Edge<T> newEdge = new Edge<>(endVertex, edgeWeight);

        //The two vertices cannot be the same, and must not already have this edge between them.
        if (!endVertex.getLabel().equals(label) && !edgeList.contains(newEdge)) {
            edgeList.add(newEdge);

            return true;
        }

        return false;
    }

    @Override
    public boolean connect(VertexInterface<T> endVertex) {
        Edge<T> newEdge = new Edge<>(endVertex, 0);

        //The two vertices cannot be the same, and must not already have this edge between them.
        if (!endVertex.getLabel().equals(label) && !edgeList.contains(newEdge)) {
            edgeList.add(newEdge);

            return true;
        }

        return false;
    }

    @Override
    public boolean disconnect(VertexInterface<T> endVertex, double edgeWeight) {
        Edge<T> newEdge = new Edge<>(endVertex, edgeWeight);

        //The Edge should exist in order to be disconnected
        if (edgeList.contains(newEdge)) {
            edgeList.remove(newEdge);
            return true;
        }

        return false;
    }

    @Override
    public boolean disconnect(VertexInterface<T> endVertex) {
        Edge<T> newEdge = new Edge<>(endVertex, 0);

        //The Edge should exist in order to be disconnected
        if (edgeList.contains(newEdge)) {
            edgeList.remove(newEdge);
            return true;
        }

        return false;
    }

    @Override
    public Iterator<VertexInterface<T>> getNeighborIterator() {
        //creating a list with all Neighbor
        List<VertexInterface<T>> neighbors = new ArrayList<>();
        for (Edge<T> edge : edgeList) {
            neighbors.add(edge.getV());
        }

        //and returning a listIterator of created list
        return neighbors.listIterator();
    }

    @Override
    public Iterator<Double> getWeightIterator() {
        //creating a list with all Weight
        List<Double> weights = new ArrayList<>();
        for (Edge<T> edge : edgeList) {
            weights.add(edge.getW());
        }

        //and returning a listIterator of created list
        return weights.listIterator();
    }

    @Override
    public boolean hasNeighbor() {
        return !edgeList.isEmpty();
    }

    @Override
    public VertexInterface<T> getUnvisitedNeighbor() {
        //iterate over edgeList to search neighbor vertex that is not yet visited and return is
        for (Edge<T> edge : edgeList) {
            if (!edge.getV().isVisited()) {
                return edge.getV();
            }
        }
        return null;
    }

    @Override
    public void setPredecessor(VertexInterface<T> predecessor) {
        previousVertex = predecessor;
    }

    @Override
    public VertexInterface<T> getPredecessor() {
        return previousVertex;
    }

    @Override
    public boolean hasPredecessor() {
        // if previous vertex ot null the there is a Predecessor
        return previousVertex != null;
    }

    @Override
    public void setCost(double newCost) {
        cost = newCost;
    }

    @Override
    public double getCost() {
        return cost;
    }

    /**
     * Graph Edge that connected two vertices.
     *
     * @param <T> graph key type.
     */
    protected class Edge<T> {

        private VertexInterface<T> v;
        private double w;

        /**
         * Default constructor, create a new edge with w weight.
         *
         * @param v endVertex
         * @param w weight
         */
        public Edge(VertexInterface<T> v, double w) {
            this.v = v;
            this.w = w;
        }

        /**
         * Getter method for endVertex
         *
         * @return endVertex
         */
        public VertexInterface<T> getV() {
            return v;
        }

        /**
         * Getter method for weight
         *
         * @return weight
         */
        public double getW() {
            return w;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object obj) {
            return v.getLabel().equals(((Edge<T>) obj).getV().getLabel()) && w == ((Edge<T>) obj).getW();
        }

        @Override
        public String toString() {
            return "Edge{" +
                    ", v=" + v +
                    ", w=" + w +
                    '}';
        }
    }
}
