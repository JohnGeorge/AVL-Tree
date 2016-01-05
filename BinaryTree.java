/**
 * @author John George
 * Binary search tree extended to an AVL-tree.
 */
public class BinaryTree {
	private TreeNode root; //root of the tree.

	/**
	 * Create a new node in the tree.
	 * @param data value of the new node.
	 */
	public void add(int data){

		TreeNode newNode = new TreeNode(data);

		try {
			if(root == null){
				this.root = newNode;
			}else if(root != null){
				insert(this.root, newNode);

			}
		} catch (Exception e) {

			System.out.println(e);
		}
	}

	/**
	 * Place a new node in the tree.
	 * @param current Current node.
	 * @param newNode The new node that will be placed into the tree.
	 * @throws Exception If the value of the node already exists in the tree an exception will be thrown.
	 */
	public void insert(TreeNode current, TreeNode newNode)throws Exception{
		if(current == null){
			this.root = newNode;
		} else{

			if(newNode.getData() < current.getData()){
				if(current.getLeft() == null){
					current.setLeft(newNode);
					newNode.setParent(current);
					
					checkBalance(current);
				} else{
					insert(current.getLeft(), newNode);
				}
			} else if(newNode.getData() > current.getData()){
				if(current.getRight() == null){
					current.setRight(newNode);
					newNode.setParent(current);
					
					checkBalance(current);
				} else{
					insert(current.getRight(), newNode);
				}
			} else{
				throw new Exception("Your value " + newNode.getData() + " already exists.");
			}
		}
	}

	/**
	 * Searches the tree for given node.
	 * @param current Current node.
	 * @param value Value of the node that the method searches for.
	 * @return Current node with desired value.
	 */
	private TreeNode find(TreeNode current, int value){

		if(current == null || current.getData() == value){
			return current;
		} else if(current.getData() > value){
			current = find(current.getLeft(), value);

		}else if(current.getData() < value){
			current = find(current.getRight(), value);

		}
		return current;
	}

	/**
	 * If the node exists in the tree the remove() method is called.
	 * @param value Value of the node that is to be removed.
	 */
	public void delete(int value){

		TreeNode nodeToDelete = find(this.root, value);
		if(this.root == null){
			System.out.println("tree is empty");
		} else if(nodeToDelete == null){
			System.out.println("value "+ value + " doesn't exist");
		} else{
			remove(nodeToDelete);
		}
	}

	/**
	 * Finds the successor of the node that is to be removed,
	 * and replaces it with the successor.
	 * @param current Node to be removed.
	 */
	public void remove(TreeNode current) {
		TreeNode node;
		TreeNode newNode;

		if(current.getLeft() == null || current.getRight() == null) {
			if(current.getParent() == null) {
				this.root=null;
				return;
			}
			node = current;
		} else {
			node = findSuccessor(current);
			current.setData(node.getData());
		}

		if(node.getLeft() != null) {
			newNode = node.getLeft();
		} else {
			newNode = node.getRight();
		}

		if(newNode != null) {
			newNode.setParent(node.getParent());
		}

		if(node.getParent() == null) {
			this.root = newNode;
		} else {
			if(node == node.getParent().getLeft()) {
				node.getParent().setLeft(newNode);
			} else {
				node.getParent().setRight(newNode);
			}
			checkBalance(node.getParent());
		}
	}


	/**
	 * Finds the successor node which is the right sub-trees leftmost node.
	 * @param current Current node to start from.
	 * @return Successor.
	 */
	public TreeNode findSuccessor(TreeNode current) {
		if(current.getRight() != null) {
			TreeNode node = current.getRight();
			while(node.getLeft() != null) {
				node = node.getLeft();
			}
			return node;
		} else {
			TreeNode parent = current.getParent();
			while(parent != null && current == parent.getRight()) {
				current = parent;
				parent = current.getParent();
			}
			return parent;
		}
	}

	/**
	 * Inorder traversal of the AVL-tree that returns all nodes from low to high.
	 * @param node
	 */
	public void inOrder(TreeNode node){
		if(node == null){
			return;
		}else if(node != null){
			inOrder(node.getLeft());
			node.printNodeValue();
			inOrder(node.getRight());

		}
	}

