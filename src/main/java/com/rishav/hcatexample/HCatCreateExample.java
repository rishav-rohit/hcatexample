/**
 * 
 */
package com.rishav.hcatexample;

import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hive.hcatalog.api.HCatClient;
import org.apache.hive.hcatalog.api.HCatCreateDBDesc;
import org.apache.hive.hcatalog.api.HCatCreateTableDesc;
import org.apache.hive.hcatalog.common.HCatException;
import org.apache.hive.hcatalog.data.schema.HCatFieldSchema;
import org.apache.hive.hcatalog.data.schema.HCatFieldSchema.Type;

/**
 * @author rishrohi
 *
 */
public class HCatCreateExample {

	public static void main(String[] args) throws HCatException {

		String db = HCatConstants.db;
		String tableOne = HCatConstants.tableOne;
		// create HCatClient
		HCatClient client = HCatClient.create(new Configuration(HCatConstants
				.getHiveConf()));
		// create database if not exists
		HCatCreateDBDesc dbDesc = HCatCreateDBDesc.create(db).ifNotExists(true)
				.build();
		client.createDatabase(dbDesc);

		// drop table if exists
		client.dropTable(db, tableOne, true);

		// create table
		ArrayList<HCatFieldSchema> cols = new ArrayList<HCatFieldSchema>();
		cols.add(new HCatFieldSchema("id", Type.INT, "id comment"));
		cols.add(new HCatFieldSchema("value", Type.STRING, "value comment"));
		HCatCreateTableDesc tableDesc = HCatCreateTableDesc
				.create(db, tableOne, cols).fileFormat("orcfile")
				.ifNotExists(true).build();
		client.createTable(tableDesc);
		System.out.println("Created Hive table");
		client.close();
	}
}
