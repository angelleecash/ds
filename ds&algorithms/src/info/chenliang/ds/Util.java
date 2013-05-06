package info.chenliang.ds;

public class Util {
	public static void Assert(boolean flag, String message)
	{
		if(!flag)
		{
			throw new RuntimeException(message);
		}
	}
}
