package co.uk.mytutor.code.challenge.codechallenge.enums;

/**
 * @author Samuel Catalano
 * @since Feb 28, 2020
 */
public enum BookType {
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E");

    private String type;

    BookType(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}