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
 * @brief
 * @author 		"Michael Archbold (ma&#64;distriqt.com)"
 * @created 16/11/2017
 * @copyright http://distriqt.com/copyright/license.txt
 */
package com.distriqt.extension.jobscheduler.controller;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

import com.distriqt.core.ActivityStateListener;
import com.distriqt.core.utils.IExtensionContext;
import com.distriqt.extension.jobscheduler.services.TerminateAppJobService;
import com.distriqt.extension.jobscheduler.utils.Errors;
import com.distriqt.extension.jobscheduler.utils.Logger;

public class JobSchedulerController extends ActivityStateListener
{
	////////////////////////////////////////////////////////////
	//	CONSTANTS
	//

	public static final String TAG = JobSchedulerController.class.getSimpleName();






	////////////////////////////////////////////////////////////
	//	VARIABLES
	//


	private IExtensionContext _extContext;


	////////////////////////////////////////////////////////////
	//	FUNCTIONALITY
	//

	public JobSchedulerController( IExtensionContext extensionContext )
	{
		_extContext = extensionContext;
	}




	public void dispose()
	{

	}


	public boolean isSupported()
	{
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
	}



	public boolean scheduleTermination( int delay )
	{
		Logger.d( TAG, "scheduleTermination( %d )", delay );
		try
		{
			JobScheduler jobScheduler = (JobScheduler)_extContext.getActivity().getSystemService( Context.JOB_SCHEDULER_SERVICE );

			JobInfo.Builder builder = new JobInfo.Builder(
						TerminateAppJobService.TERMINATE_APP_JOB_ID,
						new ComponentName( _extContext.getActivity(), TerminateAppJobService.class ))
					.setMinimumLatency( delay )
					.setOverrideDeadline( delay + 1000 );

			//PersistableBundle extras = new PersistableBundle();
			//String workDuration = mDurationTimeEditText.getText().toString();
			//if (TextUtils.isEmpty(workDuration)) {
			//	workDuration = "1";
			//}
			//extras.putLong(WORK_DURATION_KEY, Long.valueOf(workDuration) * 1000);
			//
			//builder.setExtras(extras);

			int result = jobScheduler.schedule( builder.build() );

			return result == JobScheduler.RESULT_SUCCESS;
		}
		catch (Exception e)
		{
			Errors.handleException( e );
		}
		return false;
	}


	public boolean cancelTermination()
	{
		Logger.d( TAG, "cancelTermination()" );
		try
		{
			JobScheduler jobScheduler = (JobScheduler)_extContext.getActivity().getSystemService( Context.JOB_SCHEDULER_SERVICE );
			jobScheduler.cancel( TerminateAppJobService.TERMINATE_APP_JOB_ID );
			return true;
		}
		catch (Exception e)
		{
			Errors.handleException( e );
		}
		return false;
	}


}
