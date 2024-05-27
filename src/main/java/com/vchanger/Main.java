package com.vchanger;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.io.File;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Frame");
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("Выбрать файл");
        frame.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                    int result = fileChooser.showOpenDialog(null);
                    File file = fileChooser.getSelectedFile();
                    System.out.println(file.getAbsolutePath());
                    HashMap<String, Reactor> hashMap = null;
                    try {
                        Client client = new Client(file.getAbsolutePath());
                        hashMap = client.start();
                    } catch (IOException | ParseException | ParserConfigurationException | SAXException ex) {
                        ex.printStackTrace();
                    }
                    DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
                    DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);

                    for (Map.Entry<String, Reactor> entry : hashMap.entrySet()) {
                        DefaultMutableTreeNode reactorNode = new DefaultMutableTreeNode(entry.getKey());
                        reactorNode = entry.getValue().getNode(reactorNode);
                        treeModel.insertNodeInto((MutableTreeNode) reactorNode, (MutableTreeNode) treeModel.getRoot(), treeModel.getChildCount(treeModel.getRoot()));
                    }
                    JTree tree = new JTree(treeModel);

                    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

                    JScrollPane scrollPane = new JScrollPane(tree);

                    JFrame frame = new JFrame("Дерево реакторов");
                    frame.add(scrollPane);

                    frame.setSize(500, 300);

                    frame.setVisible(true);
                }catch (Exception exception){
                    String errorMessage = "Ошибка!!!";
                    JOptionPane.showMessageDialog(null, errorMessage, "Ошибка", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
}