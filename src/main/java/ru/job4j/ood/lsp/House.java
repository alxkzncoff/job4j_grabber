package ru.job4j.ood.lsp;

public class House {

    protected int workers;

    public House(int workers) {
        this.workers = workers;
    }

    public void build(int workers) {
        if (workers < 5) {
            throw new IllegalArgumentException("Not enough workers!");
        }
        System.out.println("House built.");
    }
}


