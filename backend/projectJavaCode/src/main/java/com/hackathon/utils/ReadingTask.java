package com.hackathon.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hackathon.service.IInvoiceService;

@Component
public class ReadingTask extends RecursiveTask<Map<String, List<String>>> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	IInvoiceService invoiceService;

	@Override
	public Map<String, List<String>> compute() {
		try {
			return invoiceService.groupRespectToInvoice();
		} catch (IOException e) {
			return null;
		}
	}
}
