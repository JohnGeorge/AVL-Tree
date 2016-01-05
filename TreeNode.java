/**
 *@author John George
 * Tree node class
 */
public class TreeNode {
	private int data; //value of the node
	private int nodeBalance; //height of the node.
	private TreeNode parent; //parent of node.
	private TreeNode left; //node left child.
	private TreeNode right; //node right child.
	
	public TreeNode(int data){
		this.data = data;
	}
	
	//---------------------------------------------
	//GET
	public int getData(){
		return this.data;
	}
	
	public int getNodeBalance(){
		return this.nodeBalance;
	}

	public TreeNode getParent(){
		return this.parent;
	}
	
	public TreeNode getLeft(){
		return this.left;
	}

	public TreeNode getRight(){
		return this.right;
	}
	
	
	//SET
	public void setData(int data){
		this.data = data;
	}
	
	public void setNodeBalance(int nodeBalance){
		this.nodeBalance = nodeBalance;
	}
	
	public void setParent(TreeNode parentNode){
		this.parent = parentNode;
	}
	
	public void setLeft(TreeNode leftChild){ this.left = leftChild; }
	
	public void setRight(TreeNode rightChild){
		this.right = rightChild;
	}
	
	//---------------------------------------------
	public void printTree() {
        if (this.right != null) {
            right.printTree(true, "");
        }
        printNodeValue();
        if (left != null) {
            left.printTree(false, "");
        }
    }

	private void printTree(boolean isRight, String indent) {
	    if (right != null) {
	        right.printTree(true, indent + (isRight ? "        " : " |      "));
	    }
	    System.out.print(indent);
	    if (isRight) {
	        System.out.print(" /");
	    } else {
	        System.out.print(" \\");
	    }
	    System.out.print("----- ");
	    printNodeValue();
	    if (left != null) {
	        left.printTree(false, indent + (isRight ? " |      " : "        "));
	    }
	}
	
	public void printNodeValue() {
	    System.out.print(data);
	    System.out.print('\n');
	}
}