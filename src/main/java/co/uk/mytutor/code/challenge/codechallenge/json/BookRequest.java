package co.uk.mytutor.code.challenge.codechallenge.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Samuel Catalano
 * @since Feb 28, 2020
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest implements Serializable {

    private String  type;
    private Integer quantity;
}