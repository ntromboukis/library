package proj6;
/*
<p>
	Description: This is the application class for project6. In this application you can create new Book objects and Library objects.
	You can scan through a text file to get the title, author, and status of the books.
	@Author: Nicholas Tromboukis
</p>
 */

import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Proj6App {
	public static void main(String[] args) throws Exception {
		
		Scanner fileScan = new Scanner(new File("enter path to libraryHoldings.txt here"));
		String author, title, status = new String();
		Book nBook;
		Library theLibrary = new Library();
		
		String[] choices = {"Quit", "Show Books", "Mark Book as Overdue", "Return Book", "Take out Book"};
		String[] statChoices = {"Overdue", "Out", "Available"};
		
		
		int numBooks = fileScan.nextInt();
		System.out.println("There are " + numBooks + " books");
		fileScan.nextLine();
		
		for(int i=0; i<numBooks; i++) {
			title = fileScan.nextLine();
			author = fileScan.nextLine();
			status = fileScan.nextLine();
			nBook = new Book(author, title, status);
			theLibrary.insertBook(nBook);
		}
		
		fileScan.close();
		
		System.out.println(theLibrary.toString());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime()));
		cal.setTime(new Date());

		boolean running = true;
		while(running == true) {
			int choice = JOptionPane.showOptionDialog(
					null,
					"Enter your choice...",
					"The Library's Main Menu",
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null, choices, choices[4]);

			if (choice == 0) {
				JOptionPane.showMessageDialog(null, "Thank's for visiting the Library");
				running = false;
			} else if (choice == 1) {
				int pos = JOptionPane.showOptionDialog(
						null,
						"Enter your choice...",
						"Availability",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null, statChoices, statChoices[2]);
				JOptionPane.showMessageDialog(null, theLibrary.findByStatus(statChoices[pos]));
			} else if (choice == 2) {
				String bookTitle = JOptionPane.showInputDialog("Title of book you want to mark as overdue");
				if (theLibrary.markOverDue(bookTitle)) {
					JOptionPane.showMessageDialog(null, bookTitle + " was marked overdue");
				} else {
					JOptionPane.showMessageDialog(null, bookTitle + " can't be marked overdue");
				}
			} else if (choice == 3) {
				String bookTitle = JOptionPane.showInputDialog("Title of book you want to return");
				if (theLibrary.returnByTitle(bookTitle)) {
					JOptionPane.showMessageDialog(null, bookTitle + " was returned and is available to take out!");
				} else {
					int reply = JOptionPane.showConfirmDialog(null, bookTitle + " is either not out, or doesn't exist.\n" +
							" Would you like to see the books that are out?", "Oops", JOptionPane.YES_NO_OPTION);
					if (reply == 0) {
						int nestReply = JOptionPane.showConfirmDialog(null, theLibrary.findByStatus(statChoices[1]) + "\n" +
								"Would you like to see the overdue books?", "Out", JOptionPane.YES_NO_OPTION);
						if(nestReply == 0) {
							JOptionPane.showMessageDialog(null, theLibrary.findByStatus(statChoices[0]));
						}
					}
				}
			} else if (choice == 4) {
				String bookTitle = JOptionPane.showInputDialog("Title of book you want to take out");
				if (theLibrary.checkoutByTitle(bookTitle) == true) {
					cal.add(Calendar.DATE, 7);
					String output = dateFormat.format(cal.getTime());
					JOptionPane.showMessageDialog(null, bookTitle + " was taken out and must be returned in 7 days which is the " + output);
				} else {
					int reply = JOptionPane.showConfirmDialog(null, bookTitle + " is either already out, or doesn't exist.\n" +
							" Would you like to see the books that are available?", "Oops", JOptionPane.YES_NO_OPTION);
					if(reply == 0) {
						JOptionPane.showMessageDialog(null, theLibrary.findByStatus(statChoices[2]));
					}
				}
			}
		}

	}
}