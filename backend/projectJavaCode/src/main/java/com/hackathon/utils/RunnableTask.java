package com.hackathon.utils;

import java.io.IOException;
import java.util.concurrent.RecursiveTask;

public abstract class RunnableTask<T> extends RecursiveTask<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected T compute() {
		try {
			return doInBackground();
		} catch (IOException e) {
			return null;
		}
	}
	
	public abstract T doInBackground() throws IOException;
}
