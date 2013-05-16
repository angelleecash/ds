package interview;

import java.util.ArrayList;
import java.util.List;

import info.chenliang.ds.Util;
import interview.Q1.Tree;

public class Q9 {

	private static boolean verifyLrp(int[] data, int length)
	{
		int root = data[length-1];
		
		int i=0;
		for(;i<length-1;i++)
		{
			if(data[i] > root)
			{
				break;
			}
		}
		
		int j=i;
		for(;j<length-1;j++)
		{
			if(data[j] < root)
			{
				return false;
			}
		}
		
		boolean leftOk = true;
		if(i > 0)
		{
			leftOk = verifyLrp(data, i);
		}
		
		boolean rightOk = true;
		if(i < length-1)
		{
			rightOk = verifyLrp(data, length-i-1);
		}
		
		return leftOk&&rightOk;
	}
	
	private static String generateString(int[] a)
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < a.length; i++) {
			sb.append(a[i] + "*");
		}
		
		return sb.toString();
	}
	
	private static String generateString(List<Integer> a)
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < a.size(); i++) {
			sb.append(a.get(i) + "*");
		}
		
		return sb.toString();
	}
	
	static class VerifyLrpTest implements Runnable
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			for (int j = 0; j < 1000000; j++) {
				int[] a = Util.generateUnduplicatedRandomArray(100, 1000);
				Tree tree = new Tree();
				for(int i=0; i < a.length; i++)
				{
					tree.add(a[i]);
				}
				
				List<Integer> l = new ArrayList<Integer>();
				Q1.lrp2(tree.root, l);
				
				String s1 = generateString(a);
				String s2 = generateString(l);
				
				boolean ok = verifyLrp(a, a.length);
				if(ok)
				{
					Util.Assert(s1.equals(s2));
				}
				else
				{
					Util.Assert(!s1.equals(s2));
				}
			}
			
			
			
			System.out.println("Verify Lrp ok");
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] a = {5,7,6,9,11,10,8};
//		System.out.println(verifyLrp(a, a.length));
//		
//		int[]b = {7,4,6,5};
//		System.out.println(verifyLrp(b, b.length));
		
		Thread t = new Thread(new VerifyLrpTest());
		t.start();
	}

}
