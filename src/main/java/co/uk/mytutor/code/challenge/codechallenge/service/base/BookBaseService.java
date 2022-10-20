package co.uk.mytutor.code.challenge.codechallenge.service.base;

import co.uk.mytutor.code.challenge.codechallenge.enums.BookType;
import co.uk.mytutor.code.challenge.codechallenge.exception.BookNotFoundException;
import co.uk.mytutor.code.challenge.codechallenge.exception.BookOutOfStockException;
import co.uk.mytutor.code.challenge.codechallenge.model.base.BaseEntity;

/**
 * @author Samuel Catalano
 * @since Feb 28, 2020
 * @param <T>
 */
public interface BookBaseService<T extends BaseEntity> {

    T sellBook(final BookType type, final Integer quantity) throws BookOutOfStockException, BookNotFoundException;

    void buyBooks(final BookType type, final Integer quantity) throws BookNotFoundException;
}
