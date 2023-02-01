---
title: Application Termination
sidebar_label: Application Termination
---


## Schedule Termination

To schedule a termination, call the `scheduleTermination` method. The method takes one parameter which is the delay (in milliseconds) before termination will occur.

Calling this function will schedule a job with the Android system that will be called at the specified delay.

For example to terminate the application in 4 seconds:

```actionscript
JobScheduler.instance.scheduleTermination( 4000 );
```



## Cancel Termination

If you wish to cancel the schedule termination simply call `cancelTermination`. This will remove the scheduled job from the system.

```actionscript
JobScheduler.instance.cancelTermination();
```





