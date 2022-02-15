package ru.job4j.ood.lsp;

class WoodHouse extends House {

    public WoodHouse(int workers) {
        super(workers);
    }

    public void build(int workers) {
        if (workers < 8) {
            throw new IllegalArgumentException("Not enough workers!");
        }
        System.out.println("House built.");
    }

    public static void main(String[] args) {
        House woodHouse = new WoodHouse(7);
        woodHouse.build(6);
    }
}
