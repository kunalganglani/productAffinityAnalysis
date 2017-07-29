package com.hackathon.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hackathon.models.Product;
import com.hackathon.models.ProductInvoice;
import com.hackathon.service.ICalculationService;

@Service
public class CalculationServiceImpl implements ICalculationService {

	@Override
	public List<Product> convertGroupsIntoProductCount(Map<String, List<ProductInvoice>> map) {
		List<Product> products = new ArrayList<>();
		for (String key : map.keySet()) {
			
		}
		return null;
	}

}
