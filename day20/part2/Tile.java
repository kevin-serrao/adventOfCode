package day20.part2;

import java.rmi.registry.RegistryHandler;
import java.util.*;

public class Tile {
    int id;
    int[][] grid;

    public Tile(int index, String[] fileContents) {
        String idString = fileContents[index];
        id = Integer.parseInt(idString.substring(5, 9));
        grid = buildGrid(index + 1, fileContents);
    }

    private int[][] buildGrid(int index, String[] fileContents) {
        int[][] grid = new int[10][10];
        for (int i = 0; i < 10; i ++) {
            for (int j = 0; j < 10; j++) {
                // System.out.println(fileContents[i + index]);
                grid[i][j] = fileContents[i + index].substring(j, j + 1).equals("#") ? 1 : 0;
            }
        }
        return grid;
    }

    public boolean isCorner(List<Tile> tiles) {
        List<int[]> edgeArrays = getEdgeArrays();
        int total = 0;
        List<Integer> matchingIds = new ArrayList<Integer>();
        for (Tile tile: tiles) {
            for (int[] possibleEdgeMatch: tile.getEdgeArrays()) {
                for (int[] myEdgeArray: edgeArrays) {
                    if ((Arrays.equals(possibleEdgeMatch, myEdgeArray) || Arrays.equals(myEdgeArray, revArray(possibleEdgeMatch))) && tile.id != id) {
                        matchingIds.add(tile.id);
                        total++;
                    }
                }
            }
        }
        return total == 2;
    }

    public void orient(List<Tile> tileList) {
        List<int[][]> gridPermutations = getGridPermutations(grid);
        for (int[][] gridPermutation: gridPermutations) {
            System.out.println("checking perm...");
            boolean isTop = false;
            boolean isLeft = false;
            for (Tile tile: tileList) {
                if (isAbove(gridPermutation, tile) && tile.id != id) {
                    System.out.println(tile.id);
                    isTop = true;
                }
            }
            for (Tile tile: tileList) {
                if (isLeftOf(gridPermutation, tile) && tile.id != id) {
                    System.out.println(tile.id);
                    isLeft = true;
                }
            }
            System.out.println(isLeft);
            System.out.println(isTop);
            if (isTop && isLeft) {
                grid = gridPermutation;
                System.out.println(Arrays.toString(grid[9]));
                System.out.println(Arrays.toString(transposeMatrix(grid)[9]));
                return;
            }
        }
    }

    public boolean isLeftOf(int[][] grid, Tile tile) {
        int[][] transposedGrid = transposeMatrix(grid);
        int[] rightColumn = transposedGrid[9];
        if (id == 3191) {
            System.out.println(Arrays.toString(rightColumn) + " for 3191!");
        }
        List<int[][]> gridPermutations = getGridPermutations(tile.grid);
        for (int[][] gridPermutation: gridPermutations) {
            int[][] transposedGridPermutation = transposeMatrix(gridPermutation);
            if (id == 3191) {
                System.out.println(tile.id+ ": " + Arrays.toString(transposedGridPermutation[0]));
            }
            if (Arrays.equals(rightColumn, transposedGridPermutation[0])) {
                tile.grid = gridPermutation;
                return true;
            }
        }
        return false;
    }

    public boolean isAbove(int[][] grid, Tile tile) {
        int[] bottomRow = grid[9];
        List<int[][]> gridPermutations = getGridPermutations(tile.grid);
        for (int[][] gridPermutation: gridPermutations) {
            if (Arrays.equals(bottomRow, gridPermutation[0])) {
                System.out.println("BASE:" + id + ": " + Arrays.toString(bottomRow));
                System.out.println("COMPARE:" + tile.id + ": " + Arrays.toString(gridPermutation[0]));
                tile.grid = gridPermutation;
                return true;
            }
        }
        return false;
    }

    public static  List<int[][]> getGridPermutations(int[][] tileGrid) {
        List<int[][]> gridPermutations = new ArrayList<int[][]>();
        gridPermutations.add(tileGrid);
        gridPermutations.add(transposeMatrix(tileGrid));
        gridPermutations.add(flipGridVertical(tileGrid));
        gridPermutations.add(transposeMatrix(flipGridVertical(tileGrid)));
        gridPermutations.add(flipGridHorizontal(tileGrid));
        gridPermutations.add(transposeMatrix(flipGridHorizontal(tileGrid)));
        gridPermutations.add(flipGridHorizontal(flipGridVertical(tileGrid)));
        gridPermutations.add(transposeMatrix(flipGridHorizontal(flipGridVertical(tileGrid))));
        gridPermutations.add(transposeMatrix(flipGridHorizontal(transposeMatrix(flipGridHorizontal(tileGrid)))));
        gridPermutations.add(flipGridVertical(flipGridHorizontal(transposeMatrix(tileGrid))));
        return gridPermutations;
    }

    private static int[][] flipGridVertical(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
    
        int[][] transposedMatrix = new int[n][m];
    
        for(int x = 0; x < n; x++) {
            for(int y = 0; y < m; y++) {
                transposedMatrix[x][y] = grid[x][n - y - 1];
            }
        }
    
        return transposedMatrix;
    }

    private static int[][] flipGridHorizontal(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
    
        int[][] transposedMatrix = new int[n][m];
    
        for(int x = 0; x < n; x++) {
            for(int y = 0; y < m; y++) {
                transposedMatrix[x][y] = grid[m - x - 1][y];
            }
        }
    
        return transposedMatrix;
    }

    private static int[] revArray(int[] arr) {
        int[] tmpArray = new int[10];
        for (int i = 0; i < arr.length; i++) {
            tmpArray[i] = arr[arr.length - i - 1];
        }
        return tmpArray;
    }

    private List<int[]> getEdgeArrays() {
        List<int[]> edgeArrays = new ArrayList<int[]>();
        edgeArrays.add(grid[0]);
        edgeArrays.add(grid[9]);
        int[][] transposedGrid = transposeMatrix(grid);
        edgeArrays.add(transposedGrid[0]);
        edgeArrays.add(transposedGrid[9]);
        return edgeArrays;
    }


    public static int[][] transposeMatrix(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
    
        int[][] transposedMatrix = new int[n][m];
    
        for(int x = 0; x < n; x++) {
            for(int y = 0; y < m; y++) {
                transposedMatrix[x][y] = matrix[y][x];
            }
        }
    
        return transposedMatrix;
    }
}
