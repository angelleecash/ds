package interview;

import info.chenliang.ds.Util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Q6 {

	static class Record
	{
		public int key;
		public int target;
		public int current;
		public Record(int key, int target, int current) {
			super();
			this.key = key;
			this.target = target;
			this.current = current;
		}
	}
	
	private static int getCount(Map<Integer, Integer> m)
	{
		int count = 0;
		
		for (Integer vk : m.keySet()) {
			count += m.get(vk);
		}
		
		return count;
	}
	
	private static Record getRecord(List<Record> l, int v)
	{
		for (int i = 0; i < l.size(); i++) {
			Record record = l.get(i);
			if(record.key == v)
			{
				return record;
			}
		}
		
		return null;
	}
	
	private static int getTotalCount(List<Record> l)
	{
		int count = 0;
		
		for (int i = 0; i < l.size(); i++) {
			count += l.get(i).target;
		}
		
		return count;
	}
	
	private static boolean tryPut(Integer v, int count, int len, List<Record> l, Map<Integer, Integer> m)
	{
		if(v >= len)
		{
			return false;
		}
		
		Record r = getRecord(l, v);
		if(r == null)
		{
			l.add(new Record(v, count, 0));
			
			if(count > 0)
			{
				int countOutput = count;
				
				if(v == count)
				{
					countOutput --;
				}
				
//				return outputCount*v;
			}
			else if(count == 0)
			{
				
			}
			
//			return xxxxxxx;
		}
		else
		{
			if(r.current <= r.target)
			{
				return false;
			}
			
			r.current++;
			return true;
		}
		
		return false;
	}
	
	public static void printSolution(int[]a , List<Record> l)
	{
		for(int i=0 ;i < a.length ;i++)
		{
			System.out.println(a[i]);	
		}
		
		for(int i=0 ;i < a.length ;i++)
		{
			Record r = getRecord(l, a[i]);
			Util.Assert(r != null);
			System.out.println(r.current);
		}
	}
	
	public static void solve(int[] a, int offset, List<Record> l, Map<Integer, Integer> m)
	{
		int v = a[offset];
		
		for(int i=0; i < a.length ;i++)
		{
			if(tryPut(v, i, a.length, l, m))
			{
				if(offset >= a.length - 1)
				{
					printSolution(a, l);		
				}
				else
				{
					solve(a, offset+1, l, m);		
				}
			}
			
			
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {10, 20, 3, 4, 5, 6, 7, 8, 9};
		int[] b = {0, 0, 0, 0, 3, 5, 0, 0, 0};
		
		boolean changed = true;
		while(changed)
		{
			changed = false;
			
			for (int i = 0; i < a.length; i++) {
				int count = 0;
				for (int j = 0; j < b.length; j++) {
					if(b[j] == a[i])
					{
						count ++;
					}
				}
				
				if(b[i] != count)
				{
					changed = true;
					b[i] = count;
					
				}
			}
			
			if(changed)
			{
				System.out.println(Arrays.toString(b));
			}
		}
		
		System.out.println(Arrays.toString(b));
		
	}

}
