package seedu.addressbook.data.person;

public class PostalCode {

    int postalCode;

    public PostalCode(String postalCode){
        this.postalCode = Integer.parseInt(postalCode);
    }

    public int getPostalCode() {
        return postalCode;
    }
}
