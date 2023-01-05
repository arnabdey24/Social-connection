import java.util.*;

/**
 * This generic class implements the GraphInterface. The implementation will
 * provide an undirected graph. The
 * class has attribute, which contains the collection of vertices the graph has.
 * This attribute is a collection of key
 * value pair, where key is the label of the vertex and the value is the vertex.
 * Note that this attribute can be any
 * data structure that implements the Map interface.
 * vertices - A dictionary of key (Vertex label), value (Vertex) pair
 *
 * @param <T> key type
 */
public class Graph<T> implements GraphInterface<T> {

    private Map<T, VertexInterface<T>> vertices;
    private int edgeCount;

    /**
     * initializes the graph with an empty graph structure.
     */
    public Graph() {
        vertices = new HashMap<>();
        edgeCount = 0;
    }

    @Override
    public boolean addVertex(T vertexLabel) {

        //Checking If vertexLabel is not null and there is no vertex exist with same label,
        // if so we can add a new vertex.
        if (vertexLabel != null && !vertices.containsKey(vertexLabel)) {
            vertices.put(vertexLabel, new Vertex<>(vertexLabel));
            return true;
        }
        return false;
    }

    @Override
    public VertexInterface<T> removeVertex(T vertexLabel) {

        //if there is a vertex exist with same vertexLabel
        if (vertices.containsKey(vertexLabel)) {

            //the vertex we want to remove
            VertexInterface<T> vertex = vertices.get(vertexLabel);

            //we'll iterate over all the vertex in the graph and remove edges that connected to the vertex we want to remove
            Iterator<VertexInterface<T>> iterator = vertex.getNeighborIterator();
            while (iterator.hasNext()) {
                VertexInterface<T> next = iterator.next();
                removeEdge(vertex.getLabel(), next.getLabel());
            }

            //finally, remove it from the hashmap
            return vertices.remove(vertexLabel);
        }

        return null;
    }

    @Override
    public boolean addEdge(T begin, T end, double edgeWeight) {

        //checking given vertices are distinct, and they are currently in this graph
        if (!begin.equals(end) && vertices.containsKey(begin) && vertices.containsKey(end)) {

            //as it is undirected graph we have to connect the vertices from both side
            boolean successful = vertices.get(begin).connect(vertices.get(end), edgeWeight) &&
                    vertices.get(end).connect(vertices.get(begin), edgeWeight);

            //if edge is created increment edgeCounter
            if (successful) {
                edgeCount++;
            }

            return successful;
        }

        return false;
    }

    @Override
    public boolean addEdge(T begin, T end) {

        //checking given vertices are distinct, and they are currently in this graph
        if (!begin.equals(end) && vertices.containsKey(begin) && vertices.containsKey(end)) {

            //as it is undirected graph we have to connect the vertices from both side
            boolean successful = vertices.get(begin).connect(vertices.get(end), 0) &&
                    vertices.get(end).connect(vertices.get(begin), 0);

            //if edge is created increment edgeCounter
            if (successful) {
                edgeCount++;
            }

            return successful;
        }

        return false;
    }

    @Override
    public boolean removeEdge(T begin, T end, double edgeWeight) {

        //checking given vertices are distinct, and they are currently in this graph
        if (!begin.equals(end) && vertices.containsKey(begin) && vertices.containsKey(end)) {

            //as it is undirected graph we have to disconnect the vertices from both side
            boolean successful = vertices.get(begin).disconnect(vertices.get(end), edgeWeight) &&
                    vertices.get(end).disconnect(vertices.get(begin), edgeWeight);

            //if edge is removed decrement edgeCounter
            if (successful) {
                edgeCount--;
            }

            return successful;
        }

        return false;
    }

    @Override
    public boolean removeEdge(T begin, T end) {

        //checking given vertices are distinct, and they are currently in this graph
        if (!begin.equals(end) && vertices.containsKey(begin) && vertices.containsKey(end)) {

            //as it is undirected graph we have to disconnect the vertices from both side
            boolean successful = vertices.get(begin).disconnect(vertices.get(end), 0) &&
                    vertices.get(end).disconnect(vertices.get(begin), 0);

            //if edge is removed decrement edgeCounter
            if (successful) {
                edgeCount--;
            }

            return successful;
        }

        return false;
    }

