package hmsProject;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

/**
 * User superclass that all users inherit from
 */
public class User implements UserOp, ApptOp, Serializable {
	private String hID;
	private String pw;

	/**
	 * User constructor
	 * @param uid unique user identifier. Also the username
	 * @param pw password
	 */
	public User(String uid, String pw) {
		this.hID = uid;
		this.pw = pw;
	}

	/**
	 * Empty userInterface to be overriden.
	 * @param cont Controller
	 * @param sc Scanner class for input
	 */
	public void userInterface(Controller cont, Scanner sc) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Changing User password.
	 * @param sc Scanner class for input
	 */
	public void changePW(Scanner sc) {
		String pass;
		String pass2;
		System.out.print("Please key in your current password: ");
		pass = sc.nextLine();
		while (!Objects.equals(this.getPw(), pass) && !Objects.equals(pass, "0")) {
			System.out.print("Incorrect password. Please try again or enter 0 to exit: ");
			pass = sc.nextLine();
		}
		if (!Objects.equals(pass, "0")) {
			do {
				System.out.print("Please key in your new password: ");
				pass = sc.nextLine();
				System.out.print("Please re-enter your new password: ");
				pass2 = sc.nextLine();
				if (!Objects.equals(pass, pass2)) {
					System.out.println("Password mismatch. Please key again. ");
				}
			} while (!Objects.equals(pass, pass2));

			this.setPw(pass);
		}
	}

	/**
	 * Empty appointment operations to be overriden.
	 */
	public void apptOp() {
		throw new UnsupportedOperationException();
	}

	/**
	 * id getter.
	 * @return id.
	 */
	public String gethID() {
		return hID;
	}

	/**
	 * id setter.
	 * @param hID new id
	 */
	public void sethID(String hID) {
		this.hID = hID;
	}

	/**
	 * password getter.
	 * @return password.
	 */
	public String getPw() {
		return pw;
	}

	/**
	 * password setter.
	 * @param pw new password
	 */
	public void setPw(String pw) {
		this.pw = pw;
	}
}