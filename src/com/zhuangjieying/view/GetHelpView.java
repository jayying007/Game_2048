package com.zhuangjieying.view;

import com.zhuangjieying.controller.MainLogic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class GetHelpView extends JFrame {
    public GetHelpView() {
        setTitle("帮助");
        setLayout(null);
        setBounds(200, 200, 850, 550);
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
        layoutSubview();
    }

    public void layoutSubview() {
        PicPanel help = new PicPanel();
        help.setBounds(10, 10, 900, 500);
        add(help);

        Font font = new Font("黑体", Font.BOLD, 25);
        String msg = "按键盘上的W、S、A、D来控制滑块移动的方向，依次是上、下、左、右\n"
                + "如图，输入W让滑块上移，滑块遇到相同的数字将会合并\n"
                + "每一次操作都会生成一个新的滑块，数值为2或4\n"
                + "发挥你的脑力，尽最大努力合成大数字的方块吧 ^_^";
        JTextArea jt = new JTextArea(msg);
        jt.setBounds(10, 380, 810, 200);
        jt.setEditable(false);
        jt.setBackground(new Color(238, 238, 238));
        jt.setFont(font);
        this.add(jt);
    }

    class PicPanel extends JPanel {
        Image start, to, end = null;

        public PicPanel() {
            try {
                start = ImageIO.read(new File("help/1.png"));
                to = ImageIO.read(new File("help/to.png"));
                end = ImageIO.read(new File("help/2.png"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void paint(Graphics g) {
            g.drawImage(start, 0, 0, 350, 350, null);
            g.drawImage(to, 350, 150, 120, 80, null);
            g.drawImage(end, 460, 0, 350, 350, null);
        }
    }
}
