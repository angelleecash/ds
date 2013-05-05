package interview;

import java.util.List;
import java.util.Stack;

public class Q2 {

	static class MinStack
	{
		static class MinStackItem
		{
			int data;
			int min;
			public MinStackItem(int data, int min) {
				super();
				this.data = data;
				this.min = min;
			}
		}
		
		List<MinStackItem> items = new Stack<Q2.MinStack.MinStackItem>();
		
		public void push(int v)
		{
			int min = v;
			
			if(items.size() > 0)
			{
				MinStackItem item = items.get(items.size() - 1);
				if(v > item.data)
				{
					v = item.data;
				}
			}
			
			items.add(new MinStackItem(v, min));
		}
		
		public int peek()
		{
			return items.get(items.size()-1).data;
		}
		
		public int pop()
		{
			return items.remove(items.size()-1).data;
		}
		
		public int getMin()
		{
			return items.get(items.size()-1).min;
		}
	}
	
	public static void main(String[] args) 
	{
		

	}

}
