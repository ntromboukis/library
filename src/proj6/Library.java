package proj6;
/*
<p>
	Description: This is the Library class. You can insert Book objects, checkout books by their title,
	find books by their title, return books by their title, mark books as overdue, and return a string of the books and
	their status in the library.
	@Author: Nicholas Tromboukis
</p>
 */


public class Library {
	private Book[] books;
	private int numBooks;

	/*
		Default constructor. Creates a new array of Book objects as well as sets numBooks to zero.
	 */
	public Library() {
		books = new Book[50];
		numBooks = 0;
	}

	/*
		The insertBook method inserts a book object in the array books and increments numBooks.
	 */
	public void insertBook(Book boo) {
		if(numBooks < books.length) {
			books[numBooks] = boo;
			numBooks++;
		}
	}

	/*
		The findByTitle method looks for a book object by its title and returns position of the book object
		in the books array. If no book is found by that title the method will return -1.

		@returns int position in array
	 */
	public int findByTitle(String str) {
		int found = -1;
		for(int i=0; i<numBooks; i++) {
			if(str.equals(books[i].getTitle())) {
				found = i;
			}
		}
		return found;
	}

	/*
		The checkoutByTitle method checks to see if the book's status is available. If the status is available, it will
		change the status to out and return true. Otherwise it will return false

		@returns boolean if book is checkout
	 */
	public boolean checkoutByTitle(String str) {
		boolean bool = false;
		int pos = this.findByTitle(str);
		if(pos == -1) {
			return bool;
		}
		String stat = books[pos].getStatus();
		if(pos != -1 && stat.equals("Available")) {
			books[pos].setStatus("Out");
			bool = true;
		}
		return bool;	
	}

	/*
		The returnByTitle method checks to see if a book object's status is Out or Overdue and wil set it to Available.
		If the status of the book object is Available the book cannot be returned so it will return false.

		@returns boolean if book object was returned or not.
	 */
	public boolean returnByTitle(String str) {
		boolean bool = false;
		int pos = this.findByTitle(str);
		if(pos == -1) {
			return bool;
		}
		String stat = books[pos].getStatus();
		if(pos != -1 && (stat.equals("Out") || stat.equals("Overdue"))) {
			books[pos].setStatus("Available");
			bool = true;
		}
		return bool;
	}

	/*
		The markOverDue method checks to see if the book object's status is Out and sets it to Overdue.

		@returns boolean value if the book's status was changed to Overdue or not
	 */
	public boolean markOverDue(String str) {
		boolean bool = false;
		int pos = this.findByTitle(str);
		String stat = books[pos].getStatus();
		if(pos != -1 && stat.equals("Out")) {
			books[pos].setStatus("Overdue");
			bool = true;
		}
		return bool;
	}

	/*
		The findByStatus method returns a String of book objects based on their status.

		@returns a String of all of the books with the same status.
	 */
	public String findByStatus(String str) {
		String find = new String();
		for(int i = 0; i<numBooks; i++) {
			if(str.equals(books[i].getStatus())) {
				find += books[i].toString() + "\n";
			}
		}
		return find;
	}

	/*
		The toString method returns a string of all of the book objects in the library.

		@returns a String of all of the book objects in the books array.
	 */
	public String toString() {
		String str = new String();
		for(int i=0; i<numBooks; i++) {
			str += books[i].toString() + "\n";
		}
		return str;
	}
}
