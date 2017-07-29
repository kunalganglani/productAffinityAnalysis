package com.hackathon.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.models.Product;
import com.hackathon.service.IInvoiceService;
import com.hackathon.utils.FileReader;

@Service
public class InvoiceServiceImpl implements IInvoiceService {
	
	@Autowired
	FileReader reader;
	
	@Override
	public synchronized Map<String, List<String>> groupRespectToInvoice() throws IOException {
		reader.start();
		try {
			reader.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return reader.readInvoices();
	}
	
	@Override
	public synchronized List<Product> listProducts() throws IOException {
		reader.start();
		try {
			reader.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return reader.readProducts();
	}
}
