package com.alexisperez148.libraryfx;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Book implements Cloneable , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String title;
	private String author;
	private String isbn;
	private String category;
	private int bookId;
	private boolean isAvailable;
	private Date dateCreated;
	private User user;
	private Calendar dueDate;
	private Date dateDue;
	
	public Book(String title, String author, String isbn, String category) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.category = category;
		bookId =0;
		setAvailable(false);
		setDateCreated(null);
		setUser(null);
		setDateDue(null);
		
	}
	
	
	public Book() {
		this("Default Title", "Default Author", "0000", "Place Holder");
		
		
	}
	
	public String getIsAvailable() {
		if(this.getDateCreated() != null) {
			return "Unavailable";
		}
		else 
			return "Available";
		
	}


	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setAuthor(String author) {
		this.author = author;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	



	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getIsbn() {
		return isbn;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}


	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public boolean sameCategory(Book book, Book book2) {
		boolean value = false;
		if (book.getCategory() == book2.getCategory()) {
			value = true;
		} else
			value = false;
		return value;
	}

	public Book clone() {
		Book cloneBook =  null;
		
		try {
			cloneBook = (Book) super.clone();
			cloneBook.title = new String(this.title);
			cloneBook.author = new String(this.author);
			cloneBook.isbn = new String(this.isbn);
			cloneBook.category = new String(this.category);
			cloneBook.isAvailable = new Boolean(isAvailable);

		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return cloneBook;
	}
	public int getBookId() {
		return this.bookId;
	}

	@Override
	public String toString() {
		return "Title: " + getTitle() + " |Author: " + getAuthor() + " |ISBN: " + getIsbn() + " |Category: "
				+ getCategory() + "\n" + "|BookID: " + getBookId();
		
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		return false;
	}


	public Date getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Calendar getDueDate() {
		return dueDate;
	}


	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
		
	}


	public Date getDateDue() {
		return dateDue;
	}


	public void setDateDue(Date dateDue) {
		this.dateDue = dateDue;
	}


}
