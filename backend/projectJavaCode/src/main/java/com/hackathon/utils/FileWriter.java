package com.hackathon.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class FileWriter<T> {

	public void writeIntoFile() throws IOException {
		File file = new File("D:/output/" + fileName());
		if (file.exists()) file.delete();
		file.createNewFile();
		writeLogic(file);
	}


	public abstract String fileName();

	public abstract boolean writeLogic(File file) throws FileNotFoundException, IOException;
}
