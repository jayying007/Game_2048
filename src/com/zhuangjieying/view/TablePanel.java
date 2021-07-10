package com.zhuangjieying.view;

import com.zhuangjieying.model.TableData;

import javax.swing.*;
import java.awt.*;

/**
 * 主要宫格面板
 */
public class TablePanel extends JPanel {
    private final Color[] cellColor;
    private final TableData tableData;

    public TablePanel() {
        this.setBounds(0, 170, 400, 400);
        this.setLayout(null);
        this.setVisible(true);

        tableData = TableData.getInstance();
        cellColor = new Color[]{new Color(205, 193, 180), new Color(238, 228, 218), new Color(237, 224, 200),
                new Color(242, 177, 121), new Color(245, 149, 99), new Color(245, 124, 95),
                new Color(247, 94, 62), new Color(237, 206, 113), new Color(236, 203, 96),
                new Color(235, 199, 79), new Color(236, 196, 64), new Color(238, 193, 48),
                new Color(255, 61, 62), new Color(254, 30, 30), new Color(150, 150, 255),
                new Color(100, 100, 255), new Color(50, 50, 255), new Color(0, 0, 0)};
    }

    private void drawBackground(Graphics g) {
        Color backgroundColor = new Color(187, 173, 160);
        g.setColor(backgroundColor);
        g.fillRect(0, 0, 400, 400);
    }

    private void drawCells(Graphics g) {
        Font font = new Font("楷体", Font.BOLD, 16);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics(font);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = tableData.valueAtIndex(i, j);
                g.setColor(getColor(value));
                g.fillRect(16 + 96 * j, 16 + 96 * i, 80, 80);
                g.setColor(Color.white);
                int textX = 55 + 96 * j - fm.stringWidth(value + "") / 2;
                if (value != 0) {
                    g.drawString(value + "", textX, 52 + 96 * i + fm.getHeight() / 2);
                }
            }
        }
    }

    private Color getColor(int value) {
        int i = 0;
        while (value != 0 && (value & 1) == 0) {
            i++;
            value = value >> 1;
        }
        i = Math.min(i, cellColor.length - 1);
        return cellColor[i];
    }

    @Override
    public void paint(Graphics g) {
        drawBackground(g);
        drawCells(g);
    }
}
