package strategy;

import client.Generateur;
import service.GenerateurObservateurAsync;

public interface AlgoDiffusion {

	public void execute(Generateur gen);
	public void attach(GenerateurObservateurAsync obs);
	
	public int getValue();
}
