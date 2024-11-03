package hmsProject;

import java.util.Scanner;

public class User implements UserOp, ApptOp {

	private String hID;
	private String pw;
	
	public User(String uid, String pw) {
		// TODO Auto-generated constructor stub
		this.hID=uid;
		this.pw=pw;
	}

	public void userInterface(controller cont, Scanner sc) {
		// TODO - implement User.userInterface
		throw new UnsupportedOperationException();
	}

	public void changePW() {
		// TODO - implement User.changePW
		throw new UnsupportedOperationException();
	}

	public void apptOp() {
		// TODO - implement User.apptOp
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