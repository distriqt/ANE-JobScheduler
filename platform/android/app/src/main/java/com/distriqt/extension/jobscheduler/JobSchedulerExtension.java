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
 * @brief Main Extension implementation for this ANE
 * @author Michael Archbold (https://github.com/marchbold)
 * @created Jan 19, 2012
 * @copyright http://distriqt.com/copyright/license.txt
 *
 */
package com.distriqt.extension.jobscheduler;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class JobSchedulerExtension implements FREExtension
{
	public static JobSchedulerContext context;

	public static String ID = "com.distriqt.JobScheduler";


	@Override
	public FREContext createContext( String arg0 )
	{
		context = new JobSchedulerContext();
		return context;
	}


	@Override
	public void dispose()
	{
		context = null;
	}


	@Override
	public void initialize()
	{
	}

}
