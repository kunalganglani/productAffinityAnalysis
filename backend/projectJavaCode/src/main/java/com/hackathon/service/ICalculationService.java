package com.hackathon.service;

import java.util.List;
import java.util.Map;

import com.hackathon.models.Product;
import com.hackathon.models.ProductInvoice;

public interface ICalculationService {

	public List<Product> convertGroupsIntoProductCount(Map<String, List<ProductInvoice>> map);
}
