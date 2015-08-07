package com.rishav.hcatexample;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hive.hcatalog.cli.SemanticAnalysis.HCatSemanticAnalyzer;

public class HCatConstants {
	public static String db = "hcat_test_db";
	public static String tableOne = "test_table";

	public static HiveConf getHiveConf() {
		HiveConf hiveConf;
		hiveConf = new HiveConf();
		hiveConf.set("hive.metastore.local", "false");
		hiveConf.setVar(HiveConf.ConfVars.METASTOREURIS,
				"thrift://hive_server_ip.com:9083");
		hiveConf.setIntVar(HiveConf.ConfVars.METASTORETHRIFTCONNECTIONRETRIES,
				3);
		hiveConf.set(HiveConf.ConfVars.SEMANTIC_ANALYZER_HOOK.varname,
				HCatSemanticAnalyzer.class.getName());
		hiveConf.set(HiveConf.ConfVars.PREEXECHOOKS.varname, "");
		hiveConf.set(HiveConf.ConfVars.POSTEXECHOOKS.varname, "");
		hiveConf.set(HiveConf.ConfVars.HIVE_SUPPORT_CONCURRENCY.varname,
				"false");
		System.setProperty(HiveConf.ConfVars.PREEXECHOOKS.varname, " ");
		System.setProperty(HiveConf.ConfVars.POSTEXECHOOKS.varname, " ");
		return hiveConf;
	}

}
