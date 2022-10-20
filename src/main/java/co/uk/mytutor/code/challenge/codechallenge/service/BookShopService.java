package co.uk.mytutor.code.challenge.codechallenge.service;

import co.uk.mytutor.code.challenge.codechallenge.enums.BookType;
import co.uk.mytutor.code.challenge.codechallenge.exception.BookNotFoundException;
import co.uk.mytutor.code.challenge.codechallenge.model.Book;
import co.uk.mytutor.code.challenge.codechallenge.model.BookShop;
import co.uk.mytutor.code.challenge.codechallenge.service.base.BookShopBaseService;
import co.uk.mytutor.code.challenge.codechallenge.utils.RoundUpValueUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Samuel Catalano
 * @since Feb 28, 2020
 */

@Service
public class BookShopService implements BookShopBaseService<BookShop> {

    /**
     * Returns selling report.
     * @return selling report
     */
    public BookShop getSellingReport() throws BookNotFoundException {
        final BookShop bookShop = new BookShop();
        final LinkedHashMap<BookType, Book> booksMap = BookService.books;
        List<Book> books = new ArrayList<>();

        if (booksMap == null || booksMap.isEmpty()) {
            throw new BookNotFoundException("There are no books!");
        }

        for(Map.Entry<BookType, Book> entry : booksMap.entrySet()) {
            books.add(entry.getValue());
        }

        final Comparator<Book> compareByCopiesSoldAndProfit = Comparator
                .comparing(Book::getCopiesSold).reversed()
                .thenComparing(Book::getType);

        books = books.stream()
                .sorted(compareByCopiesSoldAndProfit)
                .collect(Collectors.toList());


        final Double profit = books.stream().mapToDouble(Book::getProfit).sum();
        bookShop.setBallance(BookService.budget + RoundUpValueUtils.roundUpValue(profit));
        bookShop.setBooks(books);

        return bookShop;
    }
}