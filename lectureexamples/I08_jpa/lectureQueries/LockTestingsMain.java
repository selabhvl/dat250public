package no.hvl.dat250.jpa.tutorial.lectureQueries;

public class LockTestingsMain {

	public static void main(String[] args) {
		new AddressPriceUpdateConcurrently().doConcurrentUpdate();
	}
}
