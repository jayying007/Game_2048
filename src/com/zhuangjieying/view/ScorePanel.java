package com.zhuangjieying.view;

import com.zhuangjieying.model.UserData;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    JLabel currentScoreLabel;
    JLabel historyScoreLabel;

    public ScorePanel() {
        JLabel titleLabel = new JLabel("2048");
        Font titleFont = new Font("黑体", Font.BOLD, 50);
        titleLabel.setFont(titleFont);
        titleLabel.setBounds(50, 30, 200, 100);
        this.add(titleLabel);

        currentScoreLabel = new JLabel("当前分数：0");
        currentScoreLabel.setBounds(220, 10, 130, 50);
        this.add(currentScoreLabel);

        historyScoreLabel = new JLabel("历史最高：0");
        historyScoreLabel.setBounds(220, 80, 130, 50);
        this.add(historyScoreLabel);

        this.setBounds(0, 0, 400, 170);
        this.setBackground(Color.gray);
        this.setLayout(null);
    }

    public void refresh() {
        UserData userData = UserData.getInstance();
        int currentScore = userData.getCurrentScore();
        int historyScore = userData.getHistoryScore();
        currentScoreLabel.setText("当前分数：" + currentScore);
        historyScoreLabel.setText("历史最高：" + historyScore);
    }
}
