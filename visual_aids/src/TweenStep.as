package
{
	import com.greensock.TweenLite;

	public class TweenStep
	{
		private var _target:Object;
		private var _duration:Number;
		private var _vars:Object;
		
		public function TweenStep(target:Object, duration:Number, vars:Object)
		{
			_target = target;
			_duration = duration;
			_vars = vars;
		}
		
		public function execute():void
		{
			TweenLite.to(_target, _duration, _vars);
		}
	}
}