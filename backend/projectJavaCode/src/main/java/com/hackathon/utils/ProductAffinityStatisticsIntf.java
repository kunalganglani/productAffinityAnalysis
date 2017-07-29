package com.hackathon.utils;

import java.util.Map;

public interface ProductAffinityStatisticsIntf {
	public Map<String, Double> productSupportCount() throws Exception;
	public Map<String, Double> prepareBaseTargetLift() throws Exception;
	public Map<String, Double> prepareBaseTargetAffinity() throws Exception;
	public Map<String, Double> prepareTargetBaseConfidence();
	Map<String, Double> prepareBaseTargetConfidence() throws Exception;
}
