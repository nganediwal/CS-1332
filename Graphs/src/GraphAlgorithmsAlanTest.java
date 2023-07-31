import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class GraphAlgorithmsAlanTest {
    private static final int TIMEOUT = 200;
    private Graph<Integer> directedGraph;
    private Graph<Character> undirectedGraph;

    @Before
    public void setUp() {
        directedGraph = createDirectedGraph();
        undirectedGraph = createUndirectedGraph();
    }

    /**
     * Creates a directed graph.
     * The graph is depicted in the pdf.
     *
     * @return the completed graph
     */
    private Graph<Integer> createDirectedGraph() {
        Set<Vertex<Integer>> vertices = new HashSet<>();
        for (int i = 1; i <= 7; i++) {
            vertices.add(new Vertex<>(i));
        }

        Set<Edge<Integer>> edges = new LinkedHashSet<>();
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 0));
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(3), 0));
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(4), 0));
        edges.add(new Edge<>(new Vertex<>(3), new Vertex<>(5), 0));
        edges.add(new Edge<>(new Vertex<>(4), new Vertex<>(6), 0));
        edges.add(new Edge<>(new Vertex<>(5), new Vertex<>(4), 0));
        edges.add(new Edge<>(new Vertex<>(5), new Vertex<>(7), 0));
        edges.add(new Edge<>(new Vertex<>(7), new Vertex<>(6), 0));

        return new Graph<>(vertices, edges);
    }

    /**
     * Creates an undirected graph.
     * The graph is depicted in the pdf.
     *
     * @return the completed graph
     */
    private Graph<Character> createUndirectedGraph() {
        Set<Vertex<Character>> vertices = new HashSet<>();
        for (int i = 65; i <= 70; i++) {
            vertices.add(new Vertex<>((char) i));
        }

        Set<Edge<Character>> edges = new LinkedHashSet<>();
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 7));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 7));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 5));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('A'), 5));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 2));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 2));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('D'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('A'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 1));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 1));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('E'), 3));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('B'), 3));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('F'), 8));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('B'), 8));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('F'), 6));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('E'), 6));

        return new Graph<>(vertices, edges);
    }

    @Test
    public void testDfs1() {
        expectIllegalArgs(() ->
                GraphAlgorithms.dfs(null, directedGraph)
        );
        expectIllegalArgs(() ->
                GraphAlgorithms.dfs(null, null)
        );
        expectIllegalArgs(() ->
                GraphAlgorithms.dfs(new Vertex<>(1), null)
        );
        expectIllegalArgs(() ->
                GraphAlgorithms.dfs(new Vertex<>(999), directedGraph)
        );
    }

    @Test
    public void testDfs2() {
        HashSet<Vertex<Integer>> v = new HashSet<>();
        LinkedHashSet<Edge<Integer>> e = new LinkedHashSet<>();

        v.add(new Vertex<>(0));
        e.add(new Edge<>(new Vertex<>(0), new Vertex<>(0), 0));

        for (int i = 1; i < 100; i++) {
            v.add(new Vertex<>(i));
            e.add(new Edge<>(new Vertex<>(i - 1), new Vertex<>(i), 0));
            e.add(new Edge<>(new Vertex<>(i), new Vertex<>(i - 1), 0));
            Graph<Integer> g = new Graph<>(v, e);
            for (Vertex<Integer> u : v) {
                //System.out.print("starting at " + u.getData() + " - ");
                List<Vertex<Integer>> path = GraphAlgorithms.dfs(u, g);
                for (Vertex<Integer> w : v) {
                    assertTrue(path.contains(w));
                }
                /*for (Vertex<Integer> w : path) {
                    System.out.print(w.getData() + ", ");
                }*/

                if (u.getData() < i) {
                    assertTrue(
                            path.get(u.getData() + 1).getData().equals(u.getData() + 1)
                    );
                }
                //System.out.println();
            }
        }
    }

    @Test
    public void testDijkstras1() {
        expectIllegalArgs(() ->
                GraphAlgorithms.dijkstras(null, directedGraph)
        );
        expectIllegalArgs(() ->
                GraphAlgorithms.dijkstras(null, null)
        );
        expectIllegalArgs(() ->
                GraphAlgorithms.dijkstras(new Vertex<>(1), null)
        );
        expectIllegalArgs(() ->
                GraphAlgorithms.dijkstras(new Vertex<>(999), directedGraph)
        );
    }

    @Test
    public void testPrims1() {
        expectIllegalArgs(() ->
                GraphAlgorithms.prims(null, directedGraph)
        );
        expectIllegalArgs(() ->
                GraphAlgorithms.prims(null, null)
        );
        expectIllegalArgs(() ->
                GraphAlgorithms.prims(new Vertex<>(1), null)
        );
        expectIllegalArgs(() ->
                GraphAlgorithms.prims(new Vertex<>(999), directedGraph)
        );
    }

    @Test
    public void testPrims2() {
        HashSet<Vertex<Integer>> v = new HashSet<>();
        v.add(new Vertex<>(0));
        v.add(new Vertex<>(1));

        HashSet<Edge<Integer>> e = new HashSet<>();

        Graph<Integer> g = new Graph<>(v, e);
        assertNull(GraphAlgorithms.prims(v.iterator().next(), g));

        e.add(new Edge<>(new Vertex<>(0), new Vertex<>(1), 0));
        e.add(new Edge<>(new Vertex<>(1), new Vertex<>(0), 0));
        g = new Graph<>(v, e);
        assertNotNull(GraphAlgorithms.prims(v.iterator().next(), g));

        v.add(new Vertex<>(2));
        g = new Graph<>(v, e);
        assertNull(GraphAlgorithms.prims(v.iterator().next(), g));
    }

    @Test
    public void testPrims3() {
        HashSet<Vertex<Integer>> v = new HashSet<>();
        v.add(new Vertex<>(0));
        v.add(new Vertex<>(1));
        v.add(new Vertex<>(2));

        for (int i = 0; i < 1000; i++) {
            HashSet<Edge<Integer>> e = new HashSet<>();
            int a = (new Random()).nextInt(10);
            int b = (new Random()).nextInt(10);
            int c = (new Random()).nextInt(10);
            int min = Math.min(a, c);
            if (min == a) {
                min += Math.min(b, c);
            } else {
                min += Math.min(a, b);
            }

            e.add(new Edge<>(new Vertex<>(0), new Vertex<>(1), a));
            e.add(new Edge<>(new Vertex<>(1), new Vertex<>(0), a));
            e.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), b));
            e.add(new Edge<>(new Vertex<>(2), new Vertex<>(1), b));
            e.add(new Edge<>(new Vertex<>(2), new Vertex<>(0), c));
            e.add(new Edge<>(new Vertex<>(0), new Vertex<>(2), c));

            Graph<Integer> g = new Graph<>(v, e);
            Set<Edge<Integer>> mst = GraphAlgorithms.prims(v.iterator().next(), g);
            assertEquals(4, mst.size());
            int pathweight = 0;
            for (Edge<Integer> edge : mst) {
                pathweight += edge.getWeight();
            }
            assertEquals(min, pathweight / 2);
        }
    }

    public void expectIllegalArgs(Lambdable l) {
        Exception caught = null;
        try {
            l.run();
        } catch (Exception e) {
            caught = e;
        }
        assertNotNull(caught);
        assertTrue(caught instanceof IllegalArgumentException);
    }

    private interface Lambdable {
        void run();
    }
}