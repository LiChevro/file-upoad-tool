package com.szfg;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BoxAndGrid {
    public static void main(String args[]) {
        JFrame frame = new JFrame();
        JPanel topPane = new JPanel();
        JPanel midPane = new JPanel();
        JPanel panesHolder = new JPanel();
        JLabel label = new JLabel("Top label");
        JTextField field = new JTextField();
        field.setColumns(5);

        topPane.setLayout(new FlowLayout());
        midPane.setLayout(new GridLayout(3, 2));

        topPane.add(label);
        topPane.add(field);

        midPane.add(new JButton("Button 1"));
        midPane.add(new JButton("Button 2"));
        midPane.add(new JButton("A"));
        midPane.add(new JButton("H"));
        midPane.add(new JButton("I"));
        midPane.add(new JButton("T"));

        panesHolder.setLayout(new BoxLayout(panesHolder, BoxLayout.Y_AXIS));
        panesHolder.add(topPane);
        panesHolder.add(midPane);

        frame.add(panesHolder);
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

