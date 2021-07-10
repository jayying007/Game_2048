package com.zhuangjieying.view;

import com.zhuangjieying.model.UserData;

import javax.swing.*;
import java.awt.*;

public class FunctionPanel extends JPanel {
    private final JLabel playerNameLabel;
    public FunctionPanel() {
        this.init();
        this.initMenu();

        this.playerNameLabel = new JLabel("(暂无玩家)");
        this.playerNameLabel.setBounds(30, 10, 300, 30);
        this.add(this.playerNameLabel);
    }
    private void init() {
        this.setBounds(400, 0, 400, 570);
        this.setBackground(new Color(200,200,200));
        this.setLayout(null);
    }
    private void initMenu() {
        JButton button = new JButton("读取存档");
        button.setBounds(300, 10, 90, 30);
        button.addActionListener(e -> new LoadPlayerView());
        this.add(button);

        button = new JButton("新游戏");
        button.setBounds(130, 150, 150, 50);
        button.addActionListener(e -> new NewPlayerView());
        this.add(button);

        button = new JButton("排行榜");
        button.setBounds(130, 250, 150, 50);
        button.addActionListener(e -> new RankingView());
        this.add(button);

        button = new JButton("帮助");
        button.setBounds(130, 350, 150, 50);
        button.addActionListener(e -> new GetHelpView());
        this.add(button);

        button = new JButton("退出");
        button.setBounds(130, 450, 150, 50);
        button.addActionListener(e -> System.exit(0));
        this.add(button);
    }

    public void refresh() {
        this.playerNameLabel.setText(UserData.getInstance().getUsername());
    }
}
