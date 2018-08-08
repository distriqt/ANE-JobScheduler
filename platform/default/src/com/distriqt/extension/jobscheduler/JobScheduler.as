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
 * @brief  		JobScheduler Native Extension
 * @author 		Michael Archbold
 * @created		Aug 3, 2018
 * @copyright	http://distriqt.com/copyright/license.txt
 */
package com.distriqt.extension.jobscheduler
{
	import flash.events.ErrorEvent;
	import flash.events.Event;
	import flash.events.EventDispatcher;
	
	
	
	/**	
	 * <p>
	 * This class represents the JobScheduler extension.
	 * </p>
	 */
	public final class JobScheduler extends EventDispatcher
	{
		
		////////////////////////////////////////////////////////
		//	CONSTANTS
		//
		
		//
		//	ID and Version numbers
		public static const EXT_CONTEXT_ID			: String = "com.distriqt.JobScheduler";
		private static const EXT_ID_NUMBER			: int = -1;
		
		public static const VERSION					: String = Version.VERSION;
		private static const VERSION_DEFAULT		: String = "0";
		private static const IMPLEMENTATION_DEFAULT	: String = "unknown";
		
		
		//
		//	Error Messages
		private static const ERROR_CREATION			: String = "The native extension context could not be created";
		private static const ERROR_SINGLETON		: String = "The extension has already been created. Use ExtensionClass.service to access the functionality of the class";
		
		
		
		////////////////////////////////////////////////////////
		//	VARIABLES
		//
		
		//
		// Singleton variables
		private static var _instance				: JobScheduler;
		private static var _shouldCreateInstance	: Boolean = false;
		protected var _extensionId			: String = "";
		protected var _extensionIdNumber	: int = -1;
		
		
		
		////////////////////////////////////////////////////////
		//	SINGLETON INSTANCE
		//
		
		public static function get instance():JobScheduler
		{
			createInstance();
			return _instance;
		}
		
		
		private static function createInstance():void
		{
			if(_instance == null)
			{
				_shouldCreateInstance = true; 
				_instance = new JobScheduler();
				_shouldCreateInstance = false;
			}
		}
		
		
		
		////////////////////////////////////////////////////////
		//	FUNCTIONALITY
		//
		
		public function JobScheduler()
		{
			if (_shouldCreateInstance)
			{
				_extensionId = EXT_CONTEXT_ID;
				_extensionIdNumber = EXT_ID_NUMBER;
			}
			else
			{
				throw new Error( ERROR_SINGLETON );
			}
		}
		
		
		public static function init( key:String="" ):void
		{
			createInstance();
		}
		
		
		public function dispose():void
		{
			_instance = null;
		}
		
		
		public static function get isSupported():Boolean
		{
			return false;
		}
		
		
		public function get version():String
		{
			return VERSION;
		}
		
		
		public function get nativeVersion():String
		{
			return VERSION_DEFAULT;
		}
		
		public function get implementation():String
		{
			return "default";
		}
		
		
		public function scheduleTermination( delay:int ):Boolean
		{
			return false;
		}
		
		
		public function cancelTermination():Boolean
		{
			return false;
		}
		
		
		
		////////////////////////////////////////////////////////
		//	INTERNALS
		//
		
		
		
		////////////////////////////////////////////////////////
		//	EVENT HANDLERS
		//
		

	}
}
