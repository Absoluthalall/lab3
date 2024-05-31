package com.vchanger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class GUI {
    public void startGUI() {
        JFrame frame = new JFrame("Frame");
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("Выбрать файл");
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int r = fileChooser.showOpenDialog(null);
                try {
                File file = fileChooser.getSelectedFile();
                System.out.println(file.getAbsolutePath());
                SQLite sqLite = new SQLite(file.getAbsolutePath());
                Excel excel = new Excel(sqLite.getResult("Страна"),sqLite.getResult("Регион"),sqLite.getResult("Компания"));
                excel.createExcelFile();
                } catch (Exception ex) {
                    String errorMessage = "Ошибка!!!";
                    JOptionPane.showMessageDialog(null, errorMessage, "Ошибка", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
    }
