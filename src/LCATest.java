import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;



@RunWith(JUnit4.class)
public class LCATest {



	@Test
//test empty trees
	public void testEmpty() {

		LCA<Integer, Integer> lca = new LCA<Integer, Integer>();
		assertFalse("Checking if empty tree contains key ", lca.existsCheck(3));
		assertNull("Checking if we can get value from empty tree ", lca.getNode(0));
		assertEquals("Checking if we can find lca for empty tree ",null, lca.getLCA(0,0));
		assertEquals("Checking if we can get node for empty tree", null, lca.getNode(3));



	}

	@ Test
//testing if node gets out on tree
	public void putNodeTest() {


		LCA<Integer, Integer> lca = new LCA<Integer, Integer>();

		lca.putNode(1, 1);
		assertTrue("Checking if tree contains key ", lca.existsCheck(1));
		lca.putNode(2,  2);
		lca.putNode(100, 345555);
		assertTrue("Checking if tree contains key ", lca.existsCheck(100));
		assertFalse("Checking if tree contains key ", lca.existsCheck(0));
		lca.putNode(null,  null);
		assertFalse("Checking if tree contains a null key ", lca.existsCheck(null));




	}

	@ Test
//checking you can get specific nodes
	public void getNodeTest() {
		LCA<Integer, Integer> lca = new LCA<Integer, Integer>();

		lca.putNode(1, 1);
		lca.putNode(2, 2);
		lca.putNode(3, 5);
		lca.putNode(4, 4);
		lca.putNode(5, 5);
		lca.putNode(6, 6);
		lca.putNode(7, 7);
		lca.putNode(null,  null);

		assertEquals("Checking if we can get node that exists", (Integer)5, lca.getNode(3));
		assertEquals("Checking if we can get node that does not exist", null, lca.getNode(8));
		assertEquals("Checking if we can find null node", null, lca.getNode(null));


	}
//checking if node exists
	@ Test
	public void existsTest() {
		LCA<Integer, Integer> lca = new LCA<Integer, Integer>();
		assertFalse("Checking if any node exists in empty tree", lca.existsCheck(3));
		lca.putNode(1, 1);
		lca.putNode(2, 2);
		lca.putNode(3, 5);
		lca.putNode(4, 4);
		lca.putNode(5, 5);
		assertTrue("Checking if node exists in  tree", lca.existsCheck(5));
		assertTrue("Checking if node exists in  tree", lca.existsCheck(4));
		assertFalse("Checking if node exists in tree", lca.existsCheck(9));
	}
//get Lowest common ancestor test
		@ Test
	public void getLCATest() {
		LCA<Integer, Integer> lca = new LCA<Integer, Integer>();
		lca.putNode(4, 4);
		lca.putNode(2, 2);
		lca.putNode(3, 3);
		lca.putNode(7, 7);
		lca.putNode(6, 6);
		lca.putNode(8, 8);
		lca.putNode(9, 9);
		lca.putNode(10, 10);
		assertEquals("Checking two common ancestors, where LCA is the root", (Integer)4, lca.getLCA(3, 9));
		assertNull("Checking where one inputNode does not exist in the tree", lca.getLCA(4, 5));
		assertEquals("Checking two common ancestors, where LCA is not the root", (Integer)7, lca.getLCA(6, 10));
	}

	@Test
	public void testPrint(){
		//checks to see if the putNode function operates as needed
		LCA<Integer, Integer> lca = new LCA<Integer, Integer>();
		lca.putNode(4, 4);
		lca.putNode(3, 3);
		lca.putNode(7, 7);
		lca.putNode(6, 6);
		lca.putNode(9, 9);
		lca.putNode(10, 10);

		assertEquals("Testing that the keys were correctly inserted into the tree", "((()3())4((()6())7(()9(()10()))))" , lca.treeToString());
		lca.putNode(20, 20);
		lca.putNode(null, null);
		assertEquals("Testing that the keys were correctly inserted into the tree", "((()3())4((()6())7(()9(()10(()20())))))" , lca.treeToString());


	}
	//checking Dag is correct
	@Test
	public void testDirectedAcyclicGraph()
	{
		DirectedAcyclicGraph DirectedAcyclicGraph = new DirectedAcyclicGraph(10);

		DirectedAcyclicGraph.addEdge(1, 2);
		DirectedAcyclicGraph.addEdge(2, 4);
		DirectedAcyclicGraph.addEdge(2, 5);
		DirectedAcyclicGraph.addEdge(4, 6);
		DirectedAcyclicGraph.addEdge(4, 7);

		assertEquals("checking for indegree",1, DirectedAcyclicGraph.indegree(4));
		assertEquals("checking fo routdegree",2, DirectedAcyclicGraph.outdegree(4));
		assertEquals("Checking edges", 5, DirectedAcyclicGraph.E());
		assertEquals("checking vertices", 10, DirectedAcyclicGraph.V());
		String adj = "[6, 7]";
		assertEquals(adj, DirectedAcyclicGraph.adj(4).toString());
	}

