package com.zhuangjieying.model;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class UserData {
    private int currentScore;
    private int historyScore;
    private String username;
    private final TableData tableData;
    private static UserData userData;

    public static UserData getInstance() {
        if(userData == null) {
            userData = new UserData();
        }
        return userData;
    }

    private UserData() {
        tableData = TableData.getInstance();
    }

    //读取存档数据
    public void loadData(String fileName) {
        currentScore = 0;
        historyScore = 0;
        File file = new File("save/" + fileName);
        if (file.exists()) {
            username = fileName;
            try {
                FileReader in = new FileReader(file);
                char[] bytes = new char[1024];
                in.read(bytes);
                //第一行为历史最高分数
                int i = 0;
                while (bytes[i] != '\n') {
                    historyScore = historyScore * 10 + bytes[i] - '0';
                    i++;
                }
                i++;
                //第二行为当前分数
                while (bytes[i] != '\n') {
                    currentScore = currentScore * 10 + bytes[i] - '0';
                    i++;
                }
                i++;
                //第三行为宫格的数值，宫格隔开
                for(int j = 0; j < 16; j++) {
                    int value = 0;
                    while (bytes[i] != ' ') {
                        value = value * 10 + bytes[i] - '0';
                        i++;
                    }
                    tableData.setValueAtIndex(j / 4, j % 4, value);
                    i++;
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "错误，找不到该玩家的存档！！");
        }
    }
    //保存玩家数据
    public void saveData() {
        File file = new File("save/" + username);
        try {
            FileWriter in = new FileWriter(file);
            in.write(historyScore + "\n");
            in.write(currentScore + "\n");
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    in.write(tableData.valueAtIndex(i, j) + " ");
                }
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createUser(String username) {
        tableData.initCell();
        File file = new File("save/" + username);
        try {
            FileWriter in = new FileWriter(file);
            in.write("0\n0\n");
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    in.write(tableData.valueAtIndex(i, j) + " ");
                }
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void addScore(int score) {
        this.currentScore += score;
        historyScore = Math.max(historyScore, currentScore);
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getHistoryScore() {
        return historyScore;
    }

    public void restart() {
        currentScore = 0;
    }
}
