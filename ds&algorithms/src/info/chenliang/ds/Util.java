package info.chenliang.ds;

import java.util.HashSet;
import java.util.Set;

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
	
	public static int[] generateUnduplicatedRandomArray(int n, int max)
	{
		Set<Integer> set = new HashSet<Integer>();
		
		
		while(set.size() < n)
		{
			int r = generateRandomNumber(max);
			set.add(r);
		}
		
		Integer[] result = new Integer[0];
		result = set.toArray(result);
		
		Util.Assert(result.length == n);
		
		int[] ret = new int[n];
		for(int i=0;i<n;i++)
		{
			ret[i] = result[i];
		}
		
		return ret;
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
