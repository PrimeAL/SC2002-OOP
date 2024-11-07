package hmsProject;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class User implements UserOp, ApptOp, Serializable {

	private String hID;
	private String pw;

	public User(String uid, String pw) {
		// TODO Auto-generated constructor stub
		this.hID = uid;
		this.pw = pw;
	}

	public void userInterface(controller cont, Scanner sc) {
		throw new UnsupportedOperationException();
	}

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

	public void apptOp() {
		throw new UnsupportedOperationException();
	}

	public String gethID() {
		return hID;
	}

	public void sethID(String hID) {
		this.hID = hID;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
}