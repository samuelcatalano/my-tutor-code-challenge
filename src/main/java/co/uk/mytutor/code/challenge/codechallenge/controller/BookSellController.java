package co.uk.mytutor.code.challenge.codechallenge.controller;

import co.uk.mytutor.code.challenge.codechallenge.enums.BookType;
import co.uk.mytutor.code.challenge.codechallenge.exception.BookNotFoundException;
import co.uk.mytutor.code.challenge.codechallenge.exception.BookOutOfStockException;
import co.uk.mytutor.code.challenge.codechallenge.json.BookRequest;
import co.uk.mytutor.code.challenge.codechallenge.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Samuel Catalano
 * @since Feb 28, 2020
 */

@RestController
@RequestMapping(value = "/mytutor/book-sell")
public class BookSellController {

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/sell")
    public ResponseEntity<?> sellBook(@RequestBody final BookRequest request) {
        try {
            this.bookService.sellBook(BookType.valueOf(request.getType()), request.getQuantity());
            return ResponseEntity.ok("Thank you for your purchase!");
        } catch (BookNotFoundException | BookOutOfStockException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(e.getMessage());
        }
    }
}