package co.uk.mytutor.code.challenge.codechallenge.controller;

import co.uk.mytutor.code.challenge.codechallenge.exception.BookNotFoundException;
import co.uk.mytutor.code.challenge.codechallenge.model.BookShop;
import co.uk.mytutor.code.challenge.codechallenge.service.BookShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Samuel Catalano
 * @since Feb 28, 2020
 */

@RestController
@RequestMapping(value = "/mytutor/book-shop")
public class BookShopController {

    @Autowired
    private BookShopService bookShopService;

    @GetMapping(value = "/report")
    public String sellBook() {
        try {
            final BookShop bookShop = this.bookShopService.getSellingReport();
            return bookShop.toString();
        } catch (BookNotFoundException e) {
            return "";
        }
    }
}