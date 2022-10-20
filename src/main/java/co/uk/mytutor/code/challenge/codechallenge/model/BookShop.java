package co.uk.mytutor.code.challenge.codechallenge.model;

import co.uk.mytutor.code.challenge.codechallenge.model.base.BaseEntity;
import lombok.*;

import java.util.List;

/**
 * @author Samuel Catalano
 * @since Feb 28, 2020
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BookShop extends BaseEntity {

    private Double ballance;
    private List<Book> books;

    @Override
    public String toString() {
        final String budget = "MyTutor Bookshop Balance: £" + ballance;
        final String books;
        final StringBuilder booksBuilder = new StringBuilder();

        for (final Book book : this.books) {
            booksBuilder.append("Book " + book.getType().toString())
                    .append(" | ")
                    .append(book.getCopiesSold())
                    .append(" Copies Sold")
                    .append(" | ")
                    .append("£")
                    .append(book.getProfit())
                    .append(" Total Profit \n");
        }

        books = booksBuilder.toString().trim();
        return budget + "\n" + books;
    }
}