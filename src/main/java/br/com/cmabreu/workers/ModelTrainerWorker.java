package br.com.cmabreu.workers;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelTrainerWorker implements Runnable, IWorker {
	private String name;
	private Logger logger = LoggerFactory.getLogger( ModelTrainerWorker.class );	
	private WorkerManager wm;
	private final String dataDir = "/home/solon";
	private final String scriptDir = "/opt";
	
	public ModelTrainerWorker() {
		this.name = UUID.randomUUID().toString();
	}
	
	@Override
	public void run() {
		this.execute();
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	private void execute() {
		String modelDir = dataDir + "/" + this.name;
		new File( modelDir ).mkdirs();
		
		try {
			String[] environments = null;
			File workdir = new File( scriptDir );
			
			logger.info("Starting training job " + this.name );
			
			System.out.println("SALVE OS INTENTS !!");
			/*
			{"intents": [
			        {"tag": "saudacao",
			         "patterns": ["Oi", "Ola", "Oi tudo bem:", "Tem alguem ai?", "Pode me ajudar"],
			         "responses": ["Oi tudo bem e voce ?", "Ola, como posso te ajudar ?", "Oi, como podemos te ajudar?"],
			         "context_set": ""
			        }
			   ]
			}  
			*/			
			
			String[] command = { 
				"./train.sh",
				this.name,
				modelDir
			};
		    Process process = Runtime.getRuntime().exec(command, environments, workdir); 			
		    
		    String line;
		    BufferedReader input = new BufferedReader(new InputStreamReader( process.getInputStream() ) );
		    while ((line = input.readLine()) != null) {
		      logger.info(line);
		    }
		    input.close();	
		    BufferedReader error = new BufferedReader(new InputStreamReader( process.getErrorStream() ) );
		    while((line = error.readLine()) != null){
		        logger.error(line);
		    }
		    error.close();
		    		    
		    process.waitFor();
		    wm.finishCallBack( this );
		    
		} catch ( Exception e ) {
			e.printStackTrace();
		}			
		
	}

}
