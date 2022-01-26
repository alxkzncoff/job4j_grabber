package ru.job4j.gc;

public class User {

    private String name;
    private int age;
    private String city;

    public User() {
    }

    public User(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String isCity() {
        return city;
    }

    public void setAdult(String city) {
        this.city = city;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %s %d %s%n", name, age, city);
    }
}
