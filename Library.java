import javax.swing.*;
import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.*;

public class Library {
    
    public static void printHelp() {
        
        //List of commands
        System.out.println("help - Lists all commands");
        // add book
        System.out.println("add book - Adds a book to the library");

        System.out.println("list books - Lists all the books in the library");

        System.out.println("remove book - Removes a book from the library");

        System.out.println("rename book - Renames a book from the library");

        System.out.println("open book - Opens a book from the library");

        System.out.println("clear - clears the page");
                     
    }

    
    
    public static void main(String[] args){

        
        List<String> bookTitles=new ArrayList<String>();  // Creates an empty list of book titles

        List<String> bookText=new ArrayList<String>();  // Creates an empty list of actual books

        System.out.println("Hello welcome to your Library");  // Says on the users screen "Hello welcome to your library"

        File folder = new File("open_book_books");
        File[] listOfFiles = folder.listFiles();

        for (int i =1; i<listOfFiles.length;i++ ){
            if (listOfFiles[i].isFile()){
                bookTitles.add(listOfFiles[i].getName());
            }
        }

        Scanner myScanner = new Scanner(System.in);  // Creates a new scanner to get input from the user. Scanner myScanner makes a name for the scanner then the = new Scanner(System.in); makes the actual scanner
        
        String input = " "; // input is the variable for the user's console input

        int bookNum;

        while(!input.equals("exit")){
            input = myScanner.nextLine(); // Reads the input from the user into program

            input = input.toLowerCase();

            switch (input){

                case "help":
                    printHelp();
                    break;

                case "remove book":
                    //removes book

                    if(bookTitles.isEmpty()){
                         System.out.println("No books in the library!");
                        break;
                    }
                    
                    System.out.println("Which book do you want to remove?");

                    bookNum = 0;
                    for(String book: bookTitles){
                        System.out.println(bookNum + " - " + book);
                        bookNum = bookNum + 1;
                    }

                    String selection = myScanner.nextLine();

                    if (!bookTitles.contains(selection)) {

                        System.out.println("Book does not exist");

                        break;
                    }

                    bookTitles.removeIf(book -> book.equals(selection));

                    String filePath = "open_book_books/" + selection + ".txt";

                    File file = new File(filePath);
                    if (file.delete()) {
                        System.out.println("Deleted File : " + file.getName());
                    } else {
                        System.out.println("Failed to delete file");
                    }



                    break;

                case "rename book":
                    //renames book

                   if(bookTitles.isEmpty()){
                        System.out.println("No books in the library!");
                        break;
                    }

                    System.out.println("Which book do you want to rename?");

                    bookNum = 0;
                    for(String book: bookTitles){
                        System.out.println(bookNum + " - " + book);
                        bookNum = bookNum + 1;
                    }

                    String oldName = myScanner.nextLine();

                    if(bookTitles.contains(oldName)){
                        System.out.println("What would you like to rename the book to?");
                        String newName = myScanner.nextLine();
                        int i = bookTitles.indexOf(oldName);
                        bookTitles.set(i, oldName);
                        System.out.println("You have renamed the following book -");
                        System.out.println("* Index=" + i + " From - " + oldName + ", To - " + newName);
                        break;

                    } else {
                        System.out.println("Book does not exist");
                    }

                    break;
                    
                case "open book":
                        //opens book
                        
                    if(bookTitles.isEmpty()){
                        System.out.println("No books in the library!");
                        break;  // Leave the case
                        
                    }
                    
                    System.out.println("Which book do you want to open? (enter the number)"); // Ask them what book they want to open and look

                    bookNum = 0; // Here we are changing a variable and its equal to zero

                    for(String book: bookTitles){
                        System.out.println(bookNum + " - " + book);
                        bookNum = bookNum + 1;
                    } // Loop through each book title in the list of book titles and when we run it, it will plus one each time to book num. It will also print each book title

                    String openBook = myScanner.nextLine(); //user input for choosing book

                    try {
                        int intValue = Integer.parseInt(openBook); // checks if user input is a number

                        if ((0>=intValue) && (intValue<=bookTitles.size())) {
                            System.out.println("Opening book " + bookTitles.get(intValue));
                            
                            // open a book
                            File currentBook = new File("open_book_books/"+bookTitles.get(intValue));
                            Scanner bookScanner = new Scanner(currentBook);
                            while(bookScanner.hasNextLine()){
                                System.out.println(bookScanner.nextLine());
                            }

                        } else {
                            System.out.println("There is no book at position " + intValue);
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Please enter the number on the side of the book title.");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }


                    break; // Stop the code for open book
                    
                case "list books": // It makes a case for list books so that we can put code in the case to list all the books

                    if(bookTitles.isEmpty()){ // If book titles is empy
                        System.out.println("No books in the library!"); //say "No books in the library
                    } // closing brackets

                    bookNum = 0; // The variable bookNum is 0
                    for(String book: bookTitles){ // This code will string bookTitles
                        System.out.println(bookNum + " - " + book); // This will put text on the users screen and the text will say the book number - + the book
                        bookNum = bookNum + 1; // This says the book number is equal to the book number + 1
                    }



                    break; // Stop the code for list books

                case "add book": // It makes a case for add book so that we can put code in the case to add some books

                    System.out.println("Enter your book"); // It asks the user what the title of their book is
                    String book = myScanner.nextLine(); // Here it strings the book with my scanner

                    bookTitles.add(book+".txt"); //This adds the book
                    System.out.println("Adding book"); //This says to the user "Adding book"
                    
                    // TODO code application logic here
                    String filename = "/Users/blaisepisano/IdeaProjects/Book_Library/open_book_books/" + book + ".txt";
                    FileWriter fstream;

                    try {
                        fstream = new FileWriter(filename);
                        BufferedWriter out = new BufferedWriter(fstream);
                        out.write("");
                        out.newLine();                                  
                        out.flush();
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();          
                    }
                    
                    break;
                    
                case "clear":
                    
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    
                    break;

                default:
                    System.out.println("-bash: " +input+ " Command not found");
                    break;


            }
        }
    }

}
