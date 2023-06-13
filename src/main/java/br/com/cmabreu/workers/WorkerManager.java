package br.com.cmabreu.workers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerManager {
	private Map<String,IWorker> workers = new HashMap<String,IWorker>();
	private Logger logger = LoggerFactory.getLogger( WorkerManager.class );	
	
	public void startWorker() {
		IWorker worker = new ModelTrainerWorker();
		new Thread( (Runnable)worker ).start();
		workers.put( worker.getName(), worker);
	}

	public void finishCallBack(ModelTrainerWorker modelTrainerWorker) {
		logger.info( modelTrainerWorker.getName() + " finished work.");
		
	}
	
}
