package day20.part2;

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
        int[][] masterImage = getMasterImage(tileList);
        getRoughness(masterImage);
    }

    private static int[][] getMasterImage(List<Tile> tileList) {
        int l = (int) Math.sqrt(tileList.size());
        int[][] masterImage = new int[l*8][l*8];
        Set<Tile> usedTiles = new HashSet<Tile>();
        Tile currentTile = getStartingCorner(tileList);
        Tile rowBeginTile = getStartingCorner(tileList);
        currentTile.orient(tileList);
        int tileRow = 0;
        int tileColumn = 0;
        System.out.println("starting new row with " + currentTile.id);
        addTileToTileRow(currentTile, tileRow, tileColumn, masterImage);
        usedTiles.add(currentTile);
        boolean hasFoundLastInRow = false;
        while (usedTiles.size() < tileList.size()) {
            while (!hasFoundLastInRow) {
                hasFoundLastInRow = true;
                for (Tile tile: tileList) {
                    if (!usedTiles.contains(tile)) {
                        if (currentTile.isLeftOf(currentTile.grid, tile)) {
                            System.out.println("adding to row with " + tile.id);
                            usedTiles.add(tile);
                            currentTile = tile;
                            hasFoundLastInRow = false;
                            tileColumn++;
                            System.out.println(usedTiles.size());
                            System.out.println(tileList.size());
                            addTileToTileRow(currentTile, tileRow, tileColumn, masterImage);
                        }
                    }
                }
            }
            for (Tile tile: tileList) {
                if (!usedTiles.contains(tile)) {
                    if (rowBeginTile.isAbove(rowBeginTile.grid, tile)) {
                        System.out.println("starting new row with " + tile.id);
                        usedTiles.add(tile);
                        currentTile = tile;
                        rowBeginTile = tile;
                        tileRow++;
                        tileColumn = 0;
                        hasFoundLastInRow = false;
                        addTileToTileRow(currentTile, tileRow, tileColumn, masterImage);
                        break;
                    }
                }
            }
        }
        return masterImage;
    }

    private static void addTileToTileRow(Tile tile, int tileRow, int tileColumn, int[][] masterImage) {
        int[][] tileGrid = tile.grid;
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                masterImage[tileRow * 8 + (i - 1)][tileColumn * 8 + (j - 1)] = tileGrid[i][j];
            }
        }
    }

    private static  Tile getStartingCorner(List<Tile> tileList) {
        for (Tile tile: tileList) {
            if (tile.isCorner(tileList)) {
                return tile;
            }
        }
        throw new Error("couldn't find corner");
    }

    private static void getRoughness(int[][] masterImage) {
        List<int[][]> masterImagePermutations = Tile.getGridPermutations(masterImage);
        for (int[][] perm: masterImagePermutations) {
            System.out.println(getRoughnessOfPerm(perm));
        }
    }

    private static int getRoughnessOfPerm(int[][] perm) {
        int roughness = 0;
        for (int i = 0; i < perm.length; i++) {
            for (int j = 0; j < perm.length; j++) {
                if (perm[i][j] == 1) {
                    roughness++;
                }
            }
        }
        // System.out.println(Arrays.deepToString(perm));
        System.out.println("roughness is " + roughness);
        for (int i = 0; i < perm.length - 19; i++) {
            for (int j = 1; j < perm[0].length - 1; j++) {
                if (perm[i][j] == 1
                && perm[i+1][j+1] == 1
                && perm[i+4][j+1] == 1
                && perm[i+5][j] == 1
                && perm[i+6][j] == 1
                && perm[i+7][j+1] == 1
                && perm[i+10][j+1] == 1
                && perm[i+11][j] == 1
                && perm[i+12][j] == 1
                && perm[i+13][j+1] == 1
                && perm[i+16][j+1] == 1
                && perm[i+17][j] == 1
                && perm[i+18][j] == 1
                && perm[i+18][j-1] == 1
                && perm[i+19][j] == 1) {
                    roughness -= 15;
                }
            }
        }
        return roughness;
    }
}
