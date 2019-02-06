package proxy;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;

import client.Generateur;
import client.ObservateurGenerateur;
import main.Main;
import service.GenerateurAsync;
import service.GenerateurObservateurAsync;

public class Canal implements GenerateurAsync, GenerateurObservateurAsync {

	// Scheduler a sortir de la classe, un seul pour tous les canaux
	
	private ObservateurGenerateur observateur;
	private Generateur generateur;

	
	//Future<Integer>
	public ScheduledFuture<Void> update(Generateur g){
		Canal thisObject =this;
		System.out.println("update dans canal");
		ScheduledFuture<Void> result =Main.scheduler.schedule(new Callable<Void>() {
			public Void call() {
				observateur.update(thisObject);
				return null;
				
			}
		},getRandom(),MILLISECONDS);
		return result;
		
	}
	public void attach(ObservateurGenerateur o) {
		observateur = o;
	}
	public void detach(ObservateurGenerateur o) {
		
	}
	
	public void attach(Generateur g) {
		generateur = g;
	}
	public void detach(Generateur g) {
		
	}
	
	
	public ScheduledFuture <Integer> getValue(){
		ScheduledFuture<Integer> result = Main.scheduler.schedule(new Callable<Integer>() {
			public Integer call() {
				return generateur.getValue();
			}
		},20000,MILLISECONDS);
		
		return result;
	}
	
	public int getRandom() {
		return (int)Math.random() *(500);
	}
}
