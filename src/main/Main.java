package main;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import client.Afficheur;
import client.Generateur;
import client.GenerateurImpl;
import client.ObservateurGenerateur;
import proxy.Canal;

public class Main {

	public static  ScheduledExecutorService scheduler =  Executors.newScheduledThreadPool(5);
	
	 public static void main (String[] args){
		 Generateur gen1 = new GenerateurImpl();
		 
		 ObservateurGenerateur obs1=  new Afficheur();
		 
		 Canal c =new Canal();
		 gen1.attach(c);
		 c.attach(gen1);
		 c.attach(obs1);
		 
		 gen1.tick();
	 }
}
