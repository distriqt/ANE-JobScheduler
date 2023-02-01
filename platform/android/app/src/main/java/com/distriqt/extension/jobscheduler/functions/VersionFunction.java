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
 * @brief Version function implementation for this ANE
 * @author Michael Archbold (https://github.com/marchbold)
 * @created Apr 10, 2012
 * @copyright http://distriqt.com/copyright/license.txt
 *
 */
package com.distriqt.extension.jobscheduler.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;
import com.distriqt.core.utils.FREUtils;
import com.distriqt.extension.jobscheduler.JobSchedulerContext;

public class VersionFunction implements FREFunction
{

	@Override
	public FREObject call( FREContext context, FREObject[] args )
	{
		FREObject result = null;
		try
		{
			result = FREObject.newObject( JobSchedulerContext.VERSION );
		}
		catch (FREWrongThreadException e)
		{
			FREUtils.handleException( context, e );
		}
		return result;
	}

}
