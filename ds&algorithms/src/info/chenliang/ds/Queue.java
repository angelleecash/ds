package info.chenliang.ds;


import java.util.ArrayList;
import java.util.List;

public class Queue {

	List<Object> items = new ArrayList<Object>();
	public void enter(Object o)
	{
		items.add(o);
	}
	
	public Object leave()
	{
		return items.remove(0);
	}
	
	public boolean isEmpty()
	{
		return items.size() <= 0;
	}
	
	public static void main(String[] args) {
		
	}

}
