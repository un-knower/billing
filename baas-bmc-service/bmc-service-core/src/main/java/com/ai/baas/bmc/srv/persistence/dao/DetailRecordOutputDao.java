package com.ai.baas.bmc.srv.persistence.dao;

import java.util.List;
import java.util.Map;

public interface DetailRecordOutputDao {

	int createTable(String tableName, List<String> columnNames);
	
	int insertDetailRecord(String table,String key,List<String> columnNames,Map<String,String> data);
	
	
}