    @Override
    public boolean hasEdge(T begin, T end) {

        //checking given vertices are distinct, and they are currently in this graph
        if (!begin.equals(end) && vertices.containsKey(begin) && vertices.containsKey(end)) {

            //we'll iterate over all the Neighbor vertices to begin, and we'll check a neighbor is equal to end
            Iterator<VertexInterface<T>> iterator = vertices.get(begin).getNeighborIterator();
            while (iterator.hasNext()) {
                VertexInterface<T> next = iterator.next();

                //if equal fount return true
                if (next.getLabel().equals(end)) {
                    return true;
                }
            }

        }

        return false;
    }

    @Override
    public int getNumberOfVertices() {
        //hashmap size also describe number of vertices
        return vertices.size();
    }

    @Override
    public int getNumberOfEdges() {
        // edgeCount already count it
        return edgeCount;
    }

    @Override
    public boolean isEmpty() {
        //hashmap size also describe number of vertices
        return vertices.isEmpty();
    }

    @Override
    public List<VertexInterface<T>> getVertices() {
        // creating a list of all the values of hashmap and returning
        return new ArrayList<>(vertices.values());
    }

    @Override
    public void clear() {
        vertices.clear();
    }

    @Override
    public Queue<T> getBreadthFirstTraversal(T origin) {
        //for storing traversal result
        Queue<T> result = new LinkedList<>();

        //it'll be used to perform bfs
        Queue<T> queue = new LinkedList<>();

        //-------------bfs----------------------

        //step(1): add origin to the queue and mark it as visited
        queue.add(origin);
        vertices.get(origin).visit();

        //step(2): while queue is not empty do the following steps
        while (!queue.isEmpty()) {

            //step(2a): poll a vertex u
            T u = queue.poll();

            //do what we want to do
            result.add(u);

            //step(2b): visit and push all the neighbor vertex to u which are not already visited
            Iterator<VertexInterface<T>> iterator = vertices.get(u).getNeighborIterator();
            while (iterator.hasNext()) {
                VertexInterface<T> v = iterator.next();
                if (!v.isVisited()) {
                    v.visit();
                    queue.add(v.getLabel());
                }
            }
        }
        //-----------bfs end-------------------------

        return result;
    }

    @Override
    public int getShortestPath(T origin, T destination, Stack<T> path) {

        //restore visitation info
        for (T label : vertices.keySet()) {
            vertices.get(label).setCost(Integer.MAX_VALUE);
            vertices.get(label).unvisit();
        }


        //-----------------here we are doing the bfs again as graph is unweighted ----------------
        Queue<T> queue = new LinkedList<>();

        //step(1): add origin to the queue and mark it as visited
        queue.add(origin);
        vertices.get(origin).visit();

        //origin to origin cost is 0
        vertices.get(origin).setCost(0);

        //step(2): while queue is not empty do the following steps
        while (!queue.isEmpty()) {

            //step(2a): poll a vertex u
            VertexInterface<T> u = vertices.get(queue.poll());

            //step(2b): visit and push all the neighbor vertex to u which are not already visited
            Iterator<VertexInterface<T>> iterator = u.getNeighborIterator();
            while (iterator.hasNext()) {
                VertexInterface<T> v = iterator.next();
                if (!v.isVisited()) {
                    v.visit();

                    //additionally, set cost of v=cost of parent of v + 1
                    v.setCost(u.getCost() + 1);
                    //u is the parent of v
                    v.setPredecessor(u);

                    queue.add(v.getLabel());
                }
            }
        }

        //storing path in the stack if there is a path
        if (vertices.get(destination).getCost() != Integer.MAX_VALUE) {

            //we'll push starting from destination, then destination's parent, then destination's parent's parent and so on
            path.push(destination);
            while (vertices.get(path.peek()).hasPredecessor() && !vertices.get(path.peek()).getLabel().equals(origin)) {
                path.add(vertices.get(path.peek()).getPredecessor().getLabel());
            }
        }

        return (int) vertices.get(destination).getCost();
    }
}
