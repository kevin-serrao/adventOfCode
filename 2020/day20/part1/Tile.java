package day20.part1;

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
        // System.out.println(id);
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

    private int[] revArray(int[] arr) {
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
