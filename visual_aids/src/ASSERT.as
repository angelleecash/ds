package
{
	public function ASSERT(condition:Boolean, message:String = "ASSERT"):void
	{
		if(!condition)
		{
			var error:Error = new Error(message);
			throw error;
		}
	}
}