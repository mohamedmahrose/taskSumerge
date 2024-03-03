package com.sumerge.automation.task.dataModel;

import java.util.ArrayList;


public interface TestDataStrategy {

	ArrayList<ArrayList<Object>> loadTestData(String connectionProperties);
}
