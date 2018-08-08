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
 * @brief  		Main Context for an ActionScript Native Extension
 * @author 		Michael Archbold
 * @created		Jan 19, 2012
 * @copyright	http://distriqt.com/copyright/license.txt
 *
 */
package com.distriqt.extension.jobscheduler;

import android.content.Intent;
import android.content.res.Configuration;

import com.adobe.air.ActivityResultCallback;
import com.adobe.air.AndroidActivityWrapper;
import com.adobe.air.StateChangeCallback;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.distriqt.core.utils.IExtensionContext;
import com.distriqt.extension.jobscheduler.controller.JobSchedulerController;
import com.distriqt.extension.jobscheduler.functions.CancelTerminationFunction;
import com.distriqt.extension.jobscheduler.functions.ImplementationFunction;
import com.distriqt.extension.jobscheduler.functions.IsSupportedFunction;
import com.distriqt.extension.jobscheduler.functions.ScheduleTerminationFunction;
import com.distriqt.extension.jobscheduler.functions.VersionFunction;

import java.util.HashMap;
import java.util.Map;

public class JobSchedulerContext extends FREContext implements IExtensionContext, ActivityResultCallback, StateChangeCallback
{
	public static final String TAG = JobSchedulerContext.class.getSimpleName();
	public static final String VERSION = "1.0";
	public static final String IMPLEMENTATION = "Android";


	////////////////////////////////////////////////////////////
	//	VARIABLES
	//

	public boolean v = false;

	private AndroidActivityWrapper _aaw;

	private JobSchedulerController _controller = null;



	////////////////////////////////////////////////////////////
	//	FUNCTIONALITY
	//

	public JobSchedulerContext()
	{
		_aaw = AndroidActivityWrapper.GetAndroidActivityWrapper();
		_aaw.addActivityResultListener( this );
		_aaw.addActivityStateChangeListner( this );
	}


	@Override
	public void dispose() 
	{
		if (_controller != null)
		{
			_controller.dispose();
			_controller = null;
		}
		if (_aaw != null)
		{
			_aaw.removeActivityResultListener( this );
			_aaw.removeActivityStateChangeListner( this );
			_aaw = null;
		}
	}

	
	@Override
	public Map<String, FREFunction> getFunctions() 
	{
		Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();
		
		functionMap.put( "isSupported", 	new IsSupportedFunction() );
		functionMap.put( "version",   		new VersionFunction() );
		functionMap.put( "implementation", 	new ImplementationFunction() );

		functionMap.put( "scheduleTermination", new ScheduleTerminationFunction() );
		functionMap.put( "cancelTermination", new CancelTerminationFunction() );

		return functionMap;
	}


	//
	//	CONTROLLER
	//

	public JobSchedulerController controller()
	{
		if (_controller == null)
		{
			_controller = new JobSchedulerController( this );
		}
		return _controller;
	}


	//
	//	ActivityResultCallback
	//

	@Override
	public void onActivityResult( int requestCode, int resultCode, Intent intent )
	{
		if (_controller != null)
		{
			_controller.onActivityResult( requestCode, resultCode, intent );
		}
	}


	//
	//	StateChangeCallback
	//

	@Override
	public void onActivityStateChanged( AndroidActivityWrapper.ActivityState state )
	{
		if (_controller != null)
		{
			switch (state)
			{
				case STARTED:
					_controller.onStart();
					break;

				case STOPPED:
					_controller.onStop();
					break;

				case PAUSED:
					_controller.onPause();
					break;

				case RESTARTED:
					_controller.onRestart();
					break;

				case DESTROYED:
					_controller.onDestroy();
					break;

				case RESUMED:
					_controller.onResume();
					break;
			}
		}
	}


	@Override
	public void onConfigurationChanged( Configuration paramConfiguration )
	{
		if (_controller != null)
		{
			_controller.onConfigurationChanged( paramConfiguration );
		}
	}


	//
	//	IExtensionContext
	//

	@Override
	public void dispatchEvent( final String code, final String data )
	{
		try
		{
			dispatchStatusEventAsync( code, data );
		}
		catch (Exception e)
		{
		}
	}
	
}
