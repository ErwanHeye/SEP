package strategy;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;

import client.Generateur;
import service.GenerateurObservateurAsync;

public class AlgoDiffusionAtomique implements AlgoDiffusion {

	private int value;
	private int nbUpdate;
	private Set<GenerateurObservateurAsync> m_observateurs;
	
	public AlgoDiffusionAtomique() {
		m_observateurs = new HashSet<GenerateurObservateurAsync>();
		nbUpdate=0;
		
	}
	public void execute(Generateur gen) {
		value+=1;
		Set<ScheduledFuture<Void>> tempFuture = new HashSet<ScheduledFuture<Void>>();
		for(GenerateurObservateurAsync obs : m_observateurs ) {
			ScheduledFuture<Void>result =obs.update(gen);
			// première version, on update et on attend la réponse
			try {
				result.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			//tempFuture.add(result);
		}
		
		/*// deuxieme version, on a update tous les observateurs, on attend leurs reponses
		for(ScheduledFuture<Void> future : tempFuture) {
			try {
				future.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		*/
	}
	
	public int getValue() {
		nbUpdate++;
		return value;
	}
	
	public void attach(GenerateurObservateurAsync obs) {
		m_observateurs.add(obs);
	}
	
	
}
