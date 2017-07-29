package com.hackathon.utils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hackathon.models.Product;
import com.hackathon.service.IInvoiceService;

@Component
public class ReadingProductTask extends RecursiveTask<List<Product>> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	IInvoiceService invoiceService;

	@Override
	public List<Product> compute() {
		try {
			return invoiceService.listProducts();
		} catch (IOException e) {
			return null;
		}
	}

}
