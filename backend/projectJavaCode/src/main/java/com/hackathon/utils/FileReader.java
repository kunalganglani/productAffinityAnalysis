package com.hackathon.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.hackathon.ApplicationContextHolder;
import com.hackathon.models.Product;

@Component
@PropertySource(value = {"classpath:application.properties"})
public class FileReader extends Thread {

	@Value("${fileName: affinity_10K.txt}")
	String fileName;

	File file;

	int counter = 0;
	
	List<Product> list = new ArrayList<Product>();
	
	Map<String, List<String>> map = new HashMap<>();

	@PostConstruct
	public void afterConstruct() throws IOException {
		Resource resource = ApplicationContextHolder.getContext().getResource("classpath:" + fileName);
		file = resource.getFile();
	}

	@Override
	public void run() {
		clear();
		try {
			List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			lines.forEach(s -> 
			{
				if(counter > 0){
					String[] data = s.split("\\|");
					List<String> subList = Arrays.asList(data);
					/*if(list.contains(new Product(subList.get(2)))) {
						Product product = list.get(list.indexOf(new Product(subList.get(2))));
						product.setCount(product.getCount() + 1);
					} else {
						list.add(new Product(subList.get(2), 1));
					}*/
					
					
					if(map.containsKey(subList.get(subList.size() - 1))) {
						map.get(subList.get(subList.size() - 1)).add(subList.get(2));
					} else {
						List<String> tempList = new ArrayList<String>();
						tempList.add(subList.get(2));
						map.put(subList.get(subList.size() - 1), tempList);
					}
				}
				counter++;
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		counter = 0;
	}

	public List<Product> readProducts() throws IOException {
		return list;
	}
	
	public Map<String, List<String>> readInvoices() throws IOException {
		return map;
	}
	
	public void clear() {
		list.clear();
		map.clear();
	}

}
