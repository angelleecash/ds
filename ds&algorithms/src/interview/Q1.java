package interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.concurrent.ThreadPoolExecutor;

public class Q1 {

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
	
	public static void lpr(Node n, List<Integer> result)
	{
		if(n != null)
		{
			lpr(n.left, result);
			//System.out.println(n.data);
			result.add(n.data);
			lpr(n.right, result);
		}
	}
	
	public static Node lpr2(Node treeRoot, List<Integer> result)
	{
		Stack<Node> stack = new Stack<Q1.Node>();
		
		Node p = treeRoot;
		while(p != null)
		{
			stack.push(p);
			p = p.left;
		}
		
		Node head = null;
		Node last = null;
		while(!stack.isEmpty())
		{
			Node root = stack.pop();
			//System.out.println(root.data);
			result.add(root.data);
			
			if(head == null)
			{
				head = root;
			}
			
			if(last != null)
			{
				last.right = root;
				root.left = last;
			}
			
			last = root;
			Node right = root.right;
			while(right != null)
			{
				stack.push(right);
				right = right.left;
			}
			
			root.right = null;
		}
		
		return head;
	}
	
	public static void plr(Node n, List<Integer> result)
	{
		if(n != null)
		{
			//System.out.println(n.data);
			result.add(n.data);
			plr(n.left, result);
			plr(n.right, result);
		}
	}
	
	public static void plr2(Node treeRoot, List<Integer> result)
	{
		Stack<Node> stack = new Stack<Q1.Node>();
		
		Node p = treeRoot;
		while(p != null)
		{
			stack.push(p);
			//System.out.println(p.data);
			result.add(p.data);
			p = p.left;
		}
		
		while(!stack.isEmpty())
		{
			Node root = stack.pop();
		
			Node right = root.right;
			while(right != null)
			{
				//System.out.println(right.data);
				result.add(right.data);
				stack.push(right);
				right = right.left;
			}
			
		}
		
	}
	
	public static void lrp(Node n, List<Integer> result)
	{
		if(n != null)
		{
			lrp(n.left, result);
			lrp(n.right, result);
			//System.out.println(n.data);
			result.add(n.data);
		}
	}
	
	public static void lrp2(Node treeRoot, List<Integer> result)
	{
		Stack<Node> stack = new Stack<Q1.Node>();
		
		Node p = treeRoot;
		while(p != null)
		{
			stack.push(p);
			p = p.left;
		}
		
		while(!stack.isEmpty())
		{
			Node root = stack.peek();
			
			Node right = root.right;
			if(right != null && !right.visited)
			{
				while(right != null)
				{
					stack.push(right);
					right = right.left;
				}
				
				root.right.visited = true;
			}
			else
			{
				root = stack.pop();
				
				//System.out.println(root.data);
				result.add(root.data);				
			}
			
		}
		
	}
	
	public static void transformIntoDoubleLinkedListInOrder(Tree tree)
	{
		List<Integer> result = new Stack<Integer>();
		
		Node head = lpr2(tree.root, result);
		
		System.out.println();
		System.out.println();
		
		while(head != null)
		{
			System.out.println(head.data);
			head = head.right;
		}		
	}
	
	static int RUN_COUNT = 100000;
	static int ELEMENTS_COUNT = 10000;
	
	static class LrpTest implements Runnable
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=0;i < RUN_COUNT; i++)
			{
				Tree tree = new Tree();
				
				Set<Integer> set = new TreeSet<Integer>();
				
				for (int j = 0; j < ELEMENTS_COUNT; j++) {
					int v = (int)(Math.random() * 1000000000);
					//System.out.println("adding " + v);
					if(!set.contains(v))
					{
						tree.add(v);
						
						set.add(v);
					}
					
				}
				
				List<Integer> result = new ArrayList<Integer>();
				lrp(tree.root, result);
				
				List<Integer> result2 = new ArrayList<Integer>();
				lrp2(tree.root, result2);
				
				Assert(result.size() == result2.size(), "shit");
				for (int j = 0; j < result.size(); j++) {
					Assert(result.get(j).intValue() == result2.get(j).intValue(), "fuck");
				}
			}
			
			System.out.println("lrp OK");
		}
		
	}
	
	static class LprTest implements Runnable
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=0;i < RUN_COUNT; i++)
			{
				Tree tree = new Tree();
				
				Set<Integer> set = new TreeSet<Integer>();
				
				for (int j = 0; j < ELEMENTS_COUNT; j++) {
					int v = (int)(Math.random() * 1000000000);
					
					if(!set.contains(v))
					{
						tree.add(v);
						
						set.add(v);
					}
					
				}
				
				List<Integer> result = new ArrayList<Integer>();
				lpr(tree.root, result);
				
				List<Integer> result2 = new ArrayList<Integer>();
				lpr2(tree.root, result2);
				
				Assert(result.size() == result2.size(), "shit");
				for (int j = 0; j < result.size(); j++) {
					Assert(result.get(j).intValue() == result2.get(j).intValue(), "fuck");
				}
			}
			
			System.out.println("lpr OK");
		}
		
	}
	
	static class PlrTest implements Runnable
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=0;i < RUN_COUNT; i++)
			{
				Tree tree = new Tree();
				
				Set<Integer> set = new TreeSet<Integer>();
				
				for (int j = 0; j < ELEMENTS_COUNT; j++) {
					int v = (int)(Math.random() * 1000000000);
					
					if(!set.contains(v))
					{
						tree.add(v);
						
						set.add(v);
					}
					
				}
				
				List<Integer> result = new ArrayList<Integer>();
				plr(tree.root, result);
				
				List<Integer> result2 = new ArrayList<Integer>();
				plr2(tree.root, result2);
				
				Assert(result.size() == result2.size(), "shit");
				for (int j = 0; j < result.size(); j++) {
					Assert(result.get(j).intValue() == result2.get(j).intValue(), "fuck");
				}
			}
			
			System.out.println("plr OK");
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
//		Tree tree = new Tree();
//		tree.add(10);
//		tree.add(6);
//		tree.add(4);
//		tree.add(8);
//		tree.add(14);
//		tree.add(12);
//		tree.add(16);
		
		
		Thread t = null;
		
		t = new Thread(new PlrTest());
		//t.start();
		
		t = new Thread(new LprTest());
		//t.start();
		
		t = new Thread(new LrpTest());
		t.start();
	}
	
	
	
	public static void Assert(boolean flag, String message)
	{
		if(!flag)
		{
			throw new RuntimeException(message);
		}
	}

}
