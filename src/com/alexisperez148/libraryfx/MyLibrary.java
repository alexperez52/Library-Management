package com.alexisperez148.libraryfx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MyLibrary implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private ArrayList<Book> bookList;
	private ArrayList<User> userList;
	private  int bookId = 0;

	public MyLibrary() {
		bookList = new ArrayList<>();
		userList = new ArrayList<>();
		bookId = 0;
		
	}

	public MyLibrary(ArrayList<User> userList, ArrayList<Book> bookList) {
		this.bookList = bookList;
		this.userList = userList;

		
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public int getBookId() {
		return this.bookId;
	}
	
	public int addBookId() {
	
		return bookId++;
	}

	public ArrayList<User> getUsers() {
		if (!isUserEmpty()) {
			return userList;
		} else
			return null;
	}

	public boolean isUserEmpty() {
		if (userList.size() < 1) {
			return true;
		} else
			return false;
	}

	public void removeUser(User user) {
		if (userExists(user)) {
			userList.remove(user);
		}
	}

	public void addUser(User user) {
		
		if (!userExists(user)) {
			
			userList.add(user);
		}
	}

	private boolean userExists(User user) {
		if (userList.contains(user)) {
			return true;
		} else
			return false;
	}

	public void addBook(Book book) {
		if (!exists(book)) {
			
			book.setBookId(addBookId());
			bookList.add(book);
			
		}
	}

	private boolean exists(Book book) {
		if (bookList.contains(book)) {
			return true;
		} else
			return false;
	}

	public void removeBook(Book book) {
		if (exists(book)) {
			bookList.remove(book);
		}
	}

	public ArrayList<Book> getBooks() {
		if (!isEmpty()) {
			return bookList;
		} else
			return null;
	}

	public boolean isEmpty() {
		if (bookList.size() < 1) {
			return true;
		} else
			return false;
	}

	public ArrayList<Book> getBookByAuthor(String checkAuthor) {
		ArrayList<Book> bookListNew = new ArrayList<>();
		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).getAuthor().equals(checkAuthor)) {
				bookListNew.add(bookList.get(i));
			}
		}
		return bookListNew;
	}

	public ArrayList<Book> getBookByTitle(String checkTitle) {
		ArrayList<Book> bookListNew = new ArrayList<>();
		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).getTitle().equals(checkTitle)) {
				bookListNew.add(bookList.get(i));
			}
		}
		return bookListNew;
	}

	public ArrayList<Book> getBookByCategory(String checkCategory) {
		ArrayList<Book> bookListNew = new ArrayList<>();
		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).getCategory().equals(checkCategory)) {
				bookListNew.add(bookList.get(i));
			}
		}
		return bookListNew;
	}

	public ArrayList<Book> getBookByISBN(String checkISBN) {
		ArrayList<Book> bookListNew = new ArrayList<>();
		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).getIsbn().equals(checkISBN)) {
				bookListNew.add(bookList.get(i));
			}
		}
		return bookListNew;
	}

	public boolean findBook(Book book) {
		if (exists(book)) {
			return true;
		} else
			return false;
	}

	public void clearBookShelf() {
		if (!isEmpty()) {
			bookList.removeAll(bookList);
		}
	}

	@Override
	public String toString() {

		String message = "";
		String bookCount = "There are " + bookList.size() + " books in this shelf\n";

		for (int i = 0; i < bookList.size(); i++) {
			message += "|Title: " + bookList.get(i).getTitle() + "|Author: " + bookList.get(i).getAuthor() + "|ISBN: "
					+ bookList.get(i).getIsbn() + "|Category: " + bookList.get(i).getCategory() + "\n";
		}

		return bookCount + message;
	}

	public Object clone() {
		MyLibrary cloneShelf = null;
		MyLibrary newBookShelf = new MyLibrary();
		try {
			cloneShelf = (MyLibrary) super.clone();

			newBookShelf = cloneShelf;
		} catch (CloneNotSupportedException e) {

		}
		return newBookShelf;

	}
	
	public static void printPassword(String locationPassword) throws FileNotFoundException, IOException {
		StringBuffer password = new StringBuffer("adminKey");
		File passwordFile = new File(locationPassword + ".dat");
		
		 StringBuffer result= new StringBuffer(); 
		  
	        for (int i=0; i<password.length(); i++) 
	        { 
	            if (Character.isUpperCase(password.charAt(i))) 
	            { 
	                char ch = (char)(((int)password.charAt(i) + 
	                                  5 - 65) % 26 + 65); 
	                result.append(ch); 
	            } 
	            else
	            { 
	                char ch = (char)(((int)password.charAt(i) + 
	                                  5- 97) % 26 + 97); 
	                result.append(ch); 
	            } 
	        } 
	        password = result;
		
		try ( ObjectOutputStream passwordOutput = new ObjectOutputStream(new FileOutputStream(passwordFile, false));
				)
		{
		passwordOutput.writeObject(password);	
		}
	}
	

	public static void printFile(String locationUsers, String locationBooks, String locationId, MyLibrary bookShelf)
			throws IOException, ClassNotFoundException, NullPointerException {
		
		
		User[] users = new User[bookShelf.getUsers().size()];
		File fileUsers = new File(locationUsers + ".dat");
		Book[] books = new Book[bookShelf.getBooks().size()];
		File fileBooks = new File(locationBooks + ".dat");
		int bookId = bookShelf.getBookId();
		File bookIdFile = new File(locationId + ".dat");

		
		for (int i = 0; i < books.length; i++) {
			books[i] = bookShelf.getBooks().get(i);
		}
		
		for (int i = 0; i <users.length; i++) {
			users[i] = bookShelf.getUsers().get(i);
		}

//		if (fileUsers.exists() && fileBooks.exists() && bookIdFile.exists()) {
//			bookIdFile.delete();
//			fileUsers.delete();
//			fileBooks.delete();
//			
//		}
		
		try (			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(bookIdFile, false));
					ObjectOutputStream output1 = new ObjectOutputStream(new FileOutputStream(fileUsers, false));
				ObjectOutputStream output2 = new ObjectOutputStream(new FileOutputStream(fileBooks, false));)
		{
			output.writeInt(bookId);
			output2.writeObject(books);
			output1.writeObject(users);
		}
		}

	

	public static MyLibrary readFile(String userFile, String bookFile, String bookIdFile) throws IOException, ClassNotFoundException{
		MyLibrary newBookShelf = null;
		
		int idObj= 0;
		// File file = new File(location);
		ArrayList<Book> bookList = new ArrayList<>();
		ArrayList<User> userList = new ArrayList<>();
		try (	ObjectInputStream userInput = new ObjectInputStream(new FileInputStream(userFile));
				ObjectInputStream input = new ObjectInputStream(new FileInputStream(bookFile));
				ObjectInputStream id = new ObjectInputStream(new FileInputStream(bookIdFile));
				){
			
			
			
			
			
			User[] userObj = (User[]) (userInput.readObject());
			Book[] obj = (Book[]) (input.readObject());
			//book array
			for(int i = 0; i < 1; i++) {
				idObj = id.readInt();
				id.close();
			}
			for (int i = 0; i < obj.length; i++) {

				bookList.add(obj[i]);
				input.close();
			}
		
			input.close();
			for (int i = 0; i < userObj.length; i++) {
				
				userList.add(userObj[i]);
				userInput.close();
			}
			
			
		
				}
				
	
		newBookShelf = new MyLibrary(userList, bookList);
		newBookShelf.setBookId(idObj);
		
		return newBookShelf;

		}

}