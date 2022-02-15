package ru.job4j.ood.lsp;

public class NewVariantPassport extends Passport {

    public NewVariantPassport(Person personData) {
        super(personData);
    }

    @Override
    public void setPersonData(Person personData) {
        this.personData = personData;
    }

    public static void main(String[] args) {
        Passport newPassport = new NewVariantPassport(
                new Person("Alex", 15, "male")
        );

        newPassport.setPersonData(new Person("Alex", 13, "male"));
    }
}
