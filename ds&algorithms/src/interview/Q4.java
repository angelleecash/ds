package interview;

import info.chenliang.ds.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;



public class Q4 {

	static class Node
	{
		int data;
		Node left;
		Node right;
		boolean visited;
		public Node(int data, Node left, Node right) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
		}
		
		public boolean isLeaf()
		{
			return left == null && right == null;
		}
	}
	
	static class Tree
	{
		Node root;
		
		public void add(int data)
		{
			if(root == null)
			{
				root = new Node(data, null, null);
			}
			else
			{
				Node current = root;
				Node previous = null;
				while(current!= null)
				{
					previous = current;
					
					if(current.data < data)
					{
						current = current.right;
					}
					else if(current.data > data)
					{
						current = current.left;
					}
					else
					{
						return;
					}
				}
				
				Node newNode = new Node(data, null, null);
				
				if(data > previous.data)
				{
					previous.right = newNode;
				}
				else if(data < previous.data)
				{
					previous.left = newNode;
				}
			}
		}
	}
	
	public static void findPath(Node node, int n)
	{
		Queue q = new Queue();
		
		q.enter(node);
		
		while(!q.isEmpty())
		{
			Node parent = (Node)q.leave();
			if(parent.data == n)
			{
				System.out.println("Found one");
			}
			
			if(parent.left != null)
			{
				parent.left.data += parent.data;
				q.enter(parent.left);
			}
			
			if(parent.right != null)
			{
				parent.right.data += parent.data;
				q.enter(parent.right);
			}
		}
	}
	
	public static void printStack(Stack<Node> stack)
	{
		StringBuilder sb = new StringBuilder();
		
		Stack<Node> stack2 = new Stack<Q4.Node>();
		
		while(!stack.isEmpty())
		{
			Node node = stack.pop();
			stack2.push(node);
			
			sb.insert(0, node.data + " ");	
		}
		
		while(!stack2.isEmpty())
		{
			stack.push(stack2.pop());
		}
		
		System.out.println(sb.toString());
	}
	
	public static void findPath2(Node node, int n)
	{
		Stack<Node> stack = new Stack<Node>();
		
		int cost = 0;
		Node r = node;
		while(r != null)
		{
			stack.add(r);
			cost += r.data;
			r = r.left;
		}
		
		while(!stack.isEmpty())
		{
			Node root = stack.peek();
			
			if(cost == n && root.isLeaf())
			{
				printStack(stack);
			}
			
			if(root.right != null && !root.right.visited)
			{
				Node right = root.right;
				while(right != null)
				{
					stack.add(right);
					cost += right.data;
					right = right.left;
				}
				
				root.right.visited = true;
			}
			else
			{
				Node p = stack.pop();
				cost -= p.data;	
			}
		}
	}
	
	public static void findPath3(Stack<Node> stack, int cost, int target, Node node)
	{
		if(node == null)
		{
			return;
		}
		
		stack.push(node);
		cost += node.data;
		
		if(cost == target && node.isLeaf())
		{
			printStack(stack);
		}
		
		findPath3(stack, cost, target, node.left);
		findPath3(stack, cost, target, node.right);
		
		cost -= node.data;
		stack.pop();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node n4 = new Node(4, null, null);
		Node n7 = new Node(7, null, null);
		
		Node n5 = new Node(5, n4, n7);
		
		
		Node n12 = new Node(12, null, null);
		
		
		Node n10 = new Node(10, n5, n12);
		
		//findPath2(n10, 22);
		
		Stack<Node> stack = new Stack<Node>();
		findPath3(stack, 0, 22, n10);
	}

}
