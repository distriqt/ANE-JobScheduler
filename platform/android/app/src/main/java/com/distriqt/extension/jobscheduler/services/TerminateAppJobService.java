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
 * @author Michael Archbold (https://github.com/marchbold)
 * @created 05/08/2018
 * @copyright http://distriqt.com/copyright/license.txt
 */
package com.distriqt.extension.jobscheduler.services;

import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Context;

import com.distriqt.extension.jobscheduler.utils.Logger;

public class TerminateAppJobService extends JobService
{
	////////////////////////////////////////////////////////////
	//	CONSTANTS
	//

	public static final String TAG = TerminateAppJobService.class.getSimpleName();

	public static final int TERMINATE_APP_JOB_ID = 4323;



	////////////////////////////////////////////////////////////
	//	FUNCTIONALITY
	//

	@Override
	public boolean onStartJob( JobParameters params )
	{
		Logger.d( TAG, "onStartJob()" );
		try
		{
			// Cancel this job
			JobScheduler jobScheduler = (JobScheduler)this.getSystemService( Context.JOB_SCHEDULER_SERVICE );
			jobScheduler.cancel( TERMINATE_APP_JOB_ID );


			// Force terminate the application process
			android.os.Process.killProcess( android.os.Process.myPid() );


			//if (JobSchedulerExtension.context != null && JobSchedulerExtension.context.getActivity() != null)
			//{
			//	Activity activity = JobSchedulerExtension.context.getActivity();
			//	if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21)
			//	{
			//		activity.finishAffinity();
			//	}
			//	else if (Build.VERSION.SDK_INT >= 21)
			//	{
			//		activity.finishAndRemoveTask();
			//	}
			//	else
			//	{
			//		activity.finish();
			//	}
			//}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean onStopJob( JobParameters params )
	{
		Logger.d( TAG, "onStopJob()" );
		return false;
	}


}
