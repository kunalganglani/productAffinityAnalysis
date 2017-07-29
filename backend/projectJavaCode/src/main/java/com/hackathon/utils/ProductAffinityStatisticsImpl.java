package com.hackathon.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductAffinityStatisticsImpl implements ProductAffinityStatisticsIntf {
	
	
	@Autowired
	FileReader reader;
	static int totalTransactions=0;
	ExecutorService es1 = Executors.newFixedThreadPool(3);
	Set<String> productSet1 = new HashSet<>();
	Map<String, Integer> lMap1 = null;
	
	Map<String, List<String>> finalMap;
	
	public Map<String, List<String>> getFinalMap() {
		return finalMap;
	}

	@PostConstruct
	public void afterRead() throws IOException {
		reader.start();
		try {
			reader.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finalMap = reader.readInvoices();
	}
	
	@Override
	public Map<String, Double> productSupportCount() throws Exception{
		Map<String, List<String>> map1 = finalMap;
		Map<String, Integer> map2 = new HashMap<>();
		for(Map.Entry<String, List<String>> e1 : map1.entrySet()){
			//String key = e1.getKey();
			List<String> value = e1.getValue();//productDescription": "AW-Shoe shine sponge 50:MIXED (2 CLR):NA
			totalTransactions++;
			for(String product : value){
				String[] strArr1 = product.split(":");
				String prodName  = strArr1[0];
				map2.put(prodName, (map2.containsKey(prodName) ? map2.get(prodName)+1 : 1));
				productSet1.add(prodName);
			}
		}
		lMap1 = sortMapByValues(map2);
		Map<String, Double> lMap2 = calculateProductSuportPercent(lMap1);
		System.out.println("-----productSupportCount------");
		System.out.println(lMap2);
		return lMap2;
	}
	
	private Map<String, Double> calculateProductSuportPercent(Map<String, Integer> lMap1) {
		double percent = 0.0;
		Map<String, Double> lMap2 = new LinkedHashMap<>();
		for(Map.Entry<String, Integer> e1 : lMap1.entrySet()){
			Integer i1 = e1.getValue();
			percent = (double)(100*i1.doubleValue()/totalTransactions);
			lMap2.put(e1.getKey(), percent);
		}
		return sortMapByValues(lMap2);
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValues(Map<K, V> map1) {
		List<Map.Entry<K, V>> list1 = new LinkedList<>(map1.entrySet());
		
		Collections.sort(list1, new Comparator<Map.Entry<K, V>>(){
			@Override
			public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2){
				return e2.getValue().compareTo(e1.getValue());
			}
		});
		
		Map<K, V> map5 = new LinkedHashMap<>();
		for(Map.Entry<K, V> m1:list1){
			map5.put(m1.getKey(), m1.getValue());
		}
		return map5;
	}
	
	public Map<String, Double> jointProductSupport() throws Exception {
		Map<String, List<String>> map1 = finalMap;
		totalTransactions = 0;
		List<String> listOfProductsPerInvoice = new ArrayList<>();
		Map<String, List<String>> map2 = new HashMap<>();
		for(Map.Entry<String, List<String>> e1 : map1.entrySet()){
			String key = e1.getKey();
			List<String> value = e1.getValue();//productDescription": "AW-Shoe shine sponge 50:MIXED (2 CLR):NA
			totalTransactions++;
			for(String product : value){
				String[] strArr1 = product.split(":");
				String prodName  = strArr1[0];
				listOfProductsPerInvoice.add(prodName);
			}
			map2.put(key, listOfProductsPerInvoice);
		}
		return getJointProductCount(map2);
	}
	
	private Map<String, Double> getJointProductCount(Map<String, List<String>> map2) {
		Map<String, List<String>> map3 = map2;
		Map<String, Integer> lMap1 = new LinkedHashMap<>();
		
		if(!map3.isEmpty()){
			Set<Map.Entry<String, List<String>>> set1 = map3.entrySet();
			Iterator<Map.Entry<String, List<String>>> itr1 = set1.iterator();
			List<List<String>> list1 = new ArrayList<>();
			StringBuffer sb1 = new StringBuffer();

			if(itr1.hasNext()){
				Map.Entry<String, List<String>> e1 = itr1.next();
				list1.add(e1.getValue());
				//map3.remove(e1.getKey(), e1.getValue());

				for(int i=0; i<list1.size(); i++){
					sb1.append(list1.get(i)+":");
				}
				lMap1.put(sb1.toString(), 1);
			}

			while(itr1.hasNext()){
				Map.Entry<String, List<String>> e1 = itr1.next();
				if(list1.containsAll(e1.getValue())){
					lMap1.put(sb1.toString(), lMap1.get(sb1.toString())+1);
				}
			}
		}
		Map<String, Double> lMap2 = calculateJointProductSuportPercent(lMap1);
		return lMap2;
	}
	
	private Map<String, Double> calculateJointProductSuportPercent(Map<String, Integer> lMap1) {
		double percent = 0.0;
		Map<String, Double> lMap2 = new LinkedHashMap<>();
		for(Map.Entry<String, Integer> e1 : lMap1.entrySet()){
			Integer i1 = e1.getValue();
			percent = (double)(100*i1.doubleValue()/totalTransactions);
			lMap2.put(e1.getKey(), percent);
		}
		return sortMapByValues(lMap2);
	}

	@Override
	public Map<String, Double> prepareBaseTargetConfidence() throws Exception {
		Map<String, List<String>> map3 = finalMap;
		Map<String, Integer> lMap1 = new LinkedHashMap<>();
		
		if(!map3.isEmpty()){
			Set<Map.Entry<String, List<String>>> set1 = map3.entrySet();
			Iterator<Map.Entry<String, List<String>>> itr1 = set1.iterator();
			List<List<String>> list1 = new ArrayList<>();
			StringBuffer sb1 = new StringBuffer();

			if(itr1.hasNext()){
				Map.Entry<String, List<String>> e1 = itr1.next();
				list1.add(e1.getValue());
				//map3.remove(e1.getKey(), e1.getValue());

				for(int i=0; i<list1.size(); i++){
					sb1.append(list1.get(i)+":");
				}
				lMap1.put(sb1.toString(), 1);
			}

			while(itr1.hasNext()){
				Map.Entry<String, List<String>> e1 = itr1.next();
				if(list1.containsAll(e1.getValue())){
					lMap1.put(sb1.toString(), lMap1.get(sb1.toString())+1);
				}
			}
		}
		Map<String, Double> lMap2 = calculateConfidenceBaseTargetPercent(lMap1);
		System.out.println("-----prepareBaseTargetConfidence------");
		System.out.println(lMap2);
		return lMap2;
	}
	
	private Map<String, Double> calculateConfidenceBaseTargetPercent(Map<String, Integer> lMap3) throws Exception {
		double percent = 0.0;
		Map<String, Double> lMap2 = new LinkedHashMap<>();
		productSupportCount();
		for(Map.Entry<String, Integer> e1 : lMap3.entrySet()){
			Integer i1 = e1.getValue();
			Set<Map.Entry<String, Integer>> set2 = lMap1.entrySet();
			percent = (double)(100*i1.doubleValue()/(set2.iterator().next().getValue()));
			lMap2.put(e1.getKey(), percent);
		}
		return sortMapByValues(lMap2);
	}

	@Override
	public Map<String, Double> prepareTargetBaseConfidence() {
		Map<String, List<String>> map3 = finalMap;
		Map<String, Integer> lMap1 = new LinkedHashMap<>();
		
		if(!map3.isEmpty()){
			Set<Map.Entry<String, List<String>>> set1 = map3.entrySet();
			Iterator<Map.Entry<String, List<String>>> itr1 = set1.iterator();
			List<List<String>> list1 = new ArrayList<>();
			StringBuffer sb1 = new StringBuffer();

			if(itr1.hasNext()){
				Map.Entry<String, List<String>> e1 = itr1.next();
				list1.add(e1.getValue());
				//map3.remove(e1.getKey(), e1.getValue());

				for(int i=0; i<list1.size(); i++){
					sb1.append(list1.get(i)+":");
				}
				lMap1.put(sb1.toString(), 1);
			}

			while(itr1.hasNext()){
				Map.Entry<String, List<String>> e1 = itr1.next();
				if(list1.containsAll(e1.getValue())){
					lMap1.put(sb1.toString(), lMap1.get(sb1.toString())+1);
				}
			}
		}
		Map<String, Double> lMap2 = calculateConfidenceTargetBasePercent(lMap1);
		System.out.println("-----prepareTargetBaseConfidence------");
		System.out.println(lMap2);
		return lMap2;
	}

	private Map<String, Double> calculateConfidenceTargetBasePercent(Map<String, Integer> lMap3) {
		double percent = 0.0;
		Map<String, Double> lMap2 = new LinkedHashMap<>();
		for(Map.Entry<String, Integer> e1 : lMap3.entrySet()){
			Integer i1 = e1.getValue();
			Set<Map.Entry<String, Integer>> set2 = lMap1.entrySet();
			percent = (double)(100*i1.doubleValue()/(set2.iterator().next().getValue()));
			lMap2.put(e1.getKey(), percent);
		}
		System.out.println("-----calculateConfidenceTargetBasePercent------");
		System.out.println(lMap2);
		return sortMapByValues(lMap2);
	}

	@Override
	public Map<String, Double> prepareBaseTargetLift() throws Exception {
		Map<String, Double>  map1 = jointProductSupport();
		Map<String, Double> lMap2 = new LinkedHashMap<>();
		for(Map.Entry<String, Double> e1 : map1.entrySet()){
			Double i1 = e1.getValue();
			Set<Map.Entry<String, Integer>> set2 = lMap1.entrySet();
			double percent = (double)(totalTransactions*i1.doubleValue()/(set2.iterator().next().getValue()));
			lMap2.put(e1.getKey(), percent);
		}
		System.out.println("-----prepareBaseTargetLift------");
		System.out.println(lMap2);
		return sortMapByValues(lMap2);
	}

	@Override
	public Map<String, Double> prepareBaseTargetAffinity() throws Exception {
		Map<String, Double>  map1 = jointProductSupport();
		Map<String, Double> lMap2 = new LinkedHashMap<>();
		productSupportCount();
		for(Map.Entry<String, Double> e1 : map1.entrySet()){
			Double i1 = e1.getValue();
			Set<Map.Entry<String, Integer>> set2 = lMap1.entrySet();
			double percent = (double)(100*i1.doubleValue()/(set2.iterator().next().getValue()));
			lMap2.put(e1.getKey(), percent);
		}
		System.out.println("-----prepareBaseTargetAffinity------");
		System.out.println(lMap2);
		return sortMapByValues(lMap2);
	}
	
	public static void main(String[] args) {
		
	}
}

