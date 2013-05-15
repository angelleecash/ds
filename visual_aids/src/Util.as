package
{
	import com.greensock.TweenLite;
	
	import flash.display.MovieClip;

	public class Util
	{
		public function Util()
		{
		}
		
		public static function generateRandomPointsMovieClip(container:MovieClip, total:int, width:Number, height:Number, margin:Number, color:int):Vector.<MovieClip>
		{
			var points:Vector.<MovieClip> = new Vector.<MovieClip>(); 
			
			var count:int = 0;
			
			while(count < total)
			{
				var pointMovieClip:MovieClip = new MovieClip();
				
				var size:Number = 3;
				
				fillCircle(pointMovieClip, color, size);
				
				
				var x:int = Math.random()*(width-margin*2);
				x+= margin;
				
				var y:int = Math.random()*(height-margin*2);
				y+=margin;
				
				
				pointMovieClip.x = x;
				pointMovieClip.y = y;
				
				pointMovieClip.scaleX = pointMovieClip.scaleY = 0.0;
				
				container.addChild(pointMovieClip);
				
				//this["point_" + count] = pointMovieClip; 
				points.push(pointMovieClip);
				
				TweenLite.to(pointMovieClip, 0.2, {scaleX:1.0, scaleY:1.0});
				
				count ++;
			}
			
			return points;
		}
		
		public static function fillCircle(mv:MovieClip, color:int, size:Number):void
		{
			mv.graphics.beginFill(color, 1.0);
			mv.graphics.drawCircle(0, 0, size);
			mv.graphics.endFill();
		}
		
		public static function comparePointByYR(mv1:MovieClip, mv2:MovieClip):int
		{
			return comparePointByY(mv1, mv2) * -1;
		}
		
		public static function comparePointByY(mv1:MovieClip, mv2:MovieClip):int
		{
			if(mv1.y == mv2.y)
			{
				return 0;
			}
			else if(mv1.y < mv2.y)
			{
				return -1;	
			}
			else
			{
				return 1;
			}
		}
		
		public static function comparePointByX(mv1:MovieClip, mv2:MovieClip):int
		{
			if(mv1.x == mv2.x)
			{
				return 0;
			}
			else if(mv1.x < mv2.x)
			{
				return -1;	
			}
			else
			{
				return 1;
			}
		}
		
		public static function comparePointByXR(mv1:MovieClip, mv2:MovieClip):int
		{
			return comparePointByX(mv1, mv2) * -1;
		}
	}
}