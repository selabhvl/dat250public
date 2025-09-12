package no.hvl.dat250.jpa.tutorial.creditcards.driver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import no.hvl.dat250.jpa.tutorial.lectureBasic.*;

public class LectureBasicDemos {
	private static final String PERSISTENCE_UNIT_NAME = "jpa-tutorial";
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	@Before
	public void setUp() throws Exception {
		this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		this.entityManager = entityManagerFactory.createEntityManager();
	}
// ========== D0 ================= 	
	@Test
	public void cleanUp() {
		System.out.println("Called the cleaning test case:");
		this.entityManager.getTransaction().begin();
		this.entityManager.createNativeQuery("DROP DATABASE Person");  
		this.entityManager.getTransaction().commit();
		System.out.println("Successful!");
	}	
// ========== D1 ================= 	
	@Test
	public void testManagedTypes() {
		System.out.println("Managed Types:");
		this.entityManager.getMetamodel().getManagedTypes().forEach((t)->System.out.println(t.getJavaType().getName()));
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testPersonOnlyAttributes() {
//		this.entityManager.getTransaction().begin();
//		Person p = new Person("Stünkel", "Patrick");
//		this.entityManager.persist(p);
//		this.entityManager.getTransaction().commit();
//		
//// ========== Read data ================		
//		List<Person> r 	= this.entityManager.createQuery("select p from Person_Basic p", Person.class).getResultList();
////		assertEquals(1, r.size());
//		printContents(r, Person.class);
//		r = this.entityManager.createNativeQuery("select * from Person_Basic where surName = 'Hansen'", Person.class).getResultList();
//		assertEquals(0, r.size());
// Show how to display the Hibernate SQL-statements (persistence.xml) 		
// Variation: Switch <property name="hibernate.hbm2ddl.auto" value="create"/> to "none" and carry out tests twice		
	}
// ================== D2 ================== 	
	@Test
	public void testPrimitiveCollections() {		
		this.entityManager.getTransaction().begin();
		Person p = new Person("Abel", "Nils", "Henrik");
		this.entityManager.persist(p);
		this.entityManager.getTransaction().commit();
			
		@SuppressWarnings("unchecked")
// ============= Read stored Persons ========================= 		
		List<Person> resultList = 
				this.entityManager
					.createNativeQuery("SELECT * FROM Person_Basic", Person.class)
					.getResultList();
		assertTrue(resultList.size()>0);
		this.printContents(resultList, Person.class);
// ============= Inspect contents of firstnames table ========		
		List<String> firstNames = 
				this.entityManager
				.createNativeQuery("SELECT contents FROM Firstnames", String.class)
				.getResultList();
		firstNames.forEach(t->System.out.println(t));
		
		System.out.println("Btw: Nils Henrik Abel was a famous Norwegian Mathematician (1802-1829)");
		// Show SQL	
		// Variation: Call the @Column in the additional table "value" and guess, why an error occurs
	
		// ---> Clean up after D2!!!!!!!!!
	}
	
// ================== D3 (Optional) ======== 		
	@Test
	public void testEnums() {		
//		Person p = new Person("Ole", "Peterson");
//		this.entityManager.getTransaction().begin();
//		this.entityManager.persist(p); 
//		this.entityManager.getTransaction().commit();
//
//		List<Person> resultList = 
//				this.entityManager
//					.createNativeQuery("SELECT * FROM Person_BASIC", Person.class)
//					.getResultList();
//		assertTrue(resultList.size()==1);
//		this.printContents(resultList, Person.class);
//		List<Integer> roleIds = 
//				this.entityManager
//					.createNativeQuery("SELECT role FROM Person_BASIC", Integer.class)
//					.getResultList();
//		assertTrue(roleIds.size()==1);
//		roleIds.forEach(r->System.out.println("RoleId=" + r));
	}
	
// ================== D4 ===================================
// --> Add Address to persistence unit! 	
	@Test
	public void testUniTo1Phase1() {	
//		this.entityManager.getTransaction().begin();
//		Address a = new Address("Christies Gate", 1);
//		Person p = new Person("Carlsen", "Magnus", a);  // Magnus Carlsen was the former chess world champion
//		//this.entityManager.persist(a);  // (*)
//		this.entityManager.persist(p);
//		this.entityManager.getTransaction().commit();
//		// ========================
//		List<Address> addressResultList = 
//				this.entityManager
//					.createQuery("SELECT a FROM Address_Basic a", Address.class)
//					.getResultList();
//		assertTrue(addressResultList.size()==1); // (*)
//		List<Person> personsResultList = 
//				this.entityManager
//					.createQuery("SELECT p FROM Person_Basic p", Person.class)
//					.getResultList();
//		assertTrue(personsResultList.size()==1);
//
//		this.printContents(personsResultList, Person.class);
// Show SQL		
// What happens, if we remove statement (*)	--> Interpret the error message	
// Repair with @ManyToOne(cascade = CascadeType.PERSIST) at attribute address in class Person		
	}

	
// Switch to none and read data from DB	
	@Test
	public void testUniTo1Phase2() {	
//		List<Person> personsResultList = 
//				this.entityManager
//					.createQuery("SELECT p FROM Person_Basic p", Person.class)
//					.getResultList();
//		assertTrue(personsResultList.size()==1);
//
//		this.printContents(personsResultList, Person.class);
	}

	@Test
	public void testUniTo1_Addendum() {	
// === Creates a second person living at the same address ======
//		this.entityManager.getTransaction().begin();
//		List<Address> addressResultList = 
//				this.entityManager
//					.createQuery("SELECT a FROM Address_Basic a", Address.class)
//					.getResultList();
//		assertTrue(!addressResultList.isEmpty());
//		
//		Person p = new Person("Ding", "Liren", addressResultList.get(0));
//		this.entityManager.persist(p);
//		this.entityManager.getTransaction().commit();
//
//		List<Person> personsResultList = 
//				this.entityManager
//					.createQuery("SELECT p FROM Person_Basic p", Person.class)
//					.getResultList();
//		assertTrue(personsResultList.size()>=2);
//
//		this.printContents(personsResultList, Person.class);
	}
// ================= D5 ==========================
	@Test
	public void testUniToMany() {	
//		this.entityManager.getTransaction().begin();
//		Person p = new Person("Magnus","Carlson");
//		Address a1 = new Address("Inndalsveien", 28);
//		this.entityManager.persist(a1);
//		p.addAddress(a1);
//		Address a2 = new Address("Christies Gate", 1);
//		this.entityManager.persist(a2);
//		p.addAddress(a2);
//		this.entityManager.persist(p);
//		this.entityManager.getTransaction().commit();
//				
//		@SuppressWarnings("unchecked")
//		List<Person> personsResultList = 
//				this.entityManager
//					.createQuery("SELECT p FROM Person_Basic p", Person.class)
//					.getResultList();
//		assertTrue(personsResultList.size()==1);
//		List<Address> addressResultList = 
//				this.entityManager
//					.createQuery("SELECT a FROM Address_Basic a", Address.class)
//					.getResultList();
//		assertTrue(addressResultList.size()==2);
//		this.printContents(personsResultList, Person.class);
// Show SQL!!!		
	}

// ============== D6! ===============
	@Test
	public void testBiOneToMany1() {
//		this.entityManager.getTransaction().begin();
//		Person p = new Person("Harald","Koenig");
//		this.entityManager.persist(p);
//		Address a1 = new Address("Inndalsveien", 28);
//		a1.setResident(p);
//		Address a2 = new Address("Freundallee", 15);
//		a2.setResident(p);
//		this.entityManager.persist(a1);
//		this.entityManager.persist(a2);
//		this.entityManager.getTransaction().commit();
		
//		this.entityManager.clear();
		
//		Person pFromDB = this.entityManager.find(Person.class, 1L);
//		assertNotNull(pFromDB);
//		List<Address> resultList = this.entityManager.createQuery("select a from Address_Basic a", Address.class).getResultList();
//		assertEquals(2, resultList.size());
//		assertEquals(2, pFromDB.getAdresses().size());
// Why does this test case fail? 		
		
//		System.out.println("Stored persons: "); 
//		System.out.println(pFromDB); 
// Why are the references from person to address not there after storage? 
	}
	@Test
	public void testBiOneToMany2() {
// Carry out this test after setting hibernate.hbm2ddl.auto to "none"		
//		Person pFromDB = this.entityManager.find(Person.class, 1L);
//		assertNotNull(pFromDB);
//		System.out.println("Stored persons: "); 
//		System.out.println(pFromDB);
	}
// =========== Optional demo of refresh() =====================	
	@Test
	public void testBiOneToMany3() {
// Carry out this test after setting hibernate.hbm2ddl.auto to "none"	
//		Person pFromDB = this.entityManager.find(Person.class, 1L);
//		assertNotNull(pFromDB);
//		System.out.println("Stored persons: " + pFromDB);
//		pFromDB.addAddress(new Address("Holiday Street", 1));
//		System.out.println("Changed person: " + pFromDB);
//		this.entityManager.refresh(pFromDB);
//		System.out.println("Reloaded: " + pFromDB);
	}
// ===================== D7 (optional) ===================
	@Test
	public void testBiManyToMany() {
//		this.entityManager.getTransaction().begin();
//		Person p1 = new Person("Magnus", "Carlson");
//		Person p2 = new Person("Fabiano", "Caruana");
//		Address a1 = new Address("Inndalsveien", 28);
//		Address a2 = new Address("Olaf Kyrres Gate", 15);
//		p1.addAddress(a1);
//		p1.addAddress(a2);
//		p2.addAddress(a2);
//		this.entityManager.persist(p1);
//		this.entityManager.persist(p2);
//		this.entityManager.getTransaction().commit();
//		
//		this.entityManager.clear();
//		List<Person> personsFromDB = this.entityManager.createNativeQuery("select * from Person_Basic", Person.class).getResultList();
//		assertTrue(personsFromDB.size()==2);
//		List<Address> addrFromDB = this.entityManager.createNativeQuery("Select * from Address_Basic", Address.class).getResultList(); 
//		assertTrue(addrFromDB.size()==2);
//		this.printContents(personsFromDB, Person.class);
//		addrFromDB.forEach(a->System.out.println("Number of residents @ " + a + ": " + a.getResidents().size() + ", "));
		// Address..getMyPersons() works although the List was never instantiated in Address
		
		// Can you guess, which is the output of the following statement? 
		// addrFromDB.forEach(a->System.out.println(a.getResidents().getClass().getName() + ", ")); 
	}
	
// =================== Auxiliaries ============================= 	
/**
 * EFFECTS a cyclic shift: First element becomes last
 * REQUIRES the list not to be empty	
 */
	private List<String> shiftCyclic(List<String> names){
		List<String> result = names;
		String first = result.remove(0);
		result.add(first);
		return result;
	}
	
	private <T> void printContents(List<T> resultList, Class<T> c) {
		System.out.println("Stored objects of type " + c.getName() + ":");
		resultList.forEach((d)->System.out.println(d));
	}

}
