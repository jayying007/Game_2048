package com.zhuangjieying.model;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class TableData {
    private final int[][] cells;
    private final int[][] lastCells;
    private final Random random;
    private final MergeHelper mergeHelper;
    private static TableData tableData;

    public static TableData getInstance() {
        if(tableData == null) {
            tableData = new TableData();
        }
        return tableData;
    }

    private TableData() {
        cells = new int[4][4];
        lastCells = new int[4][4];
        random = new Random();
        mergeHelper = new MergeHelper();
    }

    public void restart() {
        initCell();
    }

    public void initCell() {
        //clear
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cells[i][j] = 0;
            }
        }
        generateOneCell();
    }
    public void generateOneCell() {
        while (true) {
            int i = random.nextInt(4);
            int j = random.nextInt(4);
            if(cells[i][j] == 0) {
                if(i + j <= 2) {
                    cells[i][j] = 4;
                } else {
                    cells[i][j] = 2;
                }
                return;
            }
        }
    }

    public int valueAtIndex(int row, int col) {
        return cells[row][col];
    }

    public void setValueAtIndex(int row, int col, int value) {
        cells[row][col] = value;
    }

    public void moveUp() {
        updateLastCells();
        for(int j = 0; j < 4; j++) {
            for(int i = 0; i < 4; i++) {
                mergeHelper.appendCell(i, j);
            }
            for(int i = 0; i < 4; i++) {
                mergeHelper.fillCell(i, j);
            }
        }
    }
    public void moveDown() {
        updateLastCells();
        for(int j = 0; j < 4; j++) {
            for(int i = 3; i >= 0; i--) {
                mergeHelper.appendCell(i, j);
            }
            for(int i = 3; i >= 0; i--) {
                mergeHelper.fillCell(i, j);
            }
        }
    }
    public void moveLeft() {
        updateLastCells();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                mergeHelper.appendCell(i, j);
            }
            for(int j = 0; j < 4; j++) {
                mergeHelper.fillCell(i, j);
            }
        }
    }
    public void moveRight() {
        updateLastCells();
        for(int i = 0; i < 4; i++) {
            for(int j = 3; j >= 0; j--) {
                mergeHelper.appendCell(i, j);
            }
            for(int j = 3; j >= 0; j--) {
                mergeHelper.fillCell(i, j);
            }
        }
    }
    public void updateLastCells() {
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                lastCells[i][j] = cells[i][j];
            }
        }
    }
    public boolean didMove() {
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(lastCells[i][j] != cells[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean canMove() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(cells[i][j] == 0) {
                    return true;
                }
            }
        }
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 3; j++) {
                if(cells[i][j] == cells[i][j + 1]) {
                    return true;
                }
            }
        }
        for(int j = 0; j < 4; j++) {
            for(int i = 0; i < 3; i++) {
                if(cells[i][j] == cells[i + 1][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    class MergeHelper {
        private final Deque<Integer> deque;
        public MergeHelper() {
            deque = new LinkedList<>();
        }
        public void appendCell(int row, int col) {
            if(cells[row][col] == 0) {
                return;
            }
            if(!deque.isEmpty() && deque.peekLast() == cells[row][col]) {
                deque.pollLast();
                deque.addLast(cells[row][col] * 2);
                //合并加分
                UserData.getInstance().addScore(cells[row][col] * 2);
            } else {
                deque.addLast(cells[row][col]);
            }
        }
        public void fillCell(int row, int col) {
            if(!deque.isEmpty()) {
                cells[row][col] = deque.pollFirst();
            } else {
                cells[row][col] = 0;
            }
        }
    }
}
