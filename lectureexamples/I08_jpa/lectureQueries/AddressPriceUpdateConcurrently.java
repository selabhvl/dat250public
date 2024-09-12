package no.hvl.dat250.jpa.tutorial.lectureQueries;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.Persistence;
import jakarta.persistence.RollbackException;

public class AddressPriceUpdateConcurrently {
	private static final String PERSISTENCE_UNIT_NAME = "jpa-tutorial";
	private UpdateProcess updateProcess;
	public AddressPriceUpdateConcurrently() {
		super();
		this.updateProcess = new UpdateProcess();
	}
	public void doConcurrentUpdate() {
// ======= 1. Print the initial value of the address in the database ===========
		EntityManager em =  Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
		Address a = em.find(Address.class, 1L);
		System.out.println("The old value of the landprice at " + a.getStreet() + " " + a.getNumber() + " is " + a.getLandPrice() + " NOK.");
		
// ========= 2. Simulate two parallel processes, which both access the object with id 1L ==		
		Thread t1 = new Thread(()->updateProcess.updateAddressBy(1L, 100, PERSISTENCE_UNIT_NAME));
		Thread t2 = new Thread(()->updateProcess.updateAddressBy(1L, 200, PERSISTENCE_UNIT_NAME));
		t1.start();
		t2.start();
		
// 3. ====Wait until both processes have terminated =======================================		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {}

// 4. ========== Re-read the value from the database ======================================	
		em.refresh(a);
		System.out.println("The new value of the landprice at " + a.getStreet() + " " + a.getNumber() + " is " + a.getLandPrice() + " NOK.");
	}
}
