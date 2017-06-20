Profiles
1. RunApp - Runs the Program Main Method via Maven Execution Plugin. You can set any system properties (if required) via maven properties plugin in this profile.
2. BuildAppForDev - Prepares Runnable Jar File for Development Testing using default resources
3. BuildAppForUAT - Prepares Runnable Jar File for UAT Testing using uat resources

Note - 
1. Maven Jar Plugin Forms Runnable Jar with dependencies in lib folder
2. Maven Assembly Plugin Forms Runnable Jar