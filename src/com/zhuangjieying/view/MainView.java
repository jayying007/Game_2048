package com.zhuangjieying.view;

import javax.swing.*;

public class MainView extends JFrame {
    //主界面的三个面板	--分数板	--宫格板	--功能板
    private final ScorePanel scorePanel;
    private final TablePanel tablePanel;
    private final FunctionPanel functionPanel;
    public MainView() {
        init();
        this.scorePanel = new ScorePanel();
        add(this.scorePanel);

        this.tablePanel = new TablePanel();
        add(this.tablePanel);

        this.functionPanel = new FunctionPanel();
        add(this.functionPanel);
        //一定要重绘一下，这也太不智能了吧
        repaint();
    }

    private void init() {
        setTitle("合成2048");
        setLayout(null);
        setResizable(false);
        setBounds(130, 80, 806, 598);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void refreshPanel() {
        tablePanel.repaint();
        scorePanel.refresh();
        functionPanel.refresh();
    }
}
