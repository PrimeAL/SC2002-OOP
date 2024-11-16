package hmsProject;
import java.util.Scanner;
public class Main {

	/**
	 * Where the programme all begins.
	 * Creates the Scanner class to be passed throughout the system.
	 * Creates the HMS and call its initialisation.
	 * @param args arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		HMS hms = new HMS();
		hms.initialise(sc);
	}

}