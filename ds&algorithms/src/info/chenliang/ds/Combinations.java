package info.chenliang.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Combinations {
	public static void generateCombinations(int[] a)
	{
		int[] flag = new int[a.length];
		while(true)
		{
			int c=0;
			for (int i = 0; i < flag.length; i++) {
				if(flag[i] == 1)
				{
					c++;
				}
			}
			
			if(c >= flag.length)
			{
				break;
			}
			
			for(int i=flag.length-1; i>=0;i--)
			{
				if(flag[i] == 0)
				{
					for(int j=flag.length-1; j >= i; j--)
					{
						flag[j] = (flag[j]+1) %2;
					}
					break;
				}
			}
			
			printArray(a, flag);
		}
	}
	
	private static void printArray(int[] a, int[] flag)
	{
		for (int i = 0; i < flag.length; i++) {
			if(flag[i] == 1)
			{
				System.out.print(a[i]);
			}
		}
		System.out.println();
	}

	private static void printArray2(int[] a, int[] flag)
	{
		for (int i = 0; i < flag.length; i++) {
			System.out.print(a[flag[i]]);
		}
		System.out.println();
	}
	
	private static int[] generateResult(int[] a, int[] flag)
	{
		int[] result = new int[flag.length];
		for (int i = 0; i < flag.length; i++) {
			result[i] = a[flag[i]];
		}
		
		return result;
	}
	
	public static List<int[]> generateCombinations(int[] a, int n)
	{
		List<int[]> result = new ArrayList<int[]>(); 
		
		int[] flag = new int[n];
		for (int i = 0; i < flag.length; i++) {
			flag[i] = i;
		}
		
		while(true)
		{
			result.add(generateResult(a, flag));
			
			boolean equal = true;
			for (int i = 0; i < flag.length; i++) {
				if(flag[i] != a.length - n + i)
				{
					equal = false;
					break;
				}
			}
			
			if(equal)
			{
				break;
			}
			
			for(int i=flag.length-1; i >=0; i--)
			{
				if(flag[i] < a.length - n + i)
				{
					int base = flag[i];
					for(int j=i; j < flag.length; j++)
					{
						flag[j] = base + j - i + 1;
					}
					break;
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
		//Combinations.generateCombinations(a);
		List<int[]> result = Combinations.generateCombinations(a, 2);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(Arrays.toString(result.get(i)));
		}
	}
}