	@Test(expected=Exception.class)
	public void exceptionTest(){


		DirectedAcyclicGraph DirectedAcyclicGraph = new DirectedAcyclicGraph(-5);
	}
//test adding edges to the DAG works
	@Test
	public void addEdge()
	{
		DirectedAcyclicGraph DirectedAcyclicGraph = new DirectedAcyclicGraph(5);

		DirectedAcyclicGraph.addEdge(1,2);

		//As negative, will print a system error and not addEdge
		DirectedAcyclicGraph.addEdge(-1, -6);

		//This will not addEdge as 12 > 5
		DirectedAcyclicGraph.addEdge(3, 12);

		assertEquals(1, DirectedAcyclicGraph.E());
	}

	@Test
	public void testIndegree()
	{
		DirectedAcyclicGraph DirectedAcyclicGraph = new DirectedAcyclicGraph(5);

		DirectedAcyclicGraph.addEdge(1, 2);
		DirectedAcyclicGraph.addEdge(2, 4);
		DirectedAcyclicGraph.addEdge(3, 3);

		assertEquals(1, DirectedAcyclicGraph.indegree(3));
		assertEquals(-1, DirectedAcyclicGraph.indegree(5));
	}

	@Test
	public void testOutdegree()
	{
		DirectedAcyclicGraph DirectedAcyclicGraph = new DirectedAcyclicGraph(5);

		DirectedAcyclicGraph.addEdge(1, 2);
		DirectedAcyclicGraph.addEdge(2, 4);
		DirectedAcyclicGraph.addEdge(3, 3);

		assertEquals(1, DirectedAcyclicGraph.outdegree(3));
		assertEquals(-1, DirectedAcyclicGraph.outdegree(5));
	}

	@Test
	public void testE(){

		DirectedAcyclicGraph DirectedAcyclicGraph = new DirectedAcyclicGraph(5);

		DirectedAcyclicGraph.addEdge(1, 2);
		DirectedAcyclicGraph.addEdge(2, 3);
		DirectedAcyclicGraph.addEdge(3, 1);

		assertEquals("testing E()",3, DirectedAcyclicGraph.E());
	}

	@Test
	public void testV()
	{
		DirectedAcyclicGraph DirectedAcyclicGraph = new DirectedAcyclicGraph(6);
		assertEquals("checking creation of DirectedAcyclicGraph", 6, DirectedAcyclicGraph.V());
	}


	@Test
	public void testAdj()
	{
		DirectedAcyclicGraph DirectedAcyclicGraph = new DirectedAcyclicGraph(5);

		DirectedAcyclicGraph.addEdge(1, 2);
		DirectedAcyclicGraph.addEdge(2, 4);
		DirectedAcyclicGraph.addEdge(3, 3);
		DirectedAcyclicGraph.addEdge(4, 3);

		String adj = "[4]";
		assertEquals(adj, DirectedAcyclicGraph.adj(2).toString());
	}

	@Test
	public void testforCycle()
	{
		DirectedAcyclicGraph DirectedAcyclicGraph = new DirectedAcyclicGraph(10);

		DirectedAcyclicGraph.addEdge(0, 1);
		DirectedAcyclicGraph.addEdge(1, 2);
		DirectedAcyclicGraph.addEdge(2, 0);
		DirectedAcyclicGraph.addEdge(2, 3);
		DirectedAcyclicGraph.addEdge(3, 4);

		DirectedAcyclicGraph.findCycle(0);

		assertTrue("checking for cycle in cyclic DirectedAcyclicGraph", DirectedAcyclicGraph.hasCycle());
	}

	@Test
	public void testDAG()
	{
		DirectedAcyclicGraph DirectedAcyclicGraph = new DirectedAcyclicGraph(10);

		DirectedAcyclicGraph.addEdge(1, 2);
		DirectedAcyclicGraph.addEdge(2, 4);
		DirectedAcyclicGraph.addEdge(3, 3);

		DirectedAcyclicGraph.findCycle(1);
		assertFalse("Testing on an acyclic DirectedAcyclicGraph", DirectedAcyclicGraph.hasCycle());
	}

