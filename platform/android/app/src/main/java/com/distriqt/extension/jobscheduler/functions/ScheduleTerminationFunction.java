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
 * @author marchbold
 * @created 06/08/2018
 * @copyright http://distriqt.com/copyright/license.txt
 */
package com.distriqt.extension.jobscheduler.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.distriqt.extension.jobscheduler.JobSchedulerContext;
import com.distriqt.extension.jobscheduler.utils.Errors;

public class ScheduleTerminationFunction implements FREFunction
{

	@Override
	public FREObject call( FREContext context, FREObject[] args )
	{
		FREObject result = null;
		try
		{
			JobSchedulerContext ctx = (JobSchedulerContext)context;

			int delay = args[0].getAsInt();

			boolean success = ctx.controller().scheduleTermination( delay );

			result = FREObject.newObject( success );
		}
		catch (Exception e)
		{
			Errors.handleException( e );
		}
		return result;
	}

}
