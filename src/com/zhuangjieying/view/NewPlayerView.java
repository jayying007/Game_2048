package com.zhuangjieying.view;

import com.zhuangjieying.controller.MainLogic;
import com.zhuangjieying.model.UserData;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class NewPlayerView extends JFrame {
    public NewPlayerView() {
        //界面初始化
        setTitle("创建新玩家");
        setLayout(null);
        setBounds(300, 200, 350, 200);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //存档位置不存在则创建
        File save = new File("save");
        if(!save.exists()){
            save.mkdir();
        }
        //标签组件
        Container con = getContentPane();
        JLabel nameLabel = new JLabel("玩家昵称：");
        nameLabel.setBounds(50, 30, 100, 50);
        con.add(nameLabel);

        JTextField nameText = new JTextField();
        nameText.setBounds(120, 35, 200, 40);
        con.add(nameText);

        JButton confirmButton = new JButton("确定");
        confirmButton.setBounds(135, 100, 80, 50);
        confirmButton.addActionListener(e -> {
            String username = nameText.getText();
            File savePath = new File("save/"+username);
            if(savePath.exists()){
                JOptionPane.showMessageDialog(null, "已存在该玩家");
            } else {
                UserData.getInstance().createUser(username);
                MainLogic.getInstance().loadWithUsername(username);
                MainLogic.getInstance().focus();
                dispose();
            }
        });
        con.add(confirmButton);
    }
}
