/**
 * 
 */
package com.rishav.hcatexample;

import org.apache.hadoop.hive.cli.CliSessionState;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.CommandNeedRetryException;
import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.processors.CommandProcessorResponse;
import org.apache.hadoop.hive.ql.session.SessionState;

/**
 * @author rishrohi
 *
 */
public class HCatCommandExample {

	public static void main(String[] args) throws CommandNeedRetryException {
		String db = HCatConstants.db;
		String tableOne = HCatConstants.tableOne;

		Driver hcatDriver;
		HiveConf hiveConf = HCatConstants.getHiveConf();
		hcatDriver = new Driver(hiveConf);
		SessionState.start(new CliSessionState(hiveConf));

		CommandProcessorResponse response;
		// drop table
		response = hcatDriver.run("USE " + db);
		response = hcatDriver.run("DROP TABLE " + tableOne);
		if (response.getResponseCode() != 0) {
			System.out.println("Non-zero response code received from Hive");
			System.out.println("Hive error message is: \n"
					+ response.getErrorMessage());
			System.exit(-1);
		}
		System.out.println("Dropped Table " + tableOne);
		// drop database
		response = hcatDriver.run("DROP DATABASE " + db);
		if (response.getResponseCode() != 0) {
			System.out.println("Non-zero response code received from Hive");
			System.out.println("Hive error message is: \n"
					+ response.getErrorMessage());
			System.exit(-1);
		}
		System.out.println("Dropped Database " + db);

	}
}
