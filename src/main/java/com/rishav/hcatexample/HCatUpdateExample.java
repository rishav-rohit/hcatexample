/**
 * 
 */
package com.rishav.hcatexample;

import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hive.hcatalog.api.HCatClient;
import org.apache.hive.hcatalog.common.HCatException;
import org.apache.hive.hcatalog.data.schema.HCatFieldSchema;
import org.apache.hive.hcatalog.data.schema.HCatFieldSchema.Type;

/**
 * @author rishrohi
 *
 */
public class HCatUpdateExample {
	public static void main(String[] args) throws HCatException {

		String db = HCatConstants.db;
		String tableOne = HCatConstants.tableOne;

		// create HCatClient
		HCatClient client = HCatClient.create(new Configuration(HCatConstants
				.getHiveConf()));

		// update table - this will remove old columns
		ArrayList<HCatFieldSchema> cols = new ArrayList<HCatFieldSchema>();
		cols.add(new HCatFieldSchema("id", Type.INT, "id comment"));
		cols.add(new HCatFieldSchema("value", Type.STRING, "value comment"));
		cols.add(new HCatFieldSchema("addr", Type.STRING, "addr comment"));
		client.updateTableSchema(db, tableOne, cols);

		System.out.println("Updated Hive table");
		client.close();
	}

}
