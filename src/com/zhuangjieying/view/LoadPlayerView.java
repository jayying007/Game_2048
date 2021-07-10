package com.zhuangjieying.view;

import com.zhuangjieying.controller.MainLogic;
import com.zhuangjieying.model.UserData;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LoadPlayerView extends JFrame {
    private List<String> usernames;

    public LoadPlayerView() {
        setTitle("载入/删除档案");
        setLayout(null);
        setBounds(300, 200, 350, 200);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        initUsernames();
        layoutSubview();
    }
    public void initUsernames() {
        File save = new File("save");
        if (!save.exists()) {
            save.mkdir();
        }
        File[] fileList = save.listFiles();
        assert fileList != null;
        usernames = new ArrayList<>();
        for (File file : fileList) {
            usernames.add(file.getName());
        }
    }
    public void layoutSubview() {
        //选择器
        JComboBox<String> nameBox = new JComboBox<>(usernames.toArray(new String[]{}));
        nameBox.setMaximumRowCount(10);
        nameBox.setBorder(BorderFactory.createTitledBorder("选择玩家名称"));
        nameBox.setBounds(20, 20, 300, 60);
        add(nameBox);
        //读取
        JButton choose = new JButton("载入");
        choose.setBounds(50, 100, 100, 50);
        choose.addActionListener(e -> {
            String username = usernames.get(nameBox.getSelectedIndex());
            MainLogic.getInstance().loadWithUsername(username);
            MainLogic.getInstance().focus();
            dispose();
        });
        add(choose);
        //删除
        JButton delete = new JButton("删除");
        delete.setBounds(200, 100, 100, 50);
        delete.addActionListener(e -> {
            String name = usernames.get(nameBox.getSelectedIndex());
            if(name.equals(UserData.getInstance().getUsername())){
                JOptionPane.showMessageDialog(null, "不能删除当前玩家！");
            } else{
                File file = new File("save/"+name);
                if(file.exists()){
                    file.delete();
                    JOptionPane.showMessageDialog(null, "删除玩家"+name+" 数据成功！！！");
                }
                usernames.remove(name);
                nameBox.removeItemAt(nameBox.getSelectedIndex());
            }
        });
        add(delete);
    }
}
