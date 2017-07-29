package com.hackathon.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.hackathon.models.Product;

public interface IInvoiceService {
	
	public Map<String, List<String>> groupRespectToInvoice() throws IOException;
	
	public List<Product> listProducts() throws IOException;
	
}
