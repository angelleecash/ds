package info.chenliang.ds;


public class Kmp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		System.out.println(Arrays.toString(generateNext("abcdefg")));
		System.out.println(Arrays.toString(generateNext2("abcdefg")));
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println(Arrays.toString(generateNext("abcabd")));
		System.out.println(Arrays.toString(generateNext2("abcabd")));
		*/
		
		String s = "abcdefg";
		System.out.println(s.indexOf("q"));
		System.out.println(indexOf(s, "q", 0));
		
	}
	
	private static int[] generateNext(String pattern)
	{
		int[] next = new int[pattern.length()];
		
		next[0] = -1;
		int j = -1;
		for(int i=1; i < pattern.length() ; i++)
		{
			while(j >= 0 && pattern.charAt(i-1) != pattern.charAt(j))
				j = next[j];
			
			j++;
			next[i] = j;
		}
		
		return next;
	}
	
	private static int[] generateNext2(String pattern)
	{
		int[] next = new int[pattern.length()];
		
		next[0] = -1;
		int j = -1;
		int i=1;
		while(i < pattern.length()-1)
		{
			if(j == -1 || pattern.charAt(i) == pattern.charAt(j))
			{
				i ++; j++;
				next[i] = j;
			}
			else
			{
				j = next[j];
			}
		}
		
		return next;
	}
	
	public static int indexOf(String source, String pattern, int index)
	{
		int i = index, j = 0;
		int[] next = generateNext(pattern); 
		while(i < source.length() && j < pattern.length())
		{
			if(source.charAt(i) == pattern.charAt(j))
			{
				i++;
				j++;
			}
			else
			{
				j = next[j];
				
				if(j < 0)
				{
					i ++;
					//j ++ is confusing
					j = 0;
				}
			}
		}
		
		if(j >= pattern.length()) return i - pattern.length(); 
		
		return -1;
	}

}
