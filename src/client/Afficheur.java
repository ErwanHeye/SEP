package client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;

import service.GenerateurAsync;

public class Afficheur implements ObservateurGenerateur {

	// ou  Future<Integer> ?
	public void update(GenerateurAsync g) {
		ScheduledFuture<Integer> valueFuture= g.getValue();
		int result;
		try {
			result = valueFuture.get();
			System.out.println(result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
