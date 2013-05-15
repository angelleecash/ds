package
{
	import flash.display.MovieClip;
	import flash.events.KeyboardEvent;
	import flash.geom.Point;
	import flash.text.TextField;
	import flash.ui.Keyboard;
	

	public class GenerateConvexHull extends Base
	{
		private var points:Vector.<MovieClip> = new Vector.<MovieClip>(); 
		
		private static const POINT_COUNT:int = 40;
		
		
		private var _p0:MovieClip;
		
		public function GenerateConvexHull()
		{
			var tf:TextField = new TextField();
			tf.text = "Press R to refresh";
			tf.selectable = false;
			tf.mouseEnabled = false;
			
			addChild(tf);
			
			regenerate();
		}
		
		private function removeAllPoints():void
		{
			points = new Vector.<MovieClip>();
			
			while(container.numChildren > 0)
			{
				container.removeChildAt(0);
			}
		}
		
		private function regenerate():void
		{
			removeAllPoints();
			points = Util.generateRandomPointsMovieClip(container, POINT_COUNT, stage.stageWidth, stage.stageHeight, 20, 0xff);
			
			points.sort(Util.comparePointByYR);
			
			var p0:MovieClip = points.splice(0, 1)[0];
			_p0 = p0;
			
			points.sort(comparePointByAngle);
			
			var size:Number = 3;
			
			Util.fillCircle(p0, 0xff00ff, size);
			var co:int = 0;
			for(var j:int=0; j<points.length;j++)
			{
				Util.fillCircle(points[j], co+j*(0xff/points.length), size);
				var tf:TextField = new TextField();
				tf.text = ""+j;
				tf.selectable = false;
				tf.mouseEnabled = false;
				points[j].addChild(tf);
			}				
			
			var stack:Vector.<MovieClip> = new Vector.<MovieClip>();
			
			stack.push(p0);
			stack.push(points[0]);
			stack.push(points[1]);
			points.splice(0,2)
			
			var cc:Number = cross(stack[0], stack[1], stack[2]);
			
			//return;
			
			
			for(var i:int=0 ;i < points.length; i++)
			{
				
				var pi:MovieClip = points[i];
				var p1:MovieClip = stack[stack.length-2];
				var p2:MovieClip = stack[stack.length-1];
				
				//   pi
				//     p2
				//   p1
				//
				for(;cross(p1, p2, pi) >= 0;)
				{
					stack.splice(stack.length-1,1);
					p1 = stack[stack.length-2];
					p2 = stack[stack.length-1];	
				}
				
				stack.push(pi);
			}
			
			for(var k:int=0; k<stack.length;k++)
			{
				Util.fillCircle(stack[k], 0xff00, size);
			}
		}
		
		override protected function onKeyUp(event:KeyboardEvent):void
		{
			if(event.keyCode == Keyboard.R)
			{
				regenerate();
			}
		}
		
		private function cross(p1:MovieClip, p2:MovieClip, pi:MovieClip):Number
		{
			var v12:Point = new Point(p2.x-p1.x, p2.y-p1.y);
			var v1i:Point = new Point(pi.x-p1.x, pi.y-p1.y);
			
			var cross:Number = v12.x * v1i.y - v1i.x * v12.y;	
			
			return cross;
		}
		
	
		private function comparePointByAngle(mv1:MovieClip, mv2:MovieClip):int
		{
			var v01:Point = new Point(mv1.x-_p0.x, mv1.y-_p0.y);
			var v02:Point = new Point(mv2.x-_p0.x, mv2.y-_p0.y);
			
			v01.normalize(1);
			v02.normalize(1);
			
			if(v01.x == v02.x)
			{
				return 0;
			}
			else if(v01.x > v02.x)
			{
				return -1;	
			}
			else
			{
				return 1;
			}
		}
		
		override protected function init():void
		{
			
		}
		
		override protected function tick():void
		{
			
		}
	}
}