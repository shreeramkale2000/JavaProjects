package com.test.cli;

import org.jboss.as.cli.scriptsupport.CLI;
import org.jboss.as.cli.scriptsupport.CLI.Result;
import org.jboss.dmr.ModelNode;

public class Cli {

	public static void main(String[] args) {
		CLI cli = CLI.newInstance();
		cli.connect("localhost", 9990, "admin", "password".toCharArray());

		//Result result = cli.cmd(":read-attribute(name=server-state) ");
		Result result = cli.cmd("undeploy myWebApp.war");
		ModelNode response = result.getResponse();
		ModelNode serverstate = response.get("result");

		System.out.println("Current server state: " + serverstate);
		
		result = cli.cmd("deploy myWebApp.war");
		response = result.getResponse();
		serverstate = response.get("result");

		System.out.println("Current server state: " + serverstate);

		cli.disconnect();
	}

}
