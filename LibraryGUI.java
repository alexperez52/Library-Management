import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.Label;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.alexisperez148.libraryfx.*;

public class LibraryGUI extends Application {

	ImageView userProfileViewPic;
	GridPane userGridPane;
	Text userFirstName, userLastName, userAge, userStreetNumber, userStreetName, userState, userTown, userZipCode,
			userNameText, userDescription;
	int finalIndex;
	Stage window;
	Scene mainMenu, userLoginMenu, scene3, userProfileMenu, userProfileView;
	Button backToMenu, backToMenuFromUser, backToMenuFromGuest, user, admin, guest, userLogin, userRegister, userEnter,
			adminLogin, guestLogin, deleteUser, checkProfile, addBook, deleteBook, menuButtonFromAcp, borrowBook,
			returnBook, mainMenuFromSearch, checkUser, mainMenuFromProfile, editProfile, bookSearch, closeProgram,
			register, returnButton, mainMenuFromGuestSearch, userProfileViewWindow;
	TextField firstNameTextField, lastNameTextField, ageTextField, streetNameTextField, streetNumberTextField,
			stateTextField, zipCodeTextField, registerUsernameText, registerPasswordText, townTextField,
			copiesTextField, guestTextField;

	TextField titleTextField, authorTextField, isbnTextField, categoryTextField, firstNameTf, lastNameTf, ageTf,
			streetNumberTf, streetNameTf, townTf, stateTf, zipCodeTf, checkUsername, checkPassword, userNameTextField;
	String profileDisplayFirstName, profileDisplayLastName, profileDisplayAge, profileDisplayStreetNumber,
			profileDisplayStreetName, profileDisplayTown, profileDisplayState, profileDisplayUsername,
			profileDisplayZipCode;
	PasswordField passWordTextField;

	private TableView<Book> guestSearch = new TableView<>();
	private TableView<Book> table = new TableView<>();
	private TableView<Book> tableSearch = new TableView<>();
	private TableView<User> userTable = new TableView<>();
	private TableView<Book> borrowedTable = new TableView<>();

	MyLibrary library = new MyLibrary();

	Book book = new Book();

	ObservableList<Book> guestBooks;
	ObservableList<Book> borrowedBooks;
	ObservableList<User> users;
	ObservableList<Book> books;
	ObservableList<Book> finalList;

	@SuppressWarnings("unchecked")
	@Override

