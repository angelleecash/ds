package interview;

import info.chenliang.ds.Util;

public class Q3 {

	static class MaxSumTest implements Runnable {
		public void run() {
			
			
			for (int i = 0; i < 1000000; i++) {
				int[] a = Util.generateRandomArray(200, 1000);
				
				long maxSum = Long.MIN_VALUE;
				for (int j = 0; j < a.length; j++) {
					
					for(int lastIndex=j+1; lastIndex <= a.length; lastIndex++)
					{
						long sum = 0;
						for(int p=j; p < lastIndex; p++)
						{
							sum += a[p];
						}
						
						maxSum = Math.max(sum, maxSum);
					}
				}
				
				long ttt = maxSum(a);
				Util.Assert(maxSum == ttt, "shit");
			}
			
			System.out.println("max sum ok");
		}
	}
	
	public static long maxSum(int[] a)
	{
		long sum = a[0];
		long maxSum = sum;
		for (int i = 1; i < a.length; i++) {
			if(sum <= 0)
			{
				sum = 0;
			}
			
			sum += a[i];
			maxSum = Math.max(sum, maxSum);
			
			
		}
		
		return maxSum;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t = new Thread(new MaxSumTest());
		t.start();
	}

}
