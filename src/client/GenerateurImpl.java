package client;

import service.GenerateurObservateurAsync;
import strategy.AlgoDiffusion;

public class GenerateurImpl implements Generateur {
	private AlgoDiffusion algo;
	private String name;
	
	public GenerateurImpl(AlgoDiffusion alg, String n) {
		algo = alg;
		name=n;
	}
	
	public void attach(GenerateurObservateurAsync o) {
		algo.attach(o);
	}
	public void detach(GenerateurObservateurAsync o) {
		//todo
	}
	public int getValue(){
		return algo.getValue();
	}
	
	public void tick() {
		algo.execute(this);
		
	}
}
