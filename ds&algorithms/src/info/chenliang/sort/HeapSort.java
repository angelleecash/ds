package info.chenliang.sort;

import java.util.Arrays;
import java.util.List;

import info.chenliang.ds.MaxHeap;
import info.chenliang.ds.Util;

public class HeapSort {
	public static int[] sort(int[] a)
	{
		MaxHeap heap = new MaxHeap(a);
		
		List<Integer> data = heap.getData();
		
		for(; heap.getSize() >= 2; heap.setSize(heap.getSize()-1))
		{

			heap.build();
			
			int temp = data.get(heap.getSize()-1);
			data.set(heap.getSize()-1, data.get(0));
			data.set(0, temp);
			
		}
		
		int[] result = new int[a.length];
		for(int i=0;i < result.length;i ++)
		{
			result[i] = heap.getData().get(i);
		}
		return result;
	}
	
	static class HeapSortTest implements Runnable
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < 1000000; i++) {
				int[] a = Util.generateRandomArray(500, 200000);
				int[] b = a.clone();
				
				a = sort(a);
				Arrays.sort(b);
				
				for (int j = 0; j < b.length; j++) {
					Util.Assert(a[j] == b[j], "shit");
				}
			}
			
			System.out.println("Heap sort ok");
		}
		
	}
	
	public static void main(String[] args) {
		Thread t = null;
		
		t = new Thread(new HeapSortTest());
		t.start();
	}
}
