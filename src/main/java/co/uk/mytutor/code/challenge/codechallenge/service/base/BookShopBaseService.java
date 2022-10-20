package co.uk.mytutor.code.challenge.codechallenge.service.base;

import co.uk.mytutor.code.challenge.codechallenge.exception.BookNotFoundException;
import co.uk.mytutor.code.challenge.codechallenge.model.base.BaseEntity;

/**
 * @author Samuel Catalano
 * @since Feb 28, 2020
 * @param <T>
 */
public interface BookShopBaseService<T extends BaseEntity> {

    T getSellingReport() throws BookNotFoundException;
}