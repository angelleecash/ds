package
{
	import flash.display.MovieClip;
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	
	[SWF(width="600",height="457",backgroundColor="#FFFFFF",frameRate="30")]
	public class Base extends MovieClip
	{
		protected var container:MovieClip;
		
		public function Base()
		{
			addEventListener(Event.ADDED_TO_STAGE, onAddedToStage);
			stage.addChild(this);
			
			
			container = new MovieClip();
			addChild(container);
		}
		
		protected function onAddedToStage(event:Event):void
		{
			addEventListener(Event.ENTER_FRAME, onEnterFrame);	
			stage.addEventListener(KeyboardEvent.KEY_UP, onKeyUp);
			init();
		}
		
		protected function onKeyUp(event:KeyboardEvent):void
		{
			
		}
		
		protected function init():void
		{
			
		}
		
		private function onEnterFrame(event:Event):void
		{
			tick();
		}
		
		protected function tick():void
		{
			
		}
	}
}