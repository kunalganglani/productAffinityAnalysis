package com.hackathon.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.hackathon.models.Product;

public class ProductWriter extends FileWriter<Product> {
	
	List<Product> products;
	
	public ProductWriter(List<Product> products) {
		this.products = products;
	}

	@Override
	public String fileName() {
		return "products.txt";
	}

	@Override
	public boolean writeLogic(File file) throws IOException {
		java.io.FileWriter fw = new java.io.FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		try {
		Iterator<Product> iterator = products.iterator();
		while (iterator.hasNext()) {
			Product pro = iterator.next();
			StringBuilder builder = new StringBuilder();
			builder.append(pro.getProductName());
			builder.append("|");
			builder.append(pro.getCount());
			bw.write(builder.toString());
			bw.newLine();
		}
		} catch(Exception ex) {
			return false;
		} finally {
			if (bw != null)
				bw.close();

			if (fw != null)
				fw.close();
		}
		return true;
	}

}