	public void start(Stage primary) throws Exception {

		startProgram();

		finalList = FXCollections.observableArrayList(library.getBooks());
		guestBooks = FXCollections.observableArrayList(library.getBooks());
		users = FXCollections.observableArrayList(library.getUsers());
		books = FXCollections.observableArrayList(library.getBooks());

		primary.getIcons().add(new Image("Sketch.jpg"));
		window = primary;

		// main menu
		BorderPane mainMenuPane = new BorderPane();
		mainMenuPane.setTop(getMainMenuHBox());
		mainMenuPane.setCenter(getMainMenuVBox());

		// user menu
		GridPane userLoginPane = new GridPane();

		userLoginPane.setAlignment(Pos.TOP_CENTER);
		userLoginPane.setPadding(new Insets(15, 15, 15, 15));
		userLoginPane.setHgap(1);
		userLoginPane.setVgap(7);
		userNameTextField = new TextField();
		passWordTextField = new PasswordField();
		Text text1 = new Text(20, 20, "");
		Text text2 = new Text(20, 20, "");
		text1.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 15));
		text2.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 15));
		userLoginPane.setAlignment(Pos.BOTTOM_CENTER);
		userLoginPane.add(new ImageView(new Image("Book-Cover-Quill-81770.gif", 200, 200, false, false)), 8, 0);		// userLoginPane.add(new ImageView(new Image("3232 (1).png")), 8, 1);
		userLoginPane.add(text1, 7, 2);
		userLoginPane.add(userNameTextField, 8, 2);
		userLoginPane.add(text2, 7, 3);
		userLoginPane.add(passWordTextField, 8, 3);
		userLogin = new Button("    Login      ");

		userRegister = new Button("   Register   ");
		backToMenuFromUser = new Button("Main Menu ");
		userLoginPane.add(backToMenuFromUser, 8, 7);
		userLoginPane.add(userLogin, 8, 5);
		userLoginPane.add(userRegister, 8, 6);

		Image userLoginImage = new Image("userLoginMenu.png", 750, 450, false, false);
		ImageView userMenuLogo = new ImageView(userLoginImage);

		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(userMenuLogo, userLoginPane);

		HBox userMenuHBox = new HBox();
		userMenuHBox.getChildren().add(stackPane);

		// admin login
		StackPane stackPane5 = new StackPane();
		FlowPane adminLoginPane = new FlowPane();

		adminLoginPane.setPadding(new Insets(15, 15, 15, 15));
		adminLoginPane.setHgap(10);
		adminLoginPane.setVgap(10);
		adminLogin = new Button("Login");
		backToMenu = new Button("Main Menu");
		Text text3 = new Text(20, 20, "");
		Text text4 = new Text(20, 20, "");
		Text adminText = new Text(10, 10, "");
		adminText.setFont(Font.font("Denmark", FontWeight.BOLD, FontPosture.REGULAR, 40));

		adminText.setFill(Color.DARKRED);
		TextField adminTf = new TextField("administrator");
		PasswordField passwordTf = new PasswordField();

		adminTf.setMaxWidth(150);
		passwordTf.setMaxWidth(150);	
		Image image = new Image("check.png", 730, 450, false, false);
		ImageView adminMenuLogo = new ImageView(image);

		text3.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 25));
		text3.setFill(Color.DARKRED);
		text4.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 25));
		text4.setFill(Color.DARKRED);
		// Change texts to generated text Pictures
		adminLoginPane.getChildren().addAll(adminText, text3, adminTf, text4, passwordTf, adminLogin, backToMenu);
		adminLoginPane.setAlignment(Pos.CENTER);
		adminLoginPane.setOrientation(Orientation.VERTICAL);

		stackPane5.getChildren().addAll(adminMenuLogo, adminLoginPane);

		HBox rootTry = new HBox();
		rootTry.getChildren().add(stackPane5);
		// guest login
		BorderPane guestLoginPane = new BorderPane();


		guestLoginPane.setCenter(getGuestLogin());
		guestLoginPane.setBottom(getGuestLoginVBox());

		Image guestLoginImage = new Image("guestNew.png", 750, 450, false, false);
		ImageView guestMenuLogo = new ImageView(guestLoginImage);

		StackPane guestStackPane = new StackPane();
		guestStackPane.getChildren().addAll(guestMenuLogo, guestLoginPane);

		HBox guestMenuHBox = new HBox();
		guestMenuHBox.getChildren().add(guestStackPane);
		// register user
		FlowPane fatherNode = new FlowPane();

		fatherNode.setAlignment(Pos.CENTER);
		GridPane registerMenuPane = new GridPane();

		registerMenuPane.setPadding(new Insets(55, 15, 15, 15));
		registerMenuPane.setVgap(10);
		registerMenuPane.setHgap(10);
		Text firstName = new Text("                                       ");
		Text lastName = new Text("");
		Text age = new Text("");
		Text streetNumber = new Text("");
		Text streetName = new Text("");
		Text town = new Text("");
		Text state = new Text("");
		Text zipCode = new Text("");
		firstNameTf = new TextField();
		lastNameTf = new TextField();
		ageTf = new TextField();
		streetNumberTf = new TextField();
		streetNameTf = new TextField();
		townTf = new TextField();
		stateTf = new TextField();
		zipCodeTf = new TextField();

		registerMenuPane.add(firstName, 0, 0);
		registerMenuPane.add(firstNameTf, 1, 0);
		registerMenuPane.add(lastName, 0, 2);
		registerMenuPane.add(lastNameTf, 1, 2);
		registerMenuPane.add(age, 0, 4);
		registerMenuPane.add(ageTf, 1, 4);
		registerMenuPane.add(streetNumber, 0, 6);
		registerMenuPane.add(streetNumberTf, 1, 6);
		registerMenuPane.add(streetName, 0, 8);
		registerMenuPane.add(streetNameTf, 1, 8);
		registerMenuPane.add(town, 0, 10);
		registerMenuPane.add(townTf, 1, 10);
		registerMenuPane.add(state, 0, 12);
		registerMenuPane.add(stateTf, 1, 12);
		registerMenuPane.add(zipCode, 0, 14);
		registerMenuPane.add(zipCodeTf, 1, 14);

		GridPane confirmation = new GridPane();
		confirmation.setPadding(new Insets(15, 15, 15, 15));
		confirmation.setVgap(10);
		confirmation.setHgap(10);
		confirmation.setAlignment(Pos.CENTER);
		Text userText = new Text("              ");
		Text passText = new Text("              ");
		// LEFT OFF HERE
		registerUsernameText = new TextField();
		registerPasswordText = new TextField();
		Button menuFromReg = new Button("Main Menu");

		register = new Button("Register");

		confirmation.add(userText, 0, 3);
		confirmation.add(registerUsernameText, 1, 3);
		confirmation.add(passText, 0, 4);
		confirmation.add(registerPasswordText, 1, 4);
		confirmation.add(register, 1, 5);
		confirmation.add(menuFromReg, 1, 6);

		fatherNode.setHgap(90);
		fatherNode.getChildren().addAll(registerMenuPane, confirmation);
		Image userRegisterImage = new Image("UserRegistration.png", 750, 450, false, false);
		ImageView userRegisterLogo = new ImageView(userRegisterImage);

		StackPane stackPaneReg = new StackPane();
		stackPaneReg.getChildren().addAll(userRegisterLogo, fatherNode);

		HBox userRegHBox = new HBox();
		userRegHBox.getChildren().add(stackPaneReg);

		// Admin control panel
		BorderPane adminControlPane = new BorderPane();

		Text textAdmin = new Text(10, 10, "Add Books");
		textAdmin.setUnderline(true);
		textAdmin.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 25));

		FlowPane acpFlowPane = new FlowPane();
		acpFlowPane.setStyle("-fx-background-color: darkslategrey");
		acpFlowPane.getChildren().addAll(adminControlPane, ScrollPaneVbox());
		BorderPane finalPane = new BorderPane();

		finalPane.setPadding(new Insets(5, 5, 15, 5));
		finalPane.setStyle("-fx-background-color: darkslategrey");
		finalPane.setCenter(acpFlowPane);
		finalPane.setBottom(acpButton());

		// BookSearch

		final Text label1 = new Text("                                   Book Search");
		label1.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 25));

		tableSearch.setEditable(false);
		TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
		titleColumn.setMinWidth(150);
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

		TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
		authorColumn.setMinWidth(150);
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

		TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
		isbnColumn.setMaxWidth(60);
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

		TableColumn<Book, String> categoryColumn = new TableColumn<>("Category");
		categoryColumn.setMaxWidth(120);
		categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

		TableColumn<Book, String> bookIdColumn = new TableColumn<>("Book ID");
		bookIdColumn.setMaxWidth(60);
		bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));

		TableColumn<Book, String> statusSearchColumn = new TableColumn<>("Status");
		statusSearchColumn.setMaxWidth(150);
		statusSearchColumn.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));


		tableSearch.getColumns().addAll(titleColumn, authorColumn, isbnColumn, categoryColumn, bookIdColumn,
				statusSearchColumn);

		FilteredList<Book> flBook = new FilteredList<Book>(books, p -> true);

		tableSearch.setItems(flBook);

		ChoiceBox<String> choiceBox = new ChoiceBox<String>();
		choiceBox.getItems().addAll("Title", "Author", "ISBN", "Category");
		choiceBox.setValue("Title");

		TextField textField = new TextField();
		textField.setPromptText("Search here!");
		Button searchButton = new Button("Search");

		searchButton.setOnAction(e -> {
			switch (choiceBox.getValue()) {
			case "Title":
				flBook.setPredicate(p -> p.getTitle().toLowerCase().contains(textField.getText().toLowerCase().trim()));
				break;
			case "Author":
				flBook.setPredicate(
						p -> p.getAuthor().toLowerCase().contains(textField.getText().toLowerCase().trim()));
				break;
			case "ISBN":
				flBook.setPredicate(p -> p.getIsbn().toLowerCase().contains(textField.getText().toLowerCase().trim()));
				break;
			case "Category":
				flBook.setPredicate(
						p -> p.getCategory().toLowerCase().contains(textField.getText().toLowerCase().trim()));
				break;

			}
		});

		choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal != null) {
				textField.setText("");
				flBook.setPredicate(null);

			}
		});

		Button backToProfile = new Button("Back to Profile");
		backToProfile.setOnAction(e -> {
			window.setScene(userProfileMenu);
		});
		Button borrowBook = new Button("Borrow Book");
		HBox hBoxSearch = new HBox(choiceBox, textField, searchButton, borrowBook, backToProfile);
		hBoxSearch.setSpacing(5);
		hBoxSearch.setStyle("-fx-background-color: darkslategrey");
		hBoxSearch.setAlignment(Pos.CENTER);

		final VBox vBox = new VBox();
		vBox.setSpacing(5);
		vBox.setPadding(new Insets(10, 10, 0, 10));

		Button menuFromSearch = new Button("Main Menu");

		vBox.getChildren().addAll(label1, tableSearch, hBoxSearch, menuFromSearch);
		vBox.setStyle("-fx-background-color: darkslategrey");
		BorderPane group = new BorderPane();
		group.setCenter(vBox);

		group.setStyle("-fx-background-color: darkslategrey");

		// user login

		userLogin.setOnAction(e -> {

			if (checkUserLogin()) {

				String[] userConfirmation = new String[library.getUsers().size()];
				String[] userPassword = new String[library.getUsers().size()];
				boolean found = false;
				for (int i = 0; i < library.getUsers().size(); i++) {
					userConfirmation[i] = library.getUsers().get(i).getUsername();
					userPassword[i] = library.getUsers().get(i).getPassword();
					if (userNameTextField.getText().toLowerCase()
							.contentEquals(userConfirmation[i].toString().toLowerCase())
							&& passWordTextField.getText().contentEquals(userPassword[i].toString())) {
						finalIndex = i;

						// setStatus(finalIndex);
						borrowedBooks = FXCollections.observableArrayList(library.getUsers().get(i).getBorrowedBooks());

						borrowBook.setOnAction(y -> {

							if (!isBookBorrowed()) {

								AlertBox.display("Alert", "Book Borrowed Succesfully!");
								try {
									borrowButtonClicked(finalIndex);
									guestSearch.refresh();
									table.refresh();
									tableSearch.refresh();
									userTable.refresh();
									borrowedTable.refresh();
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (NullPointerException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							} else {
								AlertBox.display("Alert", "Sorry, This Book Is Unavailable");

							}

						});
						returnButton.setOnAction(l -> {

							// borrowedTable.getSelectionModel().getSelectedItem().setAvailable(true);
							returnButtonClicked(finalIndex);
							guestSearch.refresh();
							table.refresh();
							tableSearch.refresh();
							userTable.refresh();
							
							borrowedTable.refresh();

						});

						profileDisplayFirstName = library.getUsers().get(i).getFirstName();
						profileDisplayLastName = library.getUsers().get(i).getLastName();
						profileDisplayAge = library.getUsers().get(i).getAge();
						profileDisplayStreetNumber = library.getUsers().get(i).getStreetNumber();
						profileDisplayStreetName = library.getUsers().get(i).getStreetName();
						profileDisplayTown = library.getUsers().get(i).getTown();
						profileDisplayState = library.getUsers().get(i).getState();
						profileDisplayZipCode = library.getUsers().get(i).getZipCode();
						profileDisplayUsername = library.getUsers().get(i).getUsername();
						borrowedTable.setItems(borrowedBooks);

						userGridPane.getChildren().removeAll(userProfileViewPic, userFirstName, userLastName, userAge,
								userStreetNumber, userStreetName, userTown, userState, userZipCode, userNameText,
								firstNameTextField, lastNameTextField, ageTextField, streetNameTextField,
								stateTextField, zipCodeTextField);

						firstNameTextField = new TextField();
						lastNameTextField = new TextField();
						ageTextField = new TextField();
						streetNameTextField = new TextField();
						streetNumberTextField = new TextField();
						stateTextField = new TextField();
						townTextField = new TextField();
						zipCodeTextField = new TextField();

						userProfileViewPic = new ImageView(new Image("border.png"));

						userFirstName = new Text(10, 10, "First Name: " + profileDisplayFirstName);
						userFirstName.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));
					
						userLastName = new Text(10, 10, "Last Name: " + profileDisplayLastName);
						userLastName.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));

						userAge = new Text(10, 10, "Age: " + profileDisplayAge);
						userAge.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));

						userStreetNumber = new Text(10, 10, "Street #: " + profileDisplayStreetNumber);
						userStreetNumber.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));

						userStreetName = new Text(10, 10, "Street Name: " + profileDisplayStreetName);
						userStreetName.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));

						userTown = new Text(10, 10, "Town: " + profileDisplayTown);
						userTown.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));

						userState = new Text(10, 10, "State: " + profileDisplayState);
						userState.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));

						userZipCode = new Text(10, 10, "Zip Code: " + profileDisplayZipCode);
						userZipCode.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));

						userNameText = new Text(10, 10,
								"Welcome " + profileDisplayFirstName + " " + profileDisplayLastName);
						userNameText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 35));
						userNameText.setFill(Color.DARKRED);
						userNameText.setUnderline(true);
						userDescription = new Text(10, 10, "Regular User Access");
						userDescription.setFont(Font.font("Courier", FontWeight.NORMAL, FontPosture.ITALIC, 25));

						userGridPane.add(userNameText, 0, 0);
						userGridPane.add(userDescription, 0, 1);
						userGridPane.add(userFirstName, 0, 2);
						userGridPane.add(userLastName, 0, 3);
						userGridPane.add(userAge, 0, 4);
						userGridPane.add(userStreetNumber, 0, 5);
						userGridPane.add(userStreetName, 0, 6);

						userGridPane.add(userTown, 0, 7);

						userGridPane.add(userState, 0, 8);

						userGridPane.add(userZipCode, 0, 9);

						found = true;

					}
					editProfile.setOnAction(k -> {
						Stage editStage = new Stage();
						editStage.initModality(Modality.APPLICATION_MODAL);

						GridPane editProfileVbox = new GridPane();
						editProfileVbox.setPadding(new Insets(15));

						Text editFirstName = new Text("First Name: ");
						Text editLastName = new Text("Last Name: ");
						Text editAge = new Text("Age: ");
						Text editStreetNumber = new Text("Street #: ");
						Text editStreetName = new Text("Street Name: ");
						Text editTown = new Text("Town: ");
						Text editState = new Text("State: ");
						Text editZipCode = new Text("Zipcode: ");
						Text editUserName = new Text("Username: ");
						Text editPassWord = new Text("Password: ");

						TextField editUserNameTextField = new TextField(profileDisplayUsername);
						TextField editPassWordTextField = new TextField("");
						TextField editFirstNameTextField = new TextField(profileDisplayFirstName);
						TextField editLastNameTextField = new TextField(profileDisplayLastName);
						TextField editAgeTextField = new TextField(profileDisplayAge);
						TextField editStreetNumberTextField = new TextField(profileDisplayStreetNumber);
						TextField editStreetNameTextField = new TextField(profileDisplayStreetName);
						TextField editTownTextField = new TextField(profileDisplayTown);
						TextField editStateTextField = new TextField(profileDisplayState);
						TextField editZipCodeTextField = new TextField(profileDisplayZipCode);
						Button editProfileBtn = new Button("Confirm Edit");
						Button cancelEditBtn = new Button("Cancel Edit");

						editProfileVbox.add(editFirstName, 0, 0);
						editProfileVbox.add(editFirstNameTextField, 1, 0);
						editProfileVbox.add(editLastName, 0, 1);
						editProfileVbox.add(editLastNameTextField, 1, 1);
						editProfileVbox.add(editAge, 0, 2);
						editProfileVbox.add(editAgeTextField, 1, 2);
						editProfileVbox.add(editStreetNumber, 0, 3);
						editProfileVbox.add(editStreetNumberTextField, 1, 3);
						editProfileVbox.add(editStreetName, 0, 4);
						editProfileVbox.add(editStreetNameTextField, 1, 4);
						editProfileVbox.add(editTown, 0, 5);
						editProfileVbox.add(editTownTextField, 1, 5);
						editProfileVbox.add(editState, 0, 6);
						editProfileVbox.add(editStateTextField, 1, 6);
						editProfileVbox.add(editZipCode, 0, 7);
						editProfileVbox.add(editZipCodeTextField, 1, 7);
						editProfileVbox.add(editUserName, 0, 8);
						editProfileVbox.add(editUserNameTextField, 1, 8);
						editProfileVbox.add(editPassWord, 0, 9);
						editProfileVbox.add(editPassWordTextField, 1, 9);
						editProfileVbox.add(editProfileBtn, 0, 10);
						editProfileVbox.add(cancelEditBtn, 1, 10);

						editProfileVbox.setVgap(10);
						editProfileVbox.setHgap(10);

						cancelEditBtn.setOnAction(j -> editStage.close());
						editProfileBtn.setOnAction(d -> {

							library.getUsers().get(finalIndex).setFirstName(editFirstNameTextField.getText());
							library.getUsers().get(finalIndex).setLastName(editLastNameTextField.getText());
							library.getUsers().get(finalIndex).setAge(editAgeTextField.getText());
							library.getUsers().get(finalIndex).setStreetNumber(editStreetNumberTextField.getText());
							library.getUsers().get(finalIndex).setStreetName(editStreetNameTextField.getText());
							library.getUsers().get(finalIndex).setTown(editTownTextField.getText());
							library.getUsers().get(finalIndex).setState(editStateTextField.getText());
							library.getUsers().get(finalIndex).setZipCode(editZipCodeTextField.getText());
							library.getUsers().get(finalIndex).setUsername(editUserNameTextField.getText());
							library.getUsers().get(finalIndex).setPassword(editPassWordTextField.getText());
							
					
							if (editPassWordTextField.getText().isEmpty()) {
								AlertBox.display("Error", "Password field Empty");
							} else {

								
							
							profileDisplayFirstName = library.getUsers().get(finalIndex).getFirstName();
							profileDisplayLastName = library.getUsers().get(finalIndex).getLastName();
							profileDisplayAge = library.getUsers().get(finalIndex).getAge();
							profileDisplayStreetNumber = library.getUsers().get(finalIndex).getStreetNumber();
							profileDisplayStreetName = library.getUsers().get(finalIndex).getStreetName();
							profileDisplayTown = library.getUsers().get(finalIndex).getTown();
							profileDisplayState = library.getUsers().get(finalIndex).getState();
							profileDisplayZipCode = library.getUsers().get(finalIndex).getZipCode();
							profileDisplayUsername = library.getUsers().get(finalIndex).getUsername();
							borrowedTable.setItems(borrowedBooks);

							userGridPane.getChildren().removeAll(userProfileViewPic, userFirstName, userLastName,
									userAge, userStreetNumber, userStreetName, userTown, userState, userZipCode,
									userNameText, firstNameTextField, lastNameTextField, ageTextField,
									streetNameTextField, stateTextField, zipCodeTextField);

							firstNameTextField = new TextField();
							lastNameTextField = new TextField();
							ageTextField = new TextField();
							streetNameTextField = new TextField();
							streetNumberTextField = new TextField();
							stateTextField = new TextField();
							townTextField = new TextField();
							zipCodeTextField = new TextField();

							userProfileViewPic = new ImageView(new Image("nice.jpg"));

							userFirstName = new Text(10, 10, "First Name: " + profileDisplayFirstName);
							userFirstName.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));

							userLastName = new Text(10, 10, "Last Name: " + profileDisplayLastName);
							userLastName.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));

							userAge = new Text(10, 10, "Age: " + profileDisplayAge);
							userAge.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));

							userStreetNumber = new Text(10, 10, "Street #: " + profileDisplayStreetNumber);
							userStreetNumber.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));

							userStreetName = new Text(10, 10, "Street Name: " + profileDisplayStreetName);
							userStreetName.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));

							userTown = new Text(10, 10, "Town: " + profileDisplayTown);
							userTown.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));

							userState = new Text(10, 10, "State: " + profileDisplayState);
							userState.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));

							userZipCode = new Text(10, 10, "Zip Code: " + profileDisplayZipCode);
							userZipCode.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));

							userNameText = new Text(10, 10,
									"Welcome " + profileDisplayFirstName + " " + profileDisplayLastName);
							userNameText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 35));
							userNameText.setFill(Color.DARKRED);
							userNameText.setUnderline(true);
							userDescription = new Text(10, 10, "Regular User Access");
							userDescription.setFont(Font.font("Courier", FontWeight.NORMAL, FontPosture.ITALIC, 25));
							

							userGridPane.add(userNameText, 0, 0);
							userGridPane.add(userDescription, 0, 1);
							userGridPane.add(userFirstName, 0, 2);
							userGridPane.add(userLastName, 0, 3);
							userGridPane.add(userAge, 0, 4);
							userGridPane.add(userStreetNumber, 0, 5);
							userGridPane.add(userStreetName, 0, 6);

							userGridPane.add(userTown, 0, 7);

							userGridPane.add(userState, 0, 8);

							userGridPane.add(userZipCode, 0, 9);
					
							editStage.close();
							guestSearch.refresh();
							table.refresh();
							tableSearch.refresh();
							userTable.refresh();
							borrowedTable.refresh();
							}
						
						});

						Scene editProfileSecene = new Scene(editProfileVbox);

						editStage.setScene(editProfileSecene);
						editStage.show();

					});

				}

				if (found) {

					window.setScene(userProfileMenu);
					userNameTextField.clear();
					passWordTextField.clear();
				}

			} else {
				AlertBox.display("Error", "Login Info Is Incorrect");
			}

		
		});

		
		
		
		BorderPane tryPane = new BorderPane();
		userGridPane = new GridPane();
		userProfileViewPic = new ImageView(new Image("nice.jpg"));

		userGridPane.setVgap(5);
		userGridPane.setHgap(5);

		userGridPane.setPadding(new Insets(15, 15, 15, 15));

		firstNameTextField = new TextField();
		lastNameTextField = new TextField();
		ageTextField = new TextField();
		streetNameTextField = new TextField();
		streetNumberTextField = new TextField();
		stateTextField = new TextField();

		townTextField = new TextField();
		zipCodeTextField = new TextField();

		HBox buttonHbox = new HBox();
		mainMenuFromProfile = new Button("Log Out");
		editProfile = new Button("Edit Profile");
		bookSearch = new Button("Search Books");
		
		buttonHbox.getChildren().addAll(mainMenuFromProfile, editProfile, bookSearch);

		buttonHbox.setSpacing(7);
		buttonHbox.setAlignment(Pos.CENTER_RIGHT);
		buttonHbox.setPadding(new Insets(15));
		BorderPane profileViewBorder = new BorderPane();
		profileViewBorder.setCenter(userGridPane);
		profileViewBorder.setBottom(buttonHbox);
		
		StackPane profileViewStackPane = new StackPane();

		Image profileImage = new Image("border.png");
		ImageView profileImageView = new ImageView(profileImage);
		
		profileViewStackPane.getChildren().addAll(profileImageView, profileViewBorder);
		HBox profileViewHbox = new HBox();
		profileViewHbox.getChildren().add(profileViewStackPane);
		
		BorrowedDescriptionPane borrowedPane = new BorrowedDescriptionPane();

		HBox topOfBorrowed = new HBox(15);
		Text borrowedText = new Text(10, 10, "Borrowed Book List");
		borrowedText.setUnderline(true);
		borrowedText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 30));
		topOfBorrowed.getChildren().add(borrowedText);
		topOfBorrowed.setAlignment(Pos.CENTER);

		topOfBorrowed.setStyle("-fx-background-color: darkslategrey");
		tryPane.setTop(topOfBorrowed);
		tryPane.setCenter(borrowedPane);

		StackPane bgStackPane = new StackPane();

	
		bgStackPane.getChildren().addAll(tryPane);
		HBox root1 = new HBox();
		root1.getChildren().add(bgStackPane);

		// guest search
		BorderPane guestSearchPane = new BorderPane();
		DescriptionPaneGuest guestSearch = new DescriptionPaneGuest();
		guestSearchPane.setCenter(guestSearch);

		// scenes
		Scene bookSearchMenu = new Scene(group, 640, 550);
		userProfileMenu = new Scene(root1, 740, 570);
		Scene adminControlPanel = new Scene(finalPane, 700, 600);
		Scene registerMenu = new Scene(userRegHBox, 750, 450);
		Scene mainMenu = new Scene(mainMenuPane, 750, 450);
		Scene userLoginMenu = new Scene(userMenuHBox, 750, 450);
		Scene adminLoginMenu = new Scene(rootTry, 730, 450);
		Scene guestLoginMenu = new Scene(guestMenuHBox, 750, 450);
		Scene guestSearchMenu = new Scene(guestSearchPane, 705, 500);
		userProfileView = new Scene(profileViewHbox, 750, 450);

		// actions
		userProfileViewWindow.setOnAction(e -> window.setScene(userProfileView));
		
	
		register.setOnAction(e -> {
			if (!(firstNameTf.getText().isEmpty() || lastNameTf.getText().isEmpty() || ageTf.getText().isEmpty()
					|| streetNumberTf.getText().isEmpty() || streetNameTf.getText().isEmpty()
					|| townTf.getText().isEmpty() || stateTf.getText().isEmpty() || zipCodeTf.getText().isEmpty()
					|| registerUsernameText.getText().isEmpty() || registerPasswordText.getText().isEmpty())) {

				registerUser();
				firstNameTf.clear();
				lastNameTf.clear();
				ageTf.clear();
				streetNumberTf.clear();
				streetNameTf.clear();
				townTf.clear();
				stateTf.clear();
				zipCodeTf.clear();
				registerUsernameText.clear();
				registerPasswordText.clear();

				window.setScene(userLoginMenu);
			} else {
				AlertBox.display("Error", "Please fill in All Data Fields");
			}
		});
		mainMenuFromGuestSearch.setOnAction(e -> window.setScene(mainMenu));
		menuFromReg.setOnAction(e -> window.setScene(mainMenu));
		guest.setOnAction(e -> 	window.setScene(guestLoginMenu));
		backToMenuFromUser.setOnAction(e -> window.setScene(mainMenu));
		backToMenu.setOnAction(e -> window.setScene(mainMenu));
		user.setOnAction(e -> window.setScene(userLoginMenu));
		admin.setOnAction(e -> window.setScene(adminLoginMenu));
		backToMenuFromGuest.setOnAction(e -> window.setScene(mainMenu));
		userRegister.setOnAction(e -> window.setScene(registerMenu));
		adminLogin.setOnAction(e -> 
		{
			String check = null;
			try {
				check = decyptPassword();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(passwordTf.getText().contentEquals(check)) {
				window.setScene(adminControlPanel);
			}
			
			else {
				AlertBox.display("Error", "Incorrect Password");
			}
			passwordTf.clear();
			
		});
		menuButtonFromAcp.setOnAction(e -> window.setScene(mainMenu));
		guestLogin.setOnAction(e -> window.setScene(guestSearchMenu));
		mainMenuFromProfile.setOnAction(e -> window.setScene(mainMenu));
		bookSearch.setOnAction(e -> window.setScene(bookSearchMenu));
		menuFromSearch.setOnAction(e -> window.setScene(mainMenu));
		window.setOnCloseRequest(e -> {
			try {
				closeProgram();
			} catch (ClassNotFoundException | IOException e1) {

			}
		});

		// main display
		window.setTitle("LucentLibraries™");
		window.setScene(mainMenu);
		window.setResizable(false);
		window.show();
	}

	private HBox acpButton() {
		HBox hBox = new HBox(15);
		hBox.setPadding(new Insets(5, 25, 5, 5));
		menuButtonFromAcp = new Button("Main Menu");
	
		
		deleteUser = new Button("Delete User");
		deleteUser.setOnAction(e -> {
			boolean result = AlertBox.checkClose("Alert", "Are you sure you want to delete this user?");

			if (result) {
				deleteUserClicked();
			}

		});

		hBox.getChildren().addAll(deleteUser, menuButtonFromAcp);
		hBox.setStyle("-fx-background-color: darkslategrey");
		hBox.setAlignment(Pos.CENTER_RIGHT);
		return hBox;

	}

	private VBox ScrollPaneVbox() {
		VBox vBox = new VBox(5);
		vBox.setAlignment(Pos.CENTER);
		vBox.setPadding(new Insets(5, 5, 5, 5));

		Text listBook = new Text(20, 20, "List of Books");
		listBook.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 25));
		Text listUser = new Text(20, 20, "List of Users");
		listUser.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 25));
		listUser.setUnderline(true);
		listBook.setUnderline(true);
		DescriptionPane descriptionPane = new DescriptionPane();

		DescriptionPaneUser descriptionPaneUser = new DescriptionPaneUser();

		vBox.getChildren().addAll(listBook, descriptionPane, listUser, descriptionPaneUser);
		return vBox;
	}

	public class DescriptionPaneGuest extends BorderPane {
		@SuppressWarnings("unchecked")
		public DescriptionPaneGuest() {

			guestSearch = new TableView<>();

			guestSearch.setEditable(true);
			TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
			titleColumn.setMinWidth(150);
			titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

			TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
			authorColumn.setMinWidth(150);
			authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

			TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
			isbnColumn.setMaxWidth(60);
			isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

			TableColumn<Book, String> categoryColumn = new TableColumn<>("Category");
			categoryColumn.setMinWidth(150);
			categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

			TableColumn<Book, String> bookIdColumn = new TableColumn<>("Book ID");
			bookIdColumn.setMaxWidth(60);
			bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));

			TableColumn<Book, String> statusColumn = new TableColumn<>("Status");
			statusColumn.setMaxWidth(150);
			statusColumn.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));
		
			guestSearch.getColumns().addAll(titleColumn, authorColumn, isbnColumn, categoryColumn, bookIdColumn,
					statusColumn);

			FilteredList<Book> flBook = new FilteredList<Book>(books, p -> true);
			guestSearch.setItems(flBook);

			ChoiceBox<String> choiceBox = new ChoiceBox<String>();
			choiceBox.getItems().addAll("Title", "Author", "ISBN", "Category");
			choiceBox.setValue("Title");

			TextField textField = new TextField();
			textField.setPromptText("Search here!");
			Button searchButton = new Button("Search");

			searchButton.setOnAction(e -> {
				switch (choiceBox.getValue()) {
				case "Title":
					flBook.setPredicate(
							p -> p.getTitle().toLowerCase().contains(textField.getText().toLowerCase().trim()));
					break;
				case "Author":
					flBook.setPredicate(
							p -> p.getAuthor().toLowerCase().contains(textField.getText().toLowerCase().trim()));
					break;
				case "ISBN":
					flBook.setPredicate(
							p -> p.getIsbn().toLowerCase().contains(textField.getText().toLowerCase().trim()));
					break;
				case "Category":
					flBook.setPredicate(
							p -> p.getCategory().toLowerCase().contains(textField.getText().toLowerCase().trim()));
					break;

				}
			});

			choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
				if (newVal != null) {
					textField.setText("");
					flBook.setPredicate(null);

				}
			});

			HBox hBox = new HBox(choiceBox, textField, searchButton);

			hBox.setAlignment(Pos.CENTER);
			hBox.setPadding(new Insets(5));
			hBox.setSpacing(5);

			mainMenuFromGuestSearch = new Button("Main Menu");

			hBox.getChildren().add(mainMenuFromGuestSearch);

			ScrollPane scrollPane = new ScrollPane(guestSearch);
			scrollPane.setPrefSize(550, 402);
			setTop(scrollPane);
			setCenter(hBox);
			setPadding(new Insets(5, 5, 5, 5));
			setStyle("-fx-background-color: darkslategrey");

		}
	}

	public class BorrowedDescriptionPane extends BorderPane {

		@SuppressWarnings("unchecked")
		public BorrowedDescriptionPane() {

			TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
			titleColumn.setMinWidth(180);
			titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

			TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
			authorColumn.setMinWidth(123);
			authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

			TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
			isbnColumn.setMinWidth(60);
			isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

			TableColumn<Book, String> categoryColumn = new TableColumn<>("Category");
			categoryColumn.setMinWidth(60);
			categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

			TableColumn<Book, String> bookIdColumn = new TableColumn<>("Book ID");
			bookIdColumn.setMinWidth(20);
			bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));

			TableColumn<Book, String> dateColumn = new TableColumn<>("Date Borrowed");
			dateColumn.setMinWidth(200);
			dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
			
			TableColumn<Book, String> dueDateColumn = new TableColumn<>("Due Date");
			dueDateColumn.setMinWidth(200);
			dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateDue"));

			borrowedTable.getColumns().addAll(titleColumn, authorColumn, isbnColumn, categoryColumn, bookIdColumn,
					dateColumn, dueDateColumn);

			borrowedTable.setItems(borrowedBooks);
			returnButton = new Button("Return");
			userProfileViewWindow = new Button("View Profile");

			HBox hBox = new HBox();
			hBox.setPadding(new Insets(5));
			hBox.setSpacing(5);

			hBox.getChildren().addAll(returnButton, userProfileViewWindow);

			ScrollPane scrollPane = new ScrollPane(borrowedTable);
			scrollPane.setPrefSize(730, 400);
			setTop(scrollPane);
			setCenter(hBox);
			setPadding(new Insets(5, 5, 5, 5));
			setStyle("-fx-background-color: darkslategrey");

		}

	}

	public class DescriptionPane extends BorderPane {

		@SuppressWarnings("unchecked")
		public DescriptionPane() {

			TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
			titleColumn.setMinWidth(180);
			titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

			TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
			authorColumn.setMinWidth(180);
			authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

			TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
			isbnColumn.setMinWidth(60);
			isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

			TableColumn<Book, String> categoryColumn = new TableColumn<>("Category");
			categoryColumn.setMinWidth(120);
			categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
			TableColumn<Book, String> bookIdColumn = new TableColumn<>("Book ID");
			bookIdColumn.setMinWidth(20);
			bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
			TableColumn<Book, String> all = new TableColumn<>("User");
			all.setMinWidth(100);
			all.setCellValueFactory(new PropertyValueFactory<>("user"));
			
			TableColumn<Book, String> datesColumn = new TableColumn<>("Due Date");
			datesColumn.setMinWidth(200);
			datesColumn.setCellValueFactory(new PropertyValueFactory<>("dateDue"));
			

			table.setItems(books);

			table.getColumns().addAll(titleColumn, authorColumn, isbnColumn, categoryColumn, bookIdColumn, all, datesColumn);

			titleTextField = new TextField();
			titleTextField.setPromptText("Title");
			titleTextField.setMaxWidth(180);

			authorTextField = new TextField();
			authorTextField.setPromptText("Author");
			authorTextField.setMaxWidth(123);

			isbnTextField = new TextField();
			isbnTextField.setPromptText("ISBN");
			isbnTextField.setMaxWidth(60);

			categoryTextField = new TextField();
			categoryTextField.setPromptText("Category");
			categoryTextField.setMaxWidth(90);

			copiesTextField = new TextField();
			copiesTextField.setPromptText("# of Copies");
			copiesTextField.setMaxWidth(90);

			Button addButton = new Button("Add");
			addButton.setOnAction(e -> addButtonClicked());
			Button deleteButton = new Button("Delete");
			deleteButton.setOnAction(e -> deleteButtonClicked());

			HBox hBox = new HBox();
			hBox.setPadding(new Insets(5));
			hBox.setSpacing(5);

			hBox.getChildren().addAll(titleTextField, authorTextField, isbnTextField, categoryTextField,
					copiesTextField, addButton, deleteButton);

			ScrollPane scrollPane = new ScrollPane(table);

			scrollPane.setPrefSize(600, 200);
			setCenter(scrollPane);
			setBottom(hBox);
			setPadding(new Insets(5, 5, 5, 5));
			setStyle("-fx-background-color: darkslategrey");

		}

	}

	public class DescriptionPaneUser extends BorderPane {

		@SuppressWarnings("unchecked")
		public DescriptionPaneUser() {

			TableColumn<User, String> firstNameCol = new TableColumn<>("First Name");
			firstNameCol.setMinWidth(100);
			firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

			TableColumn<User, String> lastNameCol = new TableColumn<>("Last Name");
			lastNameCol.setMinWidth(100);
			lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

			TableColumn<User, String> ageCol = new TableColumn<>("Age");
			ageCol.setMinWidth(100);
			ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

			TableColumn<User, String> streetNumberCol = new TableColumn<>("Street Number");
			streetNumberCol.setMinWidth(100);
			streetNumberCol.setCellValueFactory(new PropertyValueFactory<>("streetNumber"));

			TableColumn<User, String> streetNameCol = new TableColumn<>("Street Name");
			streetNameCol.setMinWidth(100);
			streetNameCol.setCellValueFactory(new PropertyValueFactory<>("streetName"));

			TableColumn<User, String> townCol = new TableColumn<>("Town");
			townCol.setMinWidth(100);
			townCol.setCellValueFactory(new PropertyValueFactory<>("town"));

			TableColumn<User, String> stateCol = new TableColumn<>("State");
			stateCol.setMinWidth(100);
			stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));

			TableColumn<User, String> zipCodeCol = new TableColumn<>("Zip Code");
			zipCodeCol.setMinWidth(100);
			zipCodeCol.setCellValueFactory(new PropertyValueFactory<>("zipCode"));

			TableColumn<User, String> userNameCol = new TableColumn<>("User name");
			userNameCol.setMinWidth(100);
			userNameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

			TableColumn<User, String> passwordCol = new TableColumn<>("Password");
			passwordCol.setMinWidth(100);
			passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));

			// FilteredList<User> flUser = new FilteredList<User>(users, p -> true);
			userTable = new TableView<>();

			userTable.setItems(users);

			userTable.getColumns().addAll(firstNameCol, lastNameCol, ageCol, streetNumberCol, streetNameCol, townCol,
					stateCol, zipCodeCol, userNameCol, passwordCol);

			ScrollPane scrollPane = new ScrollPane(userTable);

			scrollPane.setPrefSize(600, 200);
			setCenter(scrollPane);

			setPadding(new Insets(5, 5, 5, 5));
			setStyle("-fx-background-color: darkslategrey");

		}

	}

	private HBox getGuestLogin() {
		HBox hBox = new HBox(15);
		Text guestUsernameText = new Text(20, 20, "");
		guestTextField = new TextField("Guest");
		guestUsernameText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 15));

		hBox.getChildren().addAll(guestUsernameText, guestTextField);
		hBox.setAlignment(Pos.CENTER);
		return hBox;
	}


	private VBox getGuestLoginVBox() {
		backToMenuFromGuest = new Button("Log Out");
		guestLogin = new Button("Login");

		VBox vBox = new VBox(15);
		vBox.setAlignment(Pos.CENTER);
		vBox.setPadding(new Insets(15, 15, 15, 15));



		vBox.getChildren().addAll(guestLogin, backToMenuFromGuest);

		return vBox;

	}

	private HBox getMainMenuHBox() {

		HBox hBox = new HBox(15);
		hBox.setAlignment(Pos.BOTTOM_LEFT);

		Image image = new Image("finalimage.png", 750, 300, false, false);
		ImageView mainMenuLogo = new ImageView(image);

		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(mainMenuLogo, hBox);

		HBox root = new HBox();
		root.getChildren().add(stackPane);
		return root;

	}

	private VBox getMainMenuVBox() {
		user = new Button("         User        ");
		user.setMinHeight(30);
		admin = new Button(" Administrator ");
		admin.setMinHeight(30);
		guest = new Button("       Guest        ");
		guest.setMinHeight(30);
		VBox vBox = new VBox(15);
		vBox.setAlignment(Pos.TOP_CENTER);
		vBox.setPadding(new Insets(15, 15, 15, 15));

		vBox.setStyle("-fx-background-color: darkslategrey");

		vBox.getChildren().addAll(user, admin, guest);

		return vBox;

	}


	public void addButtonClicked() throws NumberFormatException {

		book.setTitle(titleTextField.getText());
		book.setAuthor(authorTextField.getText());
		book.setIsbn(isbnTextField.getText());
		book.setCategory(categoryTextField.getText());

		titleTextField.clear();
		authorTextField.clear();
		isbnTextField.clear();
		categoryTextField.clear();


	try {
		for (int i = 0; i < Integer.parseInt(copiesTextField.getText()); i++) {

			Book b = book.clone();
			library.addBook(b);
			table.getItems().add(b);
		
		}
	} catch (NumberFormatException ex) {
		AlertBox.display("Error", "Please fill out all text fields");
	}

	copiesTextField.clear();
	}

	public void borrowButtonClicked(int woo) throws ClassNotFoundException, NullPointerException, IOException {


		borrowedTable.getItems().add(tableSearch.getSelectionModel().getSelectedItem());
		

		library.getUsers().get(woo).borrowBooks(tableSearch.getSelectionModel().getSelectedItem());
	}

	public boolean isBookBorrowed() {

		for (int j = 0; j < library.getUsers().size(); j++) {
			for (int i = 0; i < library.getUsers().get(j).getBorrowedBooks().size(); i++) {
				if (library.getUsers().get(j).getBorrowedBooks().get(i).getBookId() == tableSearch.getSelectionModel()
						.getSelectedItem().getBookId()) {

					return true;
				}
			}
		}
		return false;
	}

	public void returnButtonClicked(int x) throws NullPointerException {

		ObservableList<Book> bbookSelected, bAllBooks;
		bAllBooks = borrowedTable.getItems();
		bbookSelected = borrowedTable.getSelectionModel().getSelectedItems();

		for (Book b : bbookSelected) {
				b.setUser(null);
				b.setAvailable(true);
			library.getUsers().get(x).returnBooks(b);

		}

		try {
			
			borrowedTable.getSelectionModel().getSelectedItem().setAvailable(true);

			library.getUsers().get(x).returnBooks(borrowedTable.getSelectionModel().getSelectedItem());

			AlertBox.display("Alert", "Book Returned Succesfully!");

		}

		catch (NullPointerException e) {
			AlertBox.display("Alert", "No books to Return!");

		}

		// library.addBook(borrowedTable.getSelectionModel().getSelectedItem());

		bbookSelected.forEach(bAllBooks::remove);
		tableSearch.refresh();
		table.refresh();
		borrowedTable.refresh();
	}

	public void deleteButtonClicked() {
		ObservableList<Book> bookSelected, allBooks;
		allBooks = table.getItems();
		bookSelected = table.getSelectionModel().getSelectedItems();
		library.removeBook(table.getSelectionModel().getSelectedItem());
		bookSelected.forEach(allBooks::remove);

	}

	public void deleteUserClicked() {
		ObservableList<User> userSelected, allUsers;
		allUsers = userTable.getItems();
		userSelected = userTable.getSelectionModel().getSelectedItems();

		library.removeUser(userTable.getSelectionModel().getSelectedItem());
		userSelected.forEach(allUsers::remove);
	}

	public void closeProgram() throws IOException, ClassNotFoundException {

	
		MyLibrary.printFile("users", "books", "bookId", library);

		window.close();
	}
	
	public String decyptPassword() throws FileNotFoundException, IOException, ClassNotFoundException {
		StringBuffer a = new StringBuffer();
		File userFile = new File("adminPassword.dat");
		String b = null;
	try (	ObjectInputStream userInput = new ObjectInputStream(new FileInputStream(userFile));
			
			){
		for(int i = 0; i < 1; i++) {
			a = (StringBuffer) userInput.readObject();
			b = a.toString();
			userInput.close();
		}
	}
	
		String message = b;
		String decryptedMessage = "";
		int key = 5;
		char ch;
		

		for(int i = 0; i < message.length(); ++i){
			ch = message.charAt(i);
			
			if(ch >= 'a' && ch <= 'z'){
	            ch = (char)(ch - key);
	            
	            if(ch < 'a'){
	                ch = (char)(ch + 'z' - 'a' + 1);
	            }
	            
	            decryptedMessage += ch;
	        }
	        else if(ch >= 'A' && ch <= 'Z'){
	            ch = (char)(ch - key);
	            
	            if(ch < 'A'){
	                ch = (char)(ch + 'Z' - 'A' + 1);
	            }
	            
	            decryptedMessage += ch;
	        }
	        else {
	        	decryptedMessage += ch;
	        }
		}
	
		
		return decryptedMessage;
			
	}
	

	public MyLibrary startProgram() throws IOException, ClassNotFoundException {
		MyLibrary.printPassword("adminPassword");
		File a = new File("users.dat");
		File b = new File("books.dat");
		File c = new File("bookId.dat");
		User tempUser = new User();
		Book tempBook = new Book();
				if (a.exists() && b.exists() && c.exists()) {
					
					library = MyLibrary.readFile("users.dat", "books.dat", "bookId.dat");
			
			return library;

		}

		else {
			library.addBook(tempBook);
			library.addUser(tempUser);
			// library.setBookId(0);
		}

	

		return library;

	}

	public void registerUser() {
		User user = new User();
		user.setFirstName(firstNameTf.getText());
		user.setLastName(lastNameTf.getText());
		user.setAge(ageTf.getText());
		user.setStreetNumber(streetNumberTf.getText());
		user.setStreetName(streetNameTf.getText());
		user.setTown(townTf.getText());
		user.setState(stateTf.getText());
		user.setZipCode(zipCodeTf.getText());
		user.setUsername(registerUsernameText.getText());
		user.setPassword(registerPasswordText.getText());

		library.addUser(user);
		userTable.getItems().add(user);

	}

	public boolean checkUserLogin() {

		String[] userConfirmation = new String[library.getUsers().size()];
		String[] userPassword = new String[library.getUsers().size()];
		boolean found = false;

		for (int i = 0; i < library.getUsers().size(); i++) {
			userConfirmation[i] = library.getUsers().get(i).getUsername();
			userPassword[i] = library.getUsers().get(i).getPassword();
			if (userNameTextField.getText().toLowerCase().contentEquals(userConfirmation[i].toString().toLowerCase())
					&& passWordTextField.getText().contentEquals(userPassword[i].toString())) {
				found = true;

			}
		}

		return found;

	}
	public static void main(String[] args) {

	 Application.launch(args); 
	 //circle is the monk
	}


}