	/**
	 * Checking the balance of the node and makes rotation if needed.
	 * @param current Current node to check.
	 */
	private void checkBalance(TreeNode current){
		setBalance(current);
		int balance = current.getNodeBalance();
		
		if(balance == -2){
			
			if(height(current.getLeft().getLeft()) >= height(current.getLeft().getRight())){
				current = rotateLeftLeft(current);
			} else{
				current = rotateLeftRight(current);
			}
		} else if(balance == 2){
			if(height(current.getRight().getRight()) >= height(current.getRight().getLeft())){
				current = rotateRightRight(current);
			} else{
				current = rotateRightLeft(current);
			}
		}

		if(current.getParent() != null){
			checkBalance(current.getParent());
		} else{
			this.root = current;
		}
	}

	/**
	 * Calculates the height of the node.
	 * @param current Current node to calculate.
	 * @return Height of current node.
	 */
	private int height(TreeNode current){

		int height = 0;
		if(current != null){
			int left = height(current.getLeft());
			int right = height(current.getRight());
			height = 1+maximumInt(left, right);
		}
		return height;
	}

	/**
	 * Returns the highest of two compared nodes.
	 * @param firstNode First node to compare.
	 * @param secondNode Second node to compare.
	 * @return Highest node.
	 */
	private int maximumInt(int firstNode, int secondNode){
		if(firstNode < secondNode){
			return secondNode;
		} else{
			return firstNode;
		}
	}

	/**
	 * Sets the balance of the current node.
	 * @param current Current node.
	 */
	private void setBalance(TreeNode current){
		current.setNodeBalance(height(current.getRight()) - height(current.getLeft()));
	}


	/**
	 * Rotation of the tree to right to maintain balance.
	 * @param node Node to be used in the rotation.
	 * @return Root node of the updated tree.
	 */
	private TreeNode rotateLeftLeft(TreeNode node){
		
		TreeNode leftNode = node.getLeft();
		leftNode.setParent(node.getParent());
		
		node.setLeft(leftNode.getRight());
		
		if(node.getLeft() != null){
			node.getLeft().setParent(node);
		}
		
		leftNode.setRight(node);
		node.setParent(leftNode);
		
		if(leftNode.getParent() != null){
			if(leftNode.getParent().getRight() == node){
				leftNode.getParent().setRight(leftNode);
			} else if(leftNode.getParent().getLeft() == node){
				leftNode.getParent().setLeft(leftNode);
			}
		}
		
		setBalance(node);
		setBalance(leftNode);
		
		return leftNode;
	}

	/**
	 * Rotation of the tree to left to maintain balance.
	 * @param node Node to be used in the rotation.
	 * @return Root node of the updated tree.
	 */
	private TreeNode rotateRightRight(TreeNode node){

		TreeNode rightNode = node.getRight();
		rightNode.setParent(node.getParent());

		node.setRight(rightNode.getLeft());

		if(node.getRight()!=null){
			node.getRight().setParent(node);
		}

		rightNode.setLeft(node);
		node.setParent(rightNode);

		if(rightNode.getParent() != null){
			if(rightNode.getParent().getRight() == node){
				rightNode.getParent().setRight(rightNode);
			} else if(rightNode.getParent().getLeft() == node){
				rightNode.getParent().setLeft(rightNode);
			}
		}

		setBalance(node);
		setBalance(rightNode);

		return rightNode;
	}

	/**
	 * Double rotation of the tree, first to the right and then to the left.
	 * @param node Node to be used in the rotation.
	 * @return Root node of the updated tree.
	 */
	private TreeNode rotateRightLeft(TreeNode node){
		node.setRight(rotateLeftLeft(node.getRight()));
		return rotateRightRight(node);
	}

	/**
	 * Double rotation of the tree, first to the left and then to the right.
	 * @param node Node to be used in the rotation.
	 * @return Root node of the updated tree.
	 */
	private TreeNode rotateLeftRight(TreeNode node){
		node.setLeft(rotateRightRight(node.getLeft()));
		return rotateLeftLeft(node);
	}

	/**
	 * Prints out the tree.
	 */
	public void printTree(){
		inOrder(root);
		root.printTree();
	}
}
