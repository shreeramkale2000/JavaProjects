Profiles
1. RunAppJetty - Runs the Web Application Via Jetty Plugin
4. StartJboss - Starts the Local or Embedded Jboss. Post that installs Database Driver Runtime and Datasource via Cli. Finally Deploys the War.
5. StopAppJbossLocal - - Shutdown the Local or Embedded Jboss.
6. BuildAppForDev - Prepares War File for Development Testing using default resources. Also Deploys to Local or Embedded Jboss for Testing.
7. BuildAppForUAT - Prepares War File for UAT Testing using uat resources. Also Deploys to UAT Jboss for Testing.

Note - If Local Jboss Home is not Provided then Fresh Jboss will be Downloaded in target directory. This Jboss will be deleted and reconfigured on every Start.