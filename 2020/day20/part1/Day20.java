package day20.part1;

import java.util.*;

import fileReader.FileReader;

public class Day20 {
    public static void main(String[] args) {
        String[] fileContents = FileReader.processFile();
        List<Tile> tileList = new ArrayList<Tile>();
        for (int i = 0; i < fileContents.length; i += 12) {
            Tile tile = new Tile(i, fileContents);
            tileList.add(tile);
        }
        long product = 1;
        for (Tile tile: tileList) {
            if (tile.isCorner(tileList)) {
                product *= tile.id;
                System.out.println(product);
            }
        }
    }
}
