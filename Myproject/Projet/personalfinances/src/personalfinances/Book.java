
package personalfinances;

/**
 *
 * @author kalalajoel
 */
public class Book {
  
     /**
     * The Library of Congress number assigned to this book
     */
    private String isbn;
    /**
     * The title of the book
     */
    private String title;
    /**
     * The author(s) of the book
     */
    private String author;
    /**
     * The number of pages in the book
     */
    private int pages;
    /**
     * The retail price of the book
     */
    private double price;
    /**
     * The number of copies of this book in our inventory
     */
    private int numberOnShelf;

    /**
     * Parameterized Constructor for Book
     *
     * @param isbn the International Standard Book Number.
     * @param title The title for this book
     * @param author the author of this book
     * @param pages the number of pages in this book
     * @param price the retail price of this book
     * @param numberOnShelf the number copies of this book in the inventory
     */
    public Book(String isbn, String title, String author, int pages,
            double price, int numberOnShelf) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.price = price;
        this.numberOnShelf = numberOnShelf;
    }

    /**
     * A processing method that adds copies to the existing number of copies
     *  for this book.
     *
     * @param n the number of copies that we are adding to the shelf.
     */
    public void addToNumberOnShelf(int n) {
        if (n > 0) {
            numberOnShelf += n;
        }
    }

    /**
     * An Accessor for the property isbn.
     *
     * @return the isbn of this book
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Accessor for the property title.
     *
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Accessor for the property author.
     *
     * @return the author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Accessor for the property pages
     *
     * @return the number of pages n the book
     */
    public int getPages() {
        return pages;
    }

    /**
     * Accessor for the property price.
     *
     * @return the price of the book
     */
    public double getPrice() {
        return price;
    }

    /**
     * Accessor for the property numberOnShelf
     *
     * @return the number of this book on hand
     */
    public int getNumberOnShelf() {
        return numberOnShelf;
    }

    /**
     * The Java class Object has a toString() method so we override that one in
     * every class that we write so that we get the information out of the
     * objects in a predetermined format. This one outputs the Book data in the
     * same format that it is read in the inventory written out can be read in
     * the next day.
     *
     * @return a string with the isbn and title on one line, the author on a
     * second line, and the three numeric properties on a third line.
     */
    @Override
    public String toString() {
        return String.format("%13s  %s%n%s%n%d  %.2f  %d",
                isbn, title, author, pages, price, numberOnShelf);
    }

    /**
     * Unit Test for Book. Constructs, prints, adds and prints
     *
     * @param args
     */
    public static void main(String[] args) {

        Book book = new Book("1234567890123", "A Bogus Book", "Dot Matrix",
                324, 12.95, 5);
        System.out.println(book);
        book.addToNumberOnShelf(3);
        System.out.println(book);
    }
}
