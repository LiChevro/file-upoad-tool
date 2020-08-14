package com.szfg.ui.component;

import com.szfg.util.SettingUtil;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  10:12 2020-08-06
 **/
public class ConnectSetting {

    public JPanel init() {
        JPanel contentPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(contentPanel, BoxLayout.Y_AXIS);
        contentPanel.setLayout(boxLayout);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(commonConnInfo());
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(tokenConnInfo());
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(fileConnInfo());
        contentPanel.add(Box.createVerticalStrut(10));
        return contentPanel;
    }

    private JPanel commonConnInfo() {
        GridBagLayout bagLayout = new GridBagLayout();
        JPanel commonPanel = new JPanel();
        commonPanel.setLayout(bagLayout);
        commonPanel.setBorder(BorderFactory.createTitledBorder("Common"));
        JLabel address = new JLabel("Address");
        JTextField addressText = new JTextField(25);
        addressText.setText(SettingUtil.getValues("address"));
        addressText.setBackground(new Color(211, 211, 211));
        Button button = new Button("Save");
        button.addActionListener(e -> {
            ConsoleTextArea.startWriter("Address信息保存");
            Map<String, String> map = new HashMap<>();
            map.put("address", addressText.getText());
            SettingUtil.setValues(map);
            JOptionPane.showMessageDialog(commonPanel, "修改成功", "提示",JOptionPane.INFORMATION_MESSAGE);
        });
        GridBagConstraints gbc = setGrid(GridBagConstraints.BOTH, 0, 0, 1, 1, 0.25, 0, new Insets(8, 16, 8, 16));
        commonPanel.add(address, gbc);
        GridBagConstraints gbc1 = setGrid(GridBagConstraints.BOTH, 1, 0, 1, 1, 0.75, 0, new Insets(8, 16, 8, 16));
        commonPanel.add(addressText, gbc1);
        GridBagConstraints gbc2 = setGrid(GridBagConstraints.BOTH, 0, 1, 1, 1, 0.25, 0, new Insets(8, 16, 8, 16));
        commonPanel.add(button, gbc2);
        return commonPanel;
    }

    private JPanel tokenConnInfo() {
        GridBagLayout bagLayout = new GridBagLayout();
        JPanel tokenConnPanel = new JPanel();
        tokenConnPanel.setLayout(bagLayout);
        tokenConnPanel.setBorder(BorderFactory.createTitledBorder("Access Token"));

        JLabel tokenUri = new JLabel("Uri");
        JTextField tokenUriText = new JTextField(25);
        tokenUriText.setText(SettingUtil.getValues("tokenAccessUrl"));
        tokenUriText.setBackground(new Color(211, 211, 211));

        JLabel appId = new JLabel("AppId");
        JTextField appIdText = new JTextField(25);
        appIdText.setText(SettingUtil.getValues("appId"));
        appIdText.setBackground(new Color(211, 211, 211));

        JLabel appSecret = new JLabel("AppSecret");
        JTextField appSecretText = new JTextField(25);
        appSecretText.setText(SettingUtil.getValues("appSecret"));
        appSecretText.setBackground(new Color(211, 211, 211));

        Button saveButton = new Button("Save");
        saveButton.addActionListener(e -> {
            ConsoleTextArea.startWriter("Token Access 连接信息设置保存");
            Map<String,String> map = new HashMap<>();
            map.put("tokenAccessUrl", tokenUriText.getText());
            map.put("appId", appIdText.getText());
            map.put("appSecret", appSecretText.getText());
            SettingUtil.setValues(map);
            JOptionPane.showMessageDialog(tokenConnPanel, "修改成功", "提示",JOptionPane.INFORMATION_MESSAGE);
        });
        GridBagConstraints gbc = setGrid(GridBagConstraints.BOTH, 0, 0, 1, 1, 0.25, 0, new Insets(8, 16, 8, 16));
        tokenConnPanel.add(tokenUri, gbc);
        GridBagConstraints gbc1 = setGrid(GridBagConstraints.BOTH, 1, 0, 1, 1, 0.75, 0, new Insets(8, 16, 8, 16));
        tokenConnPanel.add(tokenUriText, gbc1);
        GridBagConstraints gbc2 = setGrid(GridBagConstraints.BOTH, 0, 1, 1, 1, 0.25, 0, new Insets(8, 16, 8, 16));
        tokenConnPanel.add(appId, gbc2);
        GridBagConstraints gbc3 = setGrid(GridBagConstraints.BOTH, 1, 1, 1, 1, 0.75, 0, new Insets(8, 16, 8, 16));
        tokenConnPanel.add(appIdText, gbc3);
        GridBagConstraints gbc4 = setGrid(GridBagConstraints.BOTH, 0, 2, 1, 1, 0.25, 0, new Insets(8, 16, 8, 16));
        tokenConnPanel.add(appSecret, gbc4);
        GridBagConstraints gbc5 = setGrid(GridBagConstraints.BOTH, 1, 2, 1, 1, 0.75, 0, new Insets(8, 16, 8, 16));
        tokenConnPanel.add(appSecretText, gbc5);
        GridBagConstraints gbc6 = setGrid(GridBagConstraints.BOTH, 0, 3, 1, 1, 0.25, 0, new Insets(8, 16, 8, 16));
        tokenConnPanel.add(saveButton, gbc6);
        return tokenConnPanel;
    }

