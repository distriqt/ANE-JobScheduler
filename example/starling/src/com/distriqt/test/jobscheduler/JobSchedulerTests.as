/**
 *        __       __               __ 
 *   ____/ /_ ____/ /______ _ ___  / /_
 *  / __  / / ___/ __/ ___/ / __ `/ __/
 * / /_/ / (__  ) / / /  / / /_/ / / 
 * \__,_/_/____/_/ /_/  /_/\__, /_/ 
 *                           / / 
 *                           \/ 
 * http://distriqt.com
 *
 * @author 		"Michael Archbold (ma&#64;distriqt.com)"
 * @created		08/01/2016
 * @copyright	http://distriqt.com/copyright/license.txt
 */
package com.distriqt.test.jobscheduler
{
	import com.distriqt.extension.jobscheduler.JobScheduler;
	
	import flash.display.Bitmap;
	import flash.filesystem.File;
	import flash.geom.Rectangle;
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	import flash.utils.setTimeout;
	
	import starling.core.Starling;
	
	import starling.display.Image;
	import starling.display.Sprite;
	
	/**	
	 */
	public class JobSchedulerTests extends Sprite
	{
		public static const TAG : String = "";
		
		private var _l : ILogger;
		
		private function log( log:String ):void
		{
			_l.log( TAG, log );
		}
		
		
		////////////////////////////////////////////////////////
		//	FUNCTIONALITY
		//
		
		public function JobSchedulerTests( logger:ILogger )
		{
			_l = logger;
			try
			{
				log( "JobScheduler Supported: " + JobScheduler.isSupported );
				if (JobScheduler.isSupported)
				{
					log( "JobScheduler Version:   " + JobScheduler.instance.version );
				}
			}
			catch (e:Error)
			{
				trace( e );
			}
		}
		
		
		////////////////////////////////////////////////////////
		//  
		//
		
		public function scheduleTermination():void
		{
			var success:Boolean = JobScheduler.instance.scheduleTermination( 4000 );
			
			log( "scheduleTermination() = " + success );
		}
		
		
		public function cancelTermination():void
		{
			log( "cancelTermination()" );
			JobScheduler.instance.cancelTermination();
		}
		
		
	}
}
