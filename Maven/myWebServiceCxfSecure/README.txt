Profiles
1. StartJboss - Starts the Local or Embedded Jboss. Post that installs Database Driver Runtime and Datasource via Cli. Finally Deploys the War.
2. StopAppJbossLocal - - Shutdown the Local or Embedded Jboss.
3. RunCxfServiceDev - Prepares War File for Development Testing using default resources. Also Deploys to Local or Embedded Jboss for Testing.
4. RunCxfServiceUAT - Prepares War File for UAT Testing using uat resources. Also Deploys to UAT Jboss for Testing.

Note - maven cxf codegen plugin is used to generate stub classes from wsdl in generate-sources phase. Run once for generated source before coding.