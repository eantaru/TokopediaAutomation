package com.gmail.eantaru.cucumber.storage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.gmail.eantaru.cucumber.storage.model.TokopediaModel;
import com.gmail.eantaru.cucumber.util.Config;

public class Storage {
	
	private final int max = 100; // max limit
	
	private static Storage storage;
	
	public static Storage instanceStorage() {
		if(storage == null) {
			storage = new Storage();
		}
		
		return storage;
	}
	
	private Storage() {}
	
	private List<TokopediaModel> models = new ArrayList<>();
	
	public void store(TokopediaModel model) {
		models.add(model);
	}
	
	@SuppressWarnings("resource")
	public void storeToCSV() throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(Config.CSV_FILENAME));
		
		int num = 1;
		
        CSVPrinter printer = new CSVPrinter(writer, 
        		CSVFormat.DEFAULT.withHeader("#", "Name of Product", "Description", "Image Link", 
        				"Price", "Rating", "Merchant Name"));
        
        for (TokopediaModel model : models) {
			printer.printRecord(num ++,
					model.getProduct(),
					model.getDesc(),
					model.getImage(),
					model.getPrice(),
					model.getRating(),
					model.getMerchant());
		}
        
        printer.flush();
	}
	
	public void print() {
		System.out.println("size: " + models.size());
		System.out.println(models.toString());
	}
	
	public boolean isReachLimit() {
		return models.size() >= max;
	}
}
