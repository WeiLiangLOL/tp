package seedu.address.model.person;

/**
 * Represents a Person's remark in the address book.
 */
public class Remark {

    public final String value;

    /**
     * Creates a new remark.
     * @param remark The remark.
     */
    public Remark(String remark) {
        this.value = remark;
    }

    @Override
    public String toString() {
        return value;
    }
}
