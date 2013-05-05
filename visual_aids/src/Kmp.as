package
{
	import flash.display.MovieClip;
	import flash.display.Sprite;
	import flash.events.Event;

	[SWF(width="600",height="457",backgroundColor="#FFFFFF",frameRate="30")]
	public class Kmp extends Sprite
	{
		private static const STATE_IDLE:int = 0;
		private static const STATE_GENERATING_NEXT:int = 1;
		private static const STATE_MATCHING:int = 2;
		
		private var _state:int = 0;
		
		private var _source:String;
		private var _pattern:String;
		
		private var _blockSize:Number;
		
		private var _tweenSteps:Vector.<TweenStep> = new Vector.<TweenStep>();
		private var _tweening:Boolean = false;
		private var _tweenPausing:Boolean = false;
		private var _tweenPause:Number;
		
		public function Kmp()
		{
			addEventListener(Event.ADDED_TO_STAGE, onAddedToStage);
			stage.addChild(this);
			
//			var m:int = match("abcgkaddjfk", "addj", 0);
//			trace(m);
		}
		
		private function onAddedToStage(event:Event):void
		{
			addEventListener(Event.ENTER_FRAME, onEnterFrame);	

			init();
		}
		
		private function generateRandomString(length:int):String
		{
			var result:String = "";
			var charBase:Number = "a".charCodeAt(0);
			var len:int = 0;
			while(len < length)
			{
				result += String.fromCharCode(charBase + (int)(Math.random()*3));
				len++;
			}
			
			return result;
		}
		
		private function init():void
		{
			var sourceLength:int = 18;
			var patternLength:int = 5;
			
			var _source:String = generateRandomString(sourceLength);
			var _pattern:String = generateRandomString(patternLength);
			
			var found:int = match(_source, _pattern);
		}
		
		private function generatePlaceHolders(source:String):MovieClip
		{
			var movieClip:MovieClip = generateEmptyPlaceHolders(source.length);
			movieClip.source = source;
			
			for(var i:int=0 ;i < source.length ;i++)
			{
				var np:MovieClip = movieClip.getChildByName("placeHolder"+i) as MovieClip;
				
				np.text.text = source.charAt(i);
				
				movieClip.addChild(np);
			}
			
			return movieClip;
		}
		
		private function generateEmptyPlaceHolders(n:int):MovieClip
		{
			var movieClip:MovieClip = new MovieClip();
			movieClip.placeHolderCount = n;
			for(var i:int=0 ;i < n ;i++)
			{
				var np:MovieClip = new NumberPlaceHolder();
				
				np.graphics.lineStyle(1, 0);
				np.graphics.drawRect(-np.width/2, -np.height/2, np.width, np.height);
				np.x = i*np.width;
				//np.y = 100;
				
				_blockSize = np.width;
				
				np.alpha = 0.6;
				np.name = "placeHolder" + i;
				
				movieClip.addChild(np);
			}
			
			return movieClip;
		}
		
		private function tickIdle():void
		{
			var movieClip:MovieClip = generatePlaceHolders(_source);
			movieClip.x = (stage.stageWidth - movieClip.width) / 2;
			movieClip.y = 300;
			addChild(movieClip);
			
			_state = STATE_GENERATING_NEXT;
		}
		
		
		
		private function tickGeneratingNext():void
		{
			
		}
		
		private function tickMatching():void
		{
			
		}
		
		private function setPlaceHolderText(parent:MovieClip, index:int, text:String):void
		{
			var np:MovieClip = parent.getChildByName("placeHolder"+index) as MovieClip;
			np.text.text = text;
		}
		
		private function generateNext(pattern:String):Vector.<int>
		{
			var movieClip:MovieClip = generatePlaceHolders(pattern);
			movieClip.x = (stage.stageWidth - movieClip.width) / 2 + _blockSize/2;
			movieClip.y = 100;
			addChild(movieClip);
			
			var nextMovieClip:MovieClip = generateEmptyPlaceHolders(pattern.length);
			nextMovieClip.x = movieClip.x;
			nextMovieClip.y = movieClip.y + movieClip.height + 2;
			addChild(nextMovieClip);
			
			var downArrow:MovieClip = new DownArrow();
			//downArrow.x = 0;
			downArrow.y = -movieClip.height/2;
			movieClip.addChild(downArrow);
			
			var next:Vector.<int> = new Vector.<int>(pattern.length);
			next[0] = -1;
			var j:int = -1;
			
			var upArrowHead:MovieClip = new UpArrow();
			upArrowHead.x = -1*_blockSize;
			upArrowHead.y = nextMovieClip.height/2;
			nextMovieClip.addChild(upArrowHead);
			
//			var upArrowTail:MovieClip = new MovieClip();
//			upArrowTail.y = movieClip.height/2;
//			movieClip.addChild(upArrowTail);
			
			setPlaceHolderText(nextMovieClip, 0, "-1");
			updateBounds(movieClip, i, j);
			for(var i:int= 1; i < pattern.length; i++)
			{
				
				animate(downArrow, 1, {x:i*_blockSize});
				
				while(j >= 0 && pattern.charAt(i - 1) != pattern.charAt(j))
				{
					j = next[j];
					
					animate(upArrowHead, 1, {x:j*_blockSize});
					//updateBounds(movieClip, i, j);
				}
				j ++;
				next[i] = j;
				
				var f:Function = function (mi:int, mj:int):Function
		    					 {
									return function():void
									{
										setPlaceHolderText(nextMovieClip, mi, "" + mj);
										updateBounds(movieClip, mi, mj);
									}
														     
							     };
				
				animate(upArrowHead, 1, {x:j*_blockSize, onComplete:f(i, j)});
				
			}
			
			return next;
		}
		
		private function updateBounds(movieClip:MovieClip, i:int, j:int):void
		{
			//return;
			var np:MovieClip = null;
			
			var placeHolderCount:int = movieClip.placeHolderCount as int;
			for(var p:int = 0; p < placeHolderCount; p++)
			{
				np = movieClip.getChildByName("placeHolder"+p) as MovieClip;
				np.bound.visible = false;
			}
			
			for(var x:int = 0; x < j; x++)
			{
				np = movieClip.getChildByName("placeHolder"+x) as MovieClip;
				np.bound.visible = true;
				
				np = movieClip.getChildByName("placeHolder"+(i - (j - x))) as MovieClip;
				np.bound.visible = true;
			}
		}
		
		private function animate(target:Object, duration:Number, vars:Object):void
		{
			var onComplete:Function = vars.onComplete;
			duration = .2;
			var f:Function = function ():void
							 {
								if(onComplete)
								{
									onComplete();
								}
								
								tweenAnimationDone();
							 };
			
			vars.onComplete = f;
			var tweenStep:TweenStep = new TweenStep(target, duration, vars);
			_tweenSteps.push(tweenStep);
		}
		
		private function tweenAnimationDone():void
		{
			_tweenPause = 800;
			_tweenPausing = true;
		}
		
		private function match(source:String, pattern:String, index:int=0):int
		{
			var i:int = index;
			var j:int = 0;
			
			var sourceMovieClip:MovieClip = generatePlaceHolders(source);
			sourceMovieClip.x = (stage.stageWidth - sourceMovieClip.width) / 2 + _blockSize/2;
			sourceMovieClip.y = 300;
			addChild(sourceMovieClip);
			
			var patternMovieClip:MovieClip = generatePlaceHolders(pattern);
			patternMovieClip.x = sourceMovieClip.x;
			patternMovieClip.y = 300+_blockSize+2;
			addChild(patternMovieClip);
			
			var sourceArrow:MovieClip = new DownArrow();
			sourceArrow.x = sourceMovieClip.x; 
			sourceArrow.y = sourceMovieClip.y - _blockSize/2;
			
			addChild(sourceArrow);
			
			var patternArrow:MovieClip = new UpArrow();
			patternArrow.x = patternMovieClip.x;
			patternArrow.y = patternMovieClip.y + _blockSize/2;
			
			addChild(patternArrow);
			
			var next:Vector.<int> = generateNext(pattern); 
			
			while(i <= source.length - pattern.length && j < pattern.length)
			{
				if(j == -1 || source.charAt(i) == pattern.charAt(j))
				{
					if(j == -1)
					{
						i++;
						j++;
						
						if(i <= source.length - pattern.length)
						{
							animate(patternMovieClip, 0.2, {x:sourceMovieClip.x + i*_blockSize});
							animate(sourceArrow, 0.2, {x:sourceMovieClip.x + i*_blockSize});
							animate(patternArrow, 0.2, {x:sourceMovieClip.x + i*_blockSize});
						}
						
						
					}
					else
					{
						i++;
						j++;
						
						animate(sourceArrow, 1, {x:sourceMovieClip.x + i*_blockSize});
						animate(patternArrow, 1, {x:sourceMovieClip.x + i*_blockSize});	
					}
					
				}
				else
				{
					var oldJ:int = j;
					j = next[j];
					if(j != -1)
					{
						animate(patternMovieClip, 1, {x:sourceMovieClip.x + i*_blockSize - j*_blockSize});	
					}
					
				}
			}
			
			if(j >= pattern.length)
			{
				return i - pattern.length;
			}
			
			return -1;	
		}
		
		private function resetUi():void
		{
			
			for(var i:int=numChildren-1 ;i >= 0 ;i--)
			{
				removeChildAt(i);
			}
		}
		
		private function onEnterFrame(event:Event):void
		{
			/*
			if(_state == STATE_IDLE)
			{
				tickIdle();		
			}
			else if(_state == STATE_GENERATING_NEXT)
			{
				tickGeneratingNext();
			}
			else if(_state == STATE_MATCHING)
			{
				tickMatching();
			}
			*/
			if(_tweening)
			{
				if(_tweenPausing)
				{
					_tweenPause -= 30;
					if(_tweenPause <= 0)
					{
						_tweenPausing = false;
						_tweening = false;
						
						if(_tweenSteps.length <= 0)
						{
							resetUi();
							init();
						}
					}
				}
			}
			else
			{
				if(_tweenSteps.length > 0)
				{
					var tweenStep:TweenStep = _tweenSteps[0];
					
					_tweenSteps.splice(0, 1);
					
					tweenStep.execute();
					
					_tweening = true;
					_tweenPausing = false;
				}
			}
		}
	}
}