package info.chenliang.ds;

public class Util {
	public static void Assert(boolean flag, String message)
	{
		if(!flag)
		{
			throw new RuntimeException(message);
		}
	}
	
	public static void Assert(boolean flag)
	{
		Assert(flag, "wtf");
	}
	
	
	public static int[] generateRandomArray(int n, int max)
	{
		int[] a = new int[n];
		
		int c = 0;
		while(n-- > 0)
		{
			a[c++] = generateRandomNumber(max);
		}
		
		return a;
	}
	
	public static int generateRandomNumber(int max)
	{
		int sign = Math.random() >= 0.5 ? 1 : -1;
		return sign * (int)(Math.random()*max);
	}
	
	public static void printTree(Node n)
	{
		Queue q = new Queue();
		q.enter(n);
		
		while(!q.isEmpty())
		{
			Node p = (Node)q.leave();
			
		}
	}
}
