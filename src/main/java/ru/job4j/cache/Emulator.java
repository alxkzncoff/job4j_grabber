package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter cached directory:");
        String dir = in.nextLine();
        System.out.println("Enter cached file:");
        String file = in.nextLine();
        DirFileCache dirFileCache = new DirFileCache(dir);
        System.out.println(dirFileCache.get(file));
    }
}
