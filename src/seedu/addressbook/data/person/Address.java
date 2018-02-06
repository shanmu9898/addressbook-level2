package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street,some unit,some postalcode";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

    public final String value;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        this.isPrivate = isPrivate;
        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        String[] splitFormOfAddress = trimmedAddress.split(",");
        Block blockAddress = new Block(" ");
        Street streetAddess = new Street(" ");
        Unit unitAddress = new Unit(" ");
        PostalCode postalCode = new PostalCode("1234");

        if(splitFormOfAddress.length == 4 ){
             blockAddress = new Block(splitFormOfAddress[0]);
             streetAddess = new Street(splitFormOfAddress[1]);
             unitAddress = new Unit(splitFormOfAddress[2]);
             postalCode = new PostalCode(splitFormOfAddress[3].trim());

        }else if (splitFormOfAddress.length ==3){
             blockAddress = new Block(splitFormOfAddress[0]);
             streetAddess = new Street(splitFormOfAddress[1]);
             unitAddress = new Unit(splitFormOfAddress[2]);
        }else if (splitFormOfAddress.length == 2){
             blockAddress = new Block(splitFormOfAddress[0]);
             streetAddess = new Street(splitFormOfAddress[1]);
        }else if (splitFormOfAddress.length == 1){
             blockAddress = new Block(splitFormOfAddress[0]);
        }else{

        }

        this.value = "Block Address:- " + blockAddress.getBlockNumber()+ " Street Address:- "+ streetAddess.getStreetAddress()
                       + " Unit Address:- "+ unitAddress.getUnitAddress() + " Postalcode:- "+ postalCode.getPostalCode();
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
