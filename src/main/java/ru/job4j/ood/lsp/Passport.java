package ru.job4j.ood.lsp;

public class Passport {

    protected Person personData;

    public Passport(Person personData) {
        validate(personData);
        this.personData = personData;
    }

    protected void validate(Person personData) {
        if (personData.getAge() < 14) {
            throw new IllegalArgumentException("Person age less then 14!");
        }
    }

    public Person getPersonData() {
        return personData;
    }

    public void setPersonData(Person personData) {
        validate(personData);
        this.personData = personData;
    }
}
