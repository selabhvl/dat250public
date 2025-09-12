package no.hvl.dat250.jpa.tutorial.lectureQueries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import jakarta.persistence.RollbackException;

public class UpdateProcess {
	public void updateAddressBy(Long addressId, int difference, String persistenceUnitName) {
		long id = Thread.currentThread().getId();
		System.out.println("Thread " + id + " gestartet!");
// Create EntityManager and Transaction --> locally in this Thread <--:	
		EntityManagerFactory emfLocal = Persistence.createEntityManagerFactory(persistenceUnitName); 
		EntityManager emLocal = emfLocal.createEntityManager();
		emLocal.getTransaction().begin();
// Concurrency Experiments
		Address address = emLocal.find(Address.class, addressId, LockModeType.NONE);    				// No Lock	 --> Lost update!!	
//		Address address = emLocal.find(Address.class, addressId, LockModeType.PESSIMISTIC_WRITE);  		// Write Lock with wait
//      NOW ADD @Version in Address and create the tables again		
//		Address address = emLocal.find(Address.class,addressId,LockModeType.OPTIMISTIC_FORCE_INCREMENT); // Lock with possible concurrent access but OptimisticLockException 

// ===== Simulating a short delay ======================		
		try {Thread.sleep(50);
		} catch (InterruptedException e) {System.out.println("Interrupt at waiting");}
		
// ======== Update... ====================================
		int oldPrice = address.getLandPrice();
		address.setLandPrice(oldPrice + difference);
		System.out.println("Updating by " + difference + " NOK by Thread "+ id);
		emLocal.merge(address);
// ============ ... and commit ========================		
		try {
			emLocal.getTransaction().commit();
		}catch(RollbackException rbe) {
			System.out.println(rbe.getMessage());
			System.out.println(rbe.getCause().toString());
		}
		emLocal.close();
		emfLocal.close();
	}
}
