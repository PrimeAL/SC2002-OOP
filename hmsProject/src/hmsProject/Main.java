package hmsProject;
import java.util.Scanner;

/**
 * Main class.
 */
public class Main {

	/**
	 * Where the programme all begins.
	 * Creates the Scanner class to be passed throughout the system.
	 * Creates the HMS and call its initialisation.
	 * @param args arguments
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HMS hms = new HMS();
		hms.initialise(sc);
	}

}