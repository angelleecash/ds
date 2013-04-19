package info.chenliang.ds;

import java.util.Arrays;
import java.util.Stack;

public class Permutations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public static void generatePermutationRecursively(int[] a, int n)
	{
		if(n >= a.length)
		{
			System.out.println(Arrays.toString(a));
			return;
		}
		
		for(int i=n; i < a.length; i++)
		{
			int v = a[n];
			a[n] = a[i];
			a[i] = v;
			generatePermutationRecursively(a, n+1);
			v = a[n];
			a[n] = a[i];
			a[i] = v;
		}
	}
	
	static class PermutationNumber
	{
		public final static int DIRECTION_LEFT = 0;
		public final static int DIRECTION_RIGHT = 1;
		
		public int v, index, direction;
		public static PermutationNumber[] numbers;
		
		public PermutationNumber(int v, int index, int direction)
		{
			this.v = v;
			this.index = index;
			this.direction = direction;
		}
		
		public PermutationNumber(PermutationNumber number)
		{
			this(number.v, number.index, number.direction);
		}
		
		public static void init(PermutationNumber[] numbers)
		{
			PermutationNumber.numbers = numbers;
		}
		
		public boolean isMobile()
		{
			if(index == 0)
			{
				return direction == DIRECTION_RIGHT;
			}
			else if(index == numbers.length-1)
			{
				return direction == DIRECTION_LEFT;
			}
			else
			{
				if(direction == DIRECTION_LEFT)
				{
					return numbers[index-1].v < v;
				}
				else
				{
					return numbers[index+1].v < v;	
				}
			}
		}
		
		public PermutationNumber getPointTo()
		{
			if(direction == DIRECTION_LEFT)
			{
				return numbers[index-1];
			}
			else
			{
				return numbers[index+1];	
			}
		}
		
		public void reverseDirection()
		{
			if(direction == DIRECTION_LEFT)
			{
				direction = DIRECTION_RIGHT;
			}
			else
			{
				direction = DIRECTION_LEFT;
			}
		}
	}
	
	private static boolean hasMobileNumbers(PermutationNumber[] numbers)
	{
		for (int i = 0; i < numbers.length; i++) {
			if(numbers[i].isMobile())
			{
				return true;
			}
		}
		
		return false;
	}
	
	private static PermutationNumber findLargestMobileNumber(PermutationNumber[] numbers)
	{
		PermutationNumber number = null;
		
		for (int i = 0; i < numbers.length; i++) {
			if(numbers[i].isMobile())
			{
				if(number == null || numbers[i].v > number.v)
				{
					number = numbers[i];
				}
			}
		}
		
		return number;
	}
	
	private static void printPermutation(PermutationNumber[] numbers)
	{
		for (int i = 0; i < numbers.length; i++) {
			PermutationNumber number = numbers[i];
			System.out.print(number.v + " ");
		}
		
		System.out.println();
	}
	
	public static void generatePermutation(int[] a)
	{
		PermutationNumber[] numbers = new PermutationNumber[a.length];
		for(int i=0; i < numbers.length ;i ++)
		{
			PermutationNumber number = new PermutationNumber(i, i, PermutationNumber.DIRECTION_LEFT);
			numbers[i] = number;
		}
		
		PermutationNumber.init(numbers);
		
		PermutationNumber largestMobile;
		
		while((largestMobile = findLargestMobileNumber(numbers)) != null)
		{
			PermutationNumber pointTo = largestMobile.getPointTo();
			
			numbers[pointTo.index] = new PermutationNumber(largestMobile);
			numbers[pointTo.index].index = pointTo.index;
			
			numbers[largestMobile.index] = new PermutationNumber(pointTo);
			numbers[largestMobile.index].index = largestMobile.index;
			
			for (int i = 0; i < numbers.length; i++) {
				PermutationNumber number = numbers[i];
				
				if(number.v > largestMobile.v)
				{
					number.reverseDirection();
				}
			}
			
			printPermutation(numbers);
		}
	}
}
