package client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;

import service.GenerateurObservateurAsync;

public class GenerateurImpl implements Generateur {
	private int value;
	private GenerateurObservateurAsync observateur;
	
	public void attach(GenerateurObservateurAsync o) {
		observateur=o;
	}
	public void detach(GenerateurObservateurAsync o) {
		observateur=null;
	}
	public int getValue(){
		return value;
	}
	
	public void tick() {
		value +=1;
		ScheduledFuture<Void>result =observateur.update(this);
		try {
			result.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
