package com.zhuangjieying.view;

import com.zhuangjieying.Main;
import com.zhuangjieying.controller.MainLogic;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class RankingView extends JFrame {
    class Player implements Comparable<Player> {
        private final String username;
        private final int score;

        public Player(String username, int score) {
            this.username = username;
            this.score = score;
        }

        @Override
        public int compareTo(Player player) {
            return Integer.compare(player.score, this.score);
        }
    }

    private Player[] players;
    public RankingView() {
        setTitle("2048英雄榜");
        setLayout(null);
        setBounds(200, 200, 600, 500);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //窗口关闭，重新聚焦面板
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainLogic.getInstance().focus();
            }
        });
        initPlayers();
        initTableView();
    }
    public void initPlayers() {
        File save = new File("save");
        if(!save.exists()){
            save.mkdir();
        }
        File[] fileList = save.listFiles();
        assert fileList != null;
        players = new Player[fileList.length];
        for(int i = 0; i < fileList.length; i++) {
            int score = 0;
            try{
                File file = new File("save/"+fileList[i].getName());
                FileReader out = new FileReader(file);
                char[] bytes = new char[1024];
                out.read(bytes);
                for(int j=0;bytes[j]!='\n';j++){
                    score=score*10+bytes[j]-'0';
                }
                out.close();
                players[i] = new Player(fileList[i].getName(), score);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        Arrays.sort(players);
    }
    public void initTableView() {
        String[][] tableData = new String[players.length][3];
        for(int i = 0; i < players.length; i++) {
            tableData[i][0] = (i + 1) + "";
            tableData[i][1] = players[i].username;
            tableData[i][2] = players[i].score + "";
        }
        String[] tableTitle = {"排名","玩家昵称","历史最高分数"};

        JTable jTable = new JTable(tableData, tableTitle);
        jTable.setEnabled(false);
        jTable.setRowHeight(40);
        jTable.setFont(new Font("黑体",Font.BOLD,25));
        //居中显示
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        jTable.setDefaultRenderer(Object.class, r);
        //添加滚动条
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(10, 10, 570, 425);
        add(jScrollPane);
    }
}
