package ru.job4j.ood.lsp.park;

public class Truck implements Vehicle {

    private final int size;

    public Truck(int size) {
        validate(size);
        this.size = size;
    }

    private void validate(int size) {
        if (size < 2) {
            throw new IllegalArgumentException("Size of truck must be greater then 1!");
        }
    }

    @Override
    public int getSize() {
        return size;
    }
}
