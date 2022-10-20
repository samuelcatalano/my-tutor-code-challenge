package co.uk.mytutor.code.challenge.codechallenge.service;

import co.uk.mytutor.code.challenge.codechallenge.enums.BookType;
import co.uk.mytutor.code.challenge.codechallenge.exception.BookNotFoundException;
import co.uk.mytutor.code.challenge.codechallenge.exception.BookOutOfStockException;
import co.uk.mytutor.code.challenge.codechallenge.model.Book;
import co.uk.mytutor.code.challenge.codechallenge.service.base.BookBaseService;
import co.uk.mytutor.code.challenge.codechallenge.utils.RoundUpValueUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author Samuel Catalano
 * @since Feb 28, 2020
 */

@Service
public class BookService implements BookBaseService<Book> {

    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);

    public static LinkedHashMap<BookType, Book> books;
    public static Double budget = 500d; // initial budget

    /**
     * Construtor.
     */
    public BookService() {
        books = setUpBooks();;
    }

    /**
     * Sell a book
     * @param type the type of the {@link Book}
     * @param quantity the quantity of the {@link Book}
     * @return a book sold
     * @throws BookOutOfStockException
     * @throws BookNotFoundException
     */
    public Book sellBook(final BookType type, final Integer quantity) throws BookOutOfStockException, BookNotFoundException {
        final Book book = books.get(type);
        if (book == null) {
            LOG.error("Sorry, this book doesn't exist!");
            throw new BookNotFoundException("Sorry, this book doesn't exist!");
        }
        if (book.getQuantity() < quantity) {
            LOG.error("Sorry, we are out of stock!");
            throw new BookOutOfStockException("Sorry, we are out of stock!");
        }

        book.setCopiesSold(book.getCopiesSold() + quantity);
        book.setQuantity(book.getQuantity() - quantity);
        book.setProfit(RoundUpValueUtils.roundUpValue(((book.getPrice() * quantity) * 0.7)));

        books.put(type, book);

        budget = budget + book.getProfit();
        budget = RoundUpValueUtils.roundUpValue(budget);

        if (this.checkNeedMoreBooks(book)) {
            LOG.info("Need to buy new books!");
            this.buyBooks(book.getType(), 10 - book.getQuantity()); // buy new books to complete 10
        }

        return book;
    }

    /**
     * Buy new books.
     * @param type the type of the book
     * @param quantity the quantity to buy
     */
    public void buyBooks(final BookType type, final Integer quantity) throws BookNotFoundException {
        final Book book = books.get(type);
        if (book == null) {
            LOG.error("Sorry, this book doesn't exist!");
            throw new BookNotFoundException("Sorry, this book doesn't exist!");
        }

        final Double price = book.getPrice();
        final Double finalPrice = ((price * 0.7) * quantity); // getting just the profit value
        budget = budget - finalPrice;

        book.setQuantity(book.getQuantity() + quantity);
        books.put(type, book);
    }

    /**
     * Returns the map with the books loaded.
     * @return map with the books loaded
     */
    private LinkedHashMap<BookType, Book> setUpBooks() {
        books = new LinkedHashMap<>();

        final Book a = Book.builder().type(BookType.A).price(25d).quantity(10).copiesSold(0).profit(0d).build();
        final Book b = Book.builder().type(BookType.B).price(20d).quantity(10).copiesSold(0).profit(0d).build();
        final Book c = Book.builder().type(BookType.C).price(23d).quantity(10).copiesSold(0).profit(0d).build();
        final Book d = Book.builder().type(BookType.D).price(30d).quantity(10).copiesSold(0).profit(0d).build();
        final Book e = Book.builder().type(BookType.E).price(27d).quantity(10).copiesSold(0).profit(0d).build();

        books.put(BookType.A, a);
        books.put(BookType.B, b);
        books.put(BookType.C, c);
        books.put(BookType.D, d);
        books.put(BookType.E, e);

        return books;
    }

    /**
     * Check if is it necessary to buy more books.
     * @param book the book that was sold
     * @return <code>true</code> or <code>false</code>
     */
    private boolean checkNeedMoreBooks(final Book book) {
        return book.getQuantity() <= 3;
    }
}