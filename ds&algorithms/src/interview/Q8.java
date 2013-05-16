package interview;

import info.chenliang.ds.Util;

public class Q8 {

	static class Node
	{
		Node next;
		int data;
		public Node(Node next, int data) {
			super();
			this.next = next;
			this.data = data;
		}
		
		
	}
	
	private static Node reverse(Node node, Node from)
	{
		if(node != null)
		{
			Node r = reverse(node.next, node);
			
			node.next = from;
			
			return r;
		}
		
		return from;
	}
	
	private static Node reverse2(Node node)
	{
		Node from = null;
			
		while(node != null)
		{
			Node last = node;
			
			node = node.next;
			
			last.next = from;
			from = last;
		}
		
		return from;
	}
	
	private static void traverse(Node node)
	{
		while(node != null)
		{
			System.out.print(node.data);
			if(node.next != null)
			{
				System.out.print("->");
			}
			
			node = node.next;
		}
		
		System.out.println();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = Util.generateRandomArray(10, 20);
		Node p = null;
		for (int i = 0; i < a.length; i++) {
			Node n = new Node(p, a[i]);
			
			p = n;
		}
		
		traverse(p);
		
		//p = reverse(p, null);
		p = reverse2(p);
		
		traverse(p);
	}

}