	@Test
	public void testLCA()
	{
		DirectedAcyclicGraph DirectedAcyclicGraph = new DirectedAcyclicGraph(10);


		DirectedAcyclicGraph.addEdge(1, 2);
		DirectedAcyclicGraph.addEdge(1, 3);
		DirectedAcyclicGraph.addEdge(2, 4);
		DirectedAcyclicGraph.addEdge(2, 5);
		DirectedAcyclicGraph.addEdge(5, 7);
		DirectedAcyclicGraph.addEdge(3, 6);
		assertEquals("Assert LCA(2,3) is 1", DirectedAcyclicGraph.findLCA(2, 3), 1);
		assertEquals("Assert LCA(4,7) is 2", DirectedAcyclicGraph.findLCA(4, 7), 2);
		assertEquals("Assert LCA(7,6) is 1", DirectedAcyclicGraph.findLCA(7, 6), 1);
	}

	@Test
	public void testNoCommonAncestor()
	{
		DirectedAcyclicGraph DirectedAcyclicGraph = new DirectedAcyclicGraph(10);
		DirectedAcyclicGraph.addEdge(0, 1);
		DirectedAcyclicGraph.addEdge(0, 3);
		DirectedAcyclicGraph.addEdge(1, 3);
		DirectedAcyclicGraph.addEdge(3, 2);
		DirectedAcyclicGraph.addEdge(2, 4);
		DirectedAcyclicGraph.addEdge(1, 5);
		DirectedAcyclicGraph.addEdge(2, 5);


		assertEquals("Check LCA function works", 0, DirectedAcyclicGraph.findLCA(2, 1));
		assertEquals("Check LCA function works",3, DirectedAcyclicGraph.findLCA(2, 3));
		assertEquals("Check LCA function works",2, DirectedAcyclicGraph.findLCA(4, 5));
		assertEquals("Check LCA function works for non presnet node",-1, DirectedAcyclicGraph.findLCA(8, 2));
		assertEquals("Check LCA function works for negatives",-1, DirectedAcyclicGraph.findLCA(-2, 3));
		assertEquals("Check LCA function works for negatives",-1, DirectedAcyclicGraph.findLCA(3, -2));
		assertEquals("Check LCA function works for negatives",-1, DirectedAcyclicGraph.findLCA(-2, -3));
	}

	@Test
	public void testSameVertex()
	{
		DirectedAcyclicGraph DirectedAcyclicGraph = new DirectedAcyclicGraph(10);

		DirectedAcyclicGraph.addEdge(0, 1);
		DirectedAcyclicGraph.addEdge(0, 3);
		DirectedAcyclicGraph.addEdge(1, 3);

		assertEquals("checking for lca with ancestor on same vertex", 3, DirectedAcyclicGraph.findLCA(3, 3));

	}
	@Test
	public void testNonDAG()
	{
		DirectedAcyclicGraph DirectedAcyclicGraph = new DirectedAcyclicGraph(10);

		DirectedAcyclicGraph.addEdge(0, 1);
		DirectedAcyclicGraph.addEdge(0, 3);
		DirectedAcyclicGraph.addEdge(3, 2);
		DirectedAcyclicGraph.addEdge(2, 0);
		DirectedAcyclicGraph.addEdge(2, 4);

		//Testing for DirectedAcyclicGraph with cycle
		assertEquals(-1, DirectedAcyclicGraph.findLCA(3, 2));
		assertEquals(-1, DirectedAcyclicGraph.findLCA(2, 4));
		assertEquals(-1, DirectedAcyclicGraph.findLCA(1, 3));
		assertEquals(-1, DirectedAcyclicGraph.findLCA(0, 3));
		assertEquals(-1, DirectedAcyclicGraph.findLCA(1, 2));

	}

	// tests empty DAG
	@Test
	public void testEmptyDAG()
	{
		DirectedAcyclicGraph DirectedAcyclicGraph = new DirectedAcyclicGraph(5);
		assertEquals("Empty DirectedAcyclicGraph test", -1, DirectedAcyclicGraph.findLCA(0, 2));
		assertEquals("Empty DirectedAcyclicGraph test",-1, DirectedAcyclicGraph.findLCA(0, 4));
		assertEquals("Empty DirectedAcyclicGraph test",-1, DirectedAcyclicGraph.findLCA(0, 0));
	}



}
