package proj6;
/*
<p>
	Description: This is the Book class. You can create a new book
	@Author: Nicholas Tromboukis
</p>
 */

public class Book {
	private String author;
	private String title;
	private String status;


	/*
		Param constructor. Stores references to the values passed through as parameters.
	 */
	public Book(String auth, String str, String stat) {
		author = auth;
		title = str;
		status = stat;
	}


	/*
		The setStatus method is a mutator method which alters the value stored in status.
	 */
	public void setStatus(String str) {
		this.status = new String(str);
	}


	/*
		The getTitle method returns a reference to a String object where the title of the book is stored.

		@returns String object
	 */
	public String getTitle() {

		return title;
	}


	/*
		The getStatus method returns a reference to a String object where the status of the book is stored.

		@returns String object
	 */
	public String getStatus() {
		return status;
	}


	/*
		The toString method returns a reference to a String object which concatenates the values stored in the
		initial variables to display a books title, author, and status.

		@returns String object
	 */
	public String toString() {
		String str = title + " by " + author + " is currently " + status;
		return str;
	}

}
