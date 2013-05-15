package
{
	import flash.display.MovieClip;
	import flash.events.KeyboardEvent;
	import flash.geom.Point;
	import flash.text.TextField;
	import flash.ui.Keyboard;

	public class ClosestPoints extends Base
	{
		private static const POINT_COUNT:int = 40;
		private var points:Vector.<MovieClip> = new Vector.<MovieClip>(); 
		
		public function ClosestPoints()
		{
			super();	
			
			var tf:TextField = new TextField();
			tf.selectable = false;
			tf.mouseEnabled = false;
			tf.text = "Press R to refresh";
			addChild(tf);
		}
		
		override protected function onKeyUp(event:KeyboardEvent):void
		{
			if(event.keyCode == Keyboard.R)
			{
				removeAllPoints();
				
				points = Util.generateRandomPointsMovieClip(container, 30, stage.stageWidth, stage.stageHeight, 20, 0xff00);
				points.sort(Util.comparePointByX);
				var cp:Vector.<MovieClip> = closestPair(points);
				for(var i:int=0 ;i < cp.length;i++)
				{
					Util.fillCircle(cp[i], 0xff0000, 3);
				}
			}
		}
		
		private function removeAllPoints():void
		{
			points = new Vector.<MovieClip>();
			
			while(container.numChildren > 0)
			{
				container.removeChildAt(0);
			}
		}
		
		private function distance(mv1:MovieClip, mv2:MovieClip):Number
		{
			var point:Point = new Point(mv1.x-mv2.x, mv1.y-mv2.y);
			return point.length;
		}
		
		private function closestPair(points:Vector.<MovieClip>):Vector.<MovieClip>
		{	
			if(points.length <= 2)
			{
				return points;
			}
			
			if(points.length == 3)
			{
				var p0:MovieClip = points[0];
				var p1:MovieClip = points[1];
				var p2:MovieClip = points[2];
				
				
				var v01:Point = new Point(p1.x-p0.x, p1.y-p0.y);
				var v02:Point = new Point(p2.x-p0.x, p2.y-p0.y);
				var v12:Point = new Point(p2.x-p1.x, p2.y-p1.y);
				
				if(v01.length <= v02.length)
				{
					if(v01.length <= v12.length)
					{
						return Vector.<MovieClip>([p0, p1]);
					}
					else
					{
						return Vector.<MovieClip>([p1, p2]);
					}
				}
				else
				{
					if(v02.length <= v12.length)
					{
						return Vector.<MovieClip>([p0, p2]);
					}
					else
					{
						return Vector.<MovieClip>([p1, p2]);
					}
				}
			}
			
			var l:int = points.length/2;
			var r:int = points.length - l;
			
			var left:Vector.<MovieClip> = new Vector.<MovieClip>();
			for(var i:int=0;i < l;i++)
			{
				left.push(points[i]);
			}
			
			var right:Vector.<MovieClip> = new Vector.<MovieClip>();
			for(var j:int=l;j < points.length;j++)
			{
				right.push(points[j]);
			}
			
			var sx:Number = points[l-1].x;
			
			var lc:Vector.<MovieClip> = closestPair(left);
			var rc:Vector.<MovieClip> = closestPair(right);
			
			ASSERT(lc.length == 2, "lc " + lc.length + " left " + left.length);
			ASSERT(rc.length == 2, "rc " + rc.length + " right " + right.length);
			
			var d1:Number = distance(lc[0], lc[1]);
			var d2:Number = distance(rc[0], rc[1]);
			
			var d:Number = d1 < d2 ? d1 : d2;
			
			var cross:Vector.<MovieClip> = new Vector.<MovieClip>();
			for(var k:int=0; k<points.length;k++)
			{
				if(Math.abs(points[k].x - sx) <= d)
				{
					cross.push(points[k]);
				}
			}
			
			//ASSERT(cross.length <= 8, "" + cross.length);
			
			if(cross.length < 2)
			{
				return d1 < d2 ? lc : rc;
			}
			
			cross.sort(Util.comparePointByY);
			
			var min:Number = d;
			var minIndex:int=-1;
			for(var t:int=1; t < cross.length;t++)
			{
				var td:Number = distance(cross[0], cross[t]);
				if(td < min)
				{
					min = td;
					minIndex = t;
				}
			}
			
			if(minIndex >= 0)
			{
				return Vector.<MovieClip>([cross[0], cross[minIndex]]);
			}
			else
			{
				return d1 < d2 ? lc : rc;
			}
		}
		
	}
}