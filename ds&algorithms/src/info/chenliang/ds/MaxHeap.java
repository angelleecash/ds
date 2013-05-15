package info.chenliang.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxHeap {
	List<Integer> data;
	int size;
	
	public MaxHeap(int[] a)
	{
		this(a, 0, a.length);
	}
	
	public MaxHeap(int[] a, int offset, int n)
	{
		data = new ArrayList<Integer>();
		for(int i=offset; i < offset+n;i ++)
		{
			data.add(a[i]);
		}
		size = data.size();
		build();
	}
	
	public int getSize()
	{
		return size;
	}
	
	public void setSize(int size)
	{
		this.size = size;
	}
	
	public void put(int v)
	{
		
	}
	
	public int max()
	{
		return data.get(0);
	}
	
	private int parent(int i)
	{
		return i / 2;
	}
	
	private int left(int i)
	{
		return i*2+1;
	}
	
	private int right(int i)
	{
		return i*2 + 2;
	}
	
	public List<Integer> getData()
	{
		return data;
	}
	
	public int getData(int i)
	{
		return data.get(i);
	}
	
	public void maxHeapify(int i)
	{
		int largest = i;
		
		int left = left(i);
		if(left < size && getData(left) > getData(i))
		{
			largest = left;
		}
		
		int right = right(i);
		if(right < size && getData(right) > getData(largest))
		{
			largest = right;
		}
		
		if(i != largest)
		{
			int temp = getData(i);
			data.set(i, getData(largest));
			data.set(largest, temp);
			
			maxHeapify(largest);
		}
	}
	
	public void build()
	{
		for(int i=size/2-1; i>=0;i--)
		{
			maxHeapify(i);
		}
	}
	
	static class MaxHeapTest implements Runnable
	{

		@Override
		public void run() {
			for (int i = 0; i < 1000000; i++) {
				int[] a = Util.generateRandomArray(500, 10000);
				int[] b = a.clone();
				
				Arrays.sort(b);
				
				MaxHeap heap = new MaxHeap(a);
				Util.Assert(heap.data.get(0) == b[b.length-1]);
			}
			
			System.out.println("Max heap ok");
		}
		
	}
	
	public static void main(String[] args) {
		Thread t = null;
		
		t = new Thread(new MaxHeapTest());
		t.start();
		
//		int[] a = {-18, -12, 16, -4, -4};
//		MaxHeap heap = new MaxHeap(a);
//	
//		System.out.println(heap.data);
	}

}
