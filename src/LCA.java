// Holly Mcevoy 19334663 this code and Dag code are taken from assignment in second year "binary trees" as well as geeksforgeeks

public class LCA<Key extends Comparable<Key>, Value> {
	
	private TreeNode root;
	
	
	LCA(){
		root = null;
	}

	public class TreeNode {
		private Key key;
		private Value val;
		private TreeNode left;
		private TreeNode right;
		private int nodesBelow;



		public TreeNode(Key key, Value val, int nodesBelow ) {
			this.key = key;
			this.val = val;
			this.nodesBelow= nodesBelow;
		}
	}
	
	
	public boolean existsCheck(Key key) {
		if(getNode(key) != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public Value getNode(Key key) {
		Value x = null;
		if( key != null) x = getNode(root, key);
		return x;

	}


	private Value getNode(TreeNode x, Key key) {
		if( x==null) return null;
		int compare = key.compareTo(x.key);
		if (compare < 0) return getNode(x.left, key);
		else if (compare > 0) return getNode(x.right, key);
		else return x.val;
	}
	
	public void putNode(Key key, Value val) {
    	if (val!= null && !existsCheck(key) ){
				root = putNode(root, key, val);
			}
		}
	
	private TreeNode putNode(TreeNode x, Key key, Value val) {
		if(x == null) return new TreeNode(key, val, 1);
		int compare = key.compareTo(x.key);
		if (compare < 0) x.left  = putNode(x.left,  key, val);
        else if (compare > 0) x.right = putNode(x.right, key, val);
        else x.val   =  val;
        x.nodesBelow = 1 + size(x.left) + size(x.right); //increment the number of nodes under this node
        return x;
    }
	
	private int size(TreeNode x) {
        if (x == null) return 0;
        else return x.nodesBelow;
    }
	
	 //returns the lowest common ancestor of two keys if both exist in the tree.
		public Key getLCA(Key keyA, Key keyB){
			Key answer = null;
			//there exists a LCA if both the keys are in the binary tree
			if (existsCheck(keyA) && existsCheck(keyB)){
				answer = getLCA(root, keyA, keyB);
			}
			return answer;
		}

		private Key getLCA(TreeNode node, Key keyA, Key keyB){
			//if both inputs are greater than the current node, LCA is in right subtree
			if ((node.key.compareTo(keyA)<0 && node.key.compareTo(keyB)<0)){
				return  getLCA(node.right, keyA, keyB);
			}
			else if((node.key.compareTo(keyA)>0 && node.key.compareTo(keyB)>0)){
				return  getLCA(node.left, keyA, keyB);
			}
			
			else{
				return node.key;
			}
		}
	public String treeToString(){
		return nodeToString(root);
	}
	private String nodeToString(TreeNode node){
		if(node == null){
			return "()";
		}
		else{
			return "(" + nodeToString(node.left) + node.key + nodeToString(node.right) + ")";
			
		}
	}


}