    private JPanel fileConnInfo() {
        GridLayout gridLayout = new GridLayout(4, 2, 16, 8);
        GridBagLayout bagLayout = new GridBagLayout();
        JPanel fileUploadPanel = new JPanel();
        fileUploadPanel.setLayout(bagLayout);
        fileUploadPanel.setBorder(BorderFactory.createTitledBorder("File Upload"));

        JLabel fileUploadUri = new JLabel("Uri");
        JTextField fileUploadText = new JTextField(25);
        fileUploadText.setText(SettingUtil.getValues("fileUploadUrl"));
        fileUploadText.setBackground(new Color(211, 211, 211));

        JLabel jLabel = new JLabel("AccessToken");
        JTextField jTextField = new JTextField(25);
        jTextField.setText(SettingUtil.getValues("accessToken"));
        jTextField.setBackground(new Color(211, 211, 211));
        jTextField.setEditable(false);

        JLabel jLabel1 = new JLabel("AuthorSecret");
        JTextField jTextField1 = new JTextField();
        jTextField1.setText(SettingUtil.getValues("authorSecret"));
        jTextField1.setBackground(new Color(211, 211, 211));

        Button button = new Button("Save");
        button.addActionListener(e -> {
            Map<String, String> map = new HashMap<>();
            map.put("authorSecret", jTextField1.getText());
            map.put("fileUploadUrl", fileUploadText.getText());
            SettingUtil.setValues(map);
            JOptionPane.showMessageDialog(fileUploadPanel, "修改成功", "提示",JOptionPane.INFORMATION_MESSAGE);
        });
        GridBagConstraints gbc = setGrid(GridBagConstraints.BOTH, 0, 0, 1, 1, 0.25, 0, new Insets(8, 16, 8, 16));
        fileUploadPanel.add(fileUploadUri, gbc);
        GridBagConstraints gbc1 = setGrid(GridBagConstraints.BOTH, 1, 0, 1, 1, 0.75, 0, new Insets(8, 16, 8, 16));
        fileUploadPanel.add(fileUploadText, gbc1);
        GridBagConstraints gbc2 = setGrid(GridBagConstraints.BOTH, 0, 1, 1, 1, 0.25, 0, new Insets(8, 16, 8, 16));
        fileUploadPanel.add(jLabel, gbc2);
        GridBagConstraints gbc3 = setGrid(GridBagConstraints.BOTH, 1, 1, 1, 1, 0.75, 0, new Insets(8, 16, 8, 16));
        fileUploadPanel.add(jTextField, gbc3);
        GridBagConstraints gbc4 = setGrid(GridBagConstraints.BOTH, 0, 2, 1, 1, 0.25, 0, new Insets(8, 16, 8, 16));
        fileUploadPanel.add(jLabel1, gbc4);
        GridBagConstraints gbc5 = setGrid(GridBagConstraints.BOTH, 1, 2, 1, 1, 0.75, 0, new Insets(8, 16, 8, 16));
        fileUploadPanel.add(jTextField1, gbc5);
        GridBagConstraints gbc6 = setGrid(GridBagConstraints.BOTH, 0, 3, 1, 1, 0.25, 0, new Insets(8, 16, 8, 16));
        fileUploadPanel.add(button, gbc6);
        return fileUploadPanel;
    }

    /**
     * @param fill       java.awt.GridBagLayout
     * @param gridX      方格左上角单元格所在行号，行号在表格中以0开始，从左到右依次编号
     * @param gridY      方格左上角单元格所在列号，列号在表格中以0开始，从上到下依次编号
     * @param gridWidth  方格在横向占用的单元格数
     * @param gridHeight 方格在纵向占用的单元格数
     * @param weightX    方格的长与表格长（即父容器的长）的比例，若为0，则为组件默认长度
     * @param weightY    方格的宽与表格宽（即父容器的宽）的比例，若为0，则为组件默认宽度
     * @param insets     单元格与周围的间距
     * @return GridBagConstraints
     */
    private GridBagConstraints setGrid(int fill, int gridX, int gridY, int gridWidth, int gridHeight, double weightX, double weightY, Insets insets) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = fill;
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.gridwidth = gridWidth;
        gbc.gridheight = gridHeight;
        gbc.weightx = weightX;
        gbc.weighty = weightY;
        gbc.insets = insets;
        return gbc;
    }
}
