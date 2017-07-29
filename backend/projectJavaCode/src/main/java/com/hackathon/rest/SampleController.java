package com.hackathon.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.models.Product;
import com.hackathon.service.IInvoiceService;
import com.hackathon.utils.ProductAffinityStatisticsImpl;
import com.hackathon.utils.ProductWriter;
import com.hackathon.utils.ReadingProductTask;
import com.hackathon.utils.ReadingTask;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SampleController {
	
	ForkJoinPool pool = new ForkJoinPool(1);
	
	@Autowired
	ReadingTask task;
	
	@Autowired
	ReadingProductTask productsTask;
	
	@Autowired
	IInvoiceService invoiceService;
	
	@Autowired
	ProductAffinityStatisticsImpl productInfo;
	
	@RequestMapping(value = "/group")
	public List<Map<String, Double>> calculateInvoiceGroups() throws Exception {
		List<Map<String, Double>> list = new ArrayList<Map<String, Double>>();
		list.add(productInfo.productSupportCount());
		list.add(productInfo.prepareBaseTargetConfidence());
		list.add(productInfo.prepareTargetBaseConfidence());
		list.add(productInfo.prepareBaseTargetLift());
		list.add(productInfo.prepareBaseTargetAffinity());
		return list;
	}
	
	@RequestMapping(value = "/affinity")
	public List<List<String>> affinityChange() throws Exception {
		Map<String, Double> affinity = productInfo.productSupportCount();
		List<List<String>> list = new ArrayList<>();
		for (String key: affinity.keySet()) {
			List<String> subList = new ArrayList<>();
			subList.add(key);
			subList.add(String.valueOf(affinity.get(key)));
			list.add(subList);
		}
		return list;
	}
	
	@RequestMapping(value = "/products")
	public List<Product> getProducts() throws IOException {
		List<Product> pros = invoiceService.listProducts();
		ProductWriter writer = new ProductWriter(pros);
		writer.writeIntoFile();
		return pros;
	}

}
