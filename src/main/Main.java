package main;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import client.Afficheur;
import client.Generateur;
import client.GenerateurImpl;
import client.ObservateurGenerateur;
import proxy.Canal;
import strategy.AlgoDiffusion;
import strategy.AlgoDiffusionAtomique;

public class Main {

	public static  ScheduledExecutorService scheduler =  Executors.newScheduledThreadPool(7);
	
	 public static void main (String[] args){
		 AlgoDiffusion atom= new AlgoDiffusionAtomique();
		 Generateur gen1 = new GenerateurImpl(atom,"gen1");
		 ObservateurGenerateur obs1=  new Afficheur("obs1");
		 Canal c1 =new Canal(obs1,gen1);
		 gen1.attach(c1);
	
	
		 ObservateurGenerateur obs2=  new Afficheur("obs2");
		 Canal c2 =new Canal(obs2,gen1);
		 gen1.attach(c2);

		 
	
		 ObservateurGenerateur obs3=  new Afficheur("obs3");
		 Canal c3 =new Canal(obs3,gen1);
		 gen1.attach(c3);

		 for(int i=0; i<20; i++) {
		 gen1.tick();
	
		 }
	 }
}
