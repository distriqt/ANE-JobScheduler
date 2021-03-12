package
{
	import com.distriqt.extension.jobscheduler.JobScheduler;
	
	import flash.display.Sprite;
	import flash.display.StageAlign;
	import flash.display.StageScaleMode;
	import flash.events.Event;
	import flash.text.TextField;
	import flash.text.TextFormat;
	
	
	/**
	 * Sample application for using the JobScheduler Native Extension
	 */
	public class TestJobScheduler extends Sprite
	{
		
		//
		//	VARIABLES
		//
		
		private var _text:TextField;
		
		
		//
		//	FUNCTIONALITY
		//
		
		/**
		 * Class constructor
		 */
		public function TestJobScheduler()
		{
			super();
			stage.align = StageAlign.TOP_LEFT;
			stage.scaleMode = StageScaleMode.NO_SCALE;
			
			_text = new TextField();
			_text.defaultTextFormat = new TextFormat( "_typewriter", 20 );
			addChild( _text );
			
			stage.addEventListener( Event.RESIZE, stage_resizeHandler, false, 0, true );
			
			addEventListener( Event.ACTIVATE, activateHandler, false, 0, true );
			addEventListener( Event.DEACTIVATE, deactivateHandler, false, 0, true );
			
			try
			{
				JobScheduler.init();
				
				message( "JobScheduler Supported: " + JobScheduler.isSupported );
				message( "JobScheduler Version:   " + JobScheduler.instance.version );
				
				//
				//	Add test inits here
				//
			}
			catch (e:Error)
			{
				message( "ERROR::" + e.message );
			}
		}
		
		
		private function message( str:String ):void
		{
			trace( str );
			_text.appendText( str + "\n" );
		}
		
		
		//
		//	EVENT HANDLERS
		//
		
		private function stage_resizeHandler( event:Event ):void
		{
			_text.width = stage.stageWidth;
			_text.height = stage.stageHeight - 100;
		}
		
		
		private function activateHandler( event:Event ):void
		{
			// Cancel termination
			JobScheduler.instance.cancelTermination();
		}
		
		
		private function deactivateHandler( event:Event ):void
		{
			// Schedule termination
			JobScheduler.instance.scheduleTermination( 5000 );
		}
		
		
	}
	
}

