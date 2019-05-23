package com.alexisperez148.libraryfx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String age;
	private String streetNumber;
	private String streetName;
	private String town;
	private String state;
	private String zipCode;
	private String username;
	private String password;
	private ArrayList<Book> borrowedBooks;


	public User(String firstName, String lastName, String age, String streetNumber, String streetName, String town,
			String state, String zipCode, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.town = town;
		this.state = state;
		this.zipCode = zipCode;
		this.username = username;
		this.password = password;
		this.borrowedBooks = new ArrayList<Book>();

	}

	public User() {
		this("Place", "Holder", "Default", "Default", "Default", "Default", "Default", "Default", "username",
				"password");
		this.borrowedBooks = new ArrayList<Book>();
	}

	public void returnBooks(Book books) {

			books.setUser(null);
			books.setDateCreated(null);
			books.setDateDue(null);
			borrowedBooks.remove(books);

		
	}

	public void borrowBooks(Book books) {

		if (!borrowed(books)) {
			
			books.setUser(this);
			books.setDateCreated(new Date());
			Calendar dueDate = Calendar.getInstance();
			dueDate.setTime(books.getDateCreated());
			dueDate.add(Calendar.DATE, 5);
			books.setDateDue(dueDate.getTime());
			borrowedBooks.add(books);
					
		}
	}

	public boolean borrowed(Book book) {

		if (borrowedBooks.contains(book)) {
			return true;
		} else
			return false;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsernameAndPassword() {
		return username + " " + password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Book> getBorrowedBooks() {

		return borrowedBooks;
	}

	public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}

	@Override
	public String toString() {
		return getUsername();

	}

}
