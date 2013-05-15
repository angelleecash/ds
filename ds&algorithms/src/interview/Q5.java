package interview;

import info.chenliang.ds.MaxHeap;
import info.chenliang.ds.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q5 {

	
	static int[] kMin(int[] a, int k)
	{
		Util.Assert(a.length >= k, "Not enough elements");
		
		int c = 0;
		int start = 0;
		int end = a.length - 1;
		
		while(c < k)
		{
			Util.Assert(start <= end, "Invalid range");
			
			int t = partition(a, start, end);
			if(t <= k)
			{
				c += (t-start+1);
				
				start = t+1;
			}
			else
			{
				end = t-1;
			}	
		}
		
//		for (int i = 0; i < k; i++) {
//			System.out.println(a[i]);
//		}
		
		int[] result = new int[k];
		for (int i = 0; i < k; i++) {
			result[i] = a[i];
		}
		
		return result;
	}
	
	static int partition(int[] a, int start, int end)
	{
		int flag = a[end];
		int smaller = start;
		for (int i = start; i < end; i++) {
			if(a[i] <= flag)
			{
				if(smaller != i)
				{
					int temp = a[smaller];
					a[smaller] = a[i];
					a[i] = temp;					
				}
				
				smaller++;
			}
		}
		
		if(smaller != end)
		{
			int temp = a[smaller];
			a[smaller] = a[end];
			a[end] = temp;			
		}
		
		return smaller;
	}
	
	static class kMinTest implements Runnable
	{

		@Override
		public void run() {
			for (int i = 0; i < 1000000; i++) {
				int[] a = Util.generateRandomArray(1000, 1000);
				int[] b = new int[a.length];
				for(int j=0; j < b.length; j++)
				{
					b[j] = a[j];
				}
				
				Arrays.sort(b);
				
				for (int j = 1; j <= a.length; j++) {
					int[] result = kMin(a, j);
					
					List<Integer> l = new ArrayList<Integer>();
					for(int k=0; k < j; k++)
					{
						l.add(b[k]);
					}
					
					for(int k=0;k < result.length;k++)
					{
						int index = l.indexOf(result[k]);
						
						l.remove(index);
						//l.remove(result[k]);
					}
					
					Util.Assert(l.isEmpty(), "Shit");
					
				}
			}
			
			System.out.println("kMin ok");
		}
		
	}
	
	static class PartitionTest implements Runnable
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=0;i < 100000;i++)
			{
				int[] a = Util.generateRandomArray(5, 25);
				int[] b = a.clone();
				
				int p = partition(a, 0, a.length-1);
				for(int j=0;j < p;j++)
				{
					Util.Assert(a[p] >= a[j], "shit");
				}
				
				for(int j=p+1;j < a.length;j++)
				{
					Util.Assert(a[p] <= a[j], "shit");
				}
			}
			
			System.out.println("partition ok");
		}
		
	}
	
	
	public static int[] kMin2(int[] a, int k)
	{
		Util.Assert(a.length >= k);
		
		MaxHeap heap = new MaxHeap(a, 0, k);
		
		for (int i = k; i < a.length; i++) {
			int max = heap.max();
			if(a[i] < max)
			{
				heap.getData().set(0, a[i]);
				heap.maxHeapify(0);
			}
		}
		
		int[] result = new int[k];
		for(int i=0; i < result.length; i++)
		{
			result[i] = heap.getData(i);
		}
		
		return result;
	}
	
	static class KMin2Test implements Runnable
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < 1000; i++) {
				int[] a = Util.generateRandomArray(1000, 1000);
				int[] b = new int[a.length];
				for(int j=0; j < b.length; j++)
				{
					b[j] = a[j];
				}
				
				Arrays.sort(b);
				
				for (int j = 1; j <= a.length; j++) {
					int[] result = kMin2(a, j);
					
					List<Integer> l = new ArrayList<Integer>();
					for(int k=0; k < j; k++)
					{
						l.add(b[k]);
					}
					
					for(int k=0;k < result.length;k++)
					{
						int index = l.indexOf(result[k]);
						
						l.remove(index);
						//l.remove(result[k]);
					}
					
					Util.Assert(l.isEmpty(), "Shit");
					
				}
			}
			
			System.out.println("kMin2(max heap) ok");
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t = null;
		
		t = new Thread(new PartitionTest());
		//t.start();
		
		t = new Thread(new kMinTest());
		//t.start();
		
		t = new Thread(new KMin2Test());
		t.start();
		//int[] a = Util.generateRandomArray(10, 20);
		//int[] result = kMin2(a, 4);
		
	}

}
