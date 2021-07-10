package com.zhuangjieying.controller;

import com.zhuangjieying.model.TableData;
import com.zhuangjieying.model.UserData;
import com.zhuangjieying.view.MainView;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainLogic {
    private final MainView view;
    private final TableData tableData;
    private final UserData userData;
    private final Set<Character> cmdSet;

    private static MainLogic mainLogic;

    public static MainLogic getInstance() {
        if (mainLogic == null) {
            mainLogic = new MainLogic();
        }
        return mainLogic;
    }

    private MainLogic() {
        view = new MainView();
        tableData = TableData.getInstance();
        userData = UserData.getInstance();
        cmdSet = new HashSet<>(Arrays.asList('w', 'W', 's', 'S', 'a', 'A', 'd', 'D'));
        view.addKeyListener(new MainKeyListener());
    }

    public void focus() {
        view.requestFocus();
    }

    public void loadWithUsername(String username) {
        userData.loadData(username);
        view.refreshPanel();
    }

    class MainKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            char cmd = e.getKeyChar();
            if (!cmdSet.contains(cmd)) {
                return;
            }
            if (userData.getUsername() == null) {
                return;
            }
            if (cmd == 'w' || cmd == 'W') {
                System.out.println("???");
                tableData.moveUp();
            } else if (cmd == 's' || cmd == 'S') {
                tableData.moveDown();
            } else if (cmd == 'a' || cmd == 'A') {
                tableData.moveLeft();
            } else if (cmd == 'd' || cmd == 'D') {
                tableData.moveRight();
            }
            if (tableData.didMove()) {
                tableData.generateOneCell();
                view.refreshPanel();
                userData.saveData();
            }
            if (!tableData.canMove()) {
                JOptionPane.showMessageDialog(null, "无路可走~~~游戏结束！");
                userData.saveData();
                tableData.restart();
                userData.restart();
                view.refreshPanel();
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
