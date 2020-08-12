package com.szfg.ui.component;

import com.szfg.logic.FileUploader;
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
        GridLayout gridLayout = new GridLayout(2, 2, 16, 8);
        JPanel commonPanel = new JPanel();
        commonPanel.setLayout(gridLayout);
        commonPanel.setBorder(BorderFactory.createTitledBorder("Common"));
        JLabel address = new JLabel("Address");
        JTextField addressText = new JTextField(25);
        addressText.setText(SettingUtil.getValues("address"));
        addressText.setBackground(new Color(211, 211, 211));
        Button button = new Button("Save");
        button.addActionListener(e -> {
            System.out.println("Address信息保存");
            Map<String, String> map = new HashMap<>();
            map.put("address", addressText.getText());
            SettingUtil.setValues(map);
        });
        commonPanel.add(address);
        commonPanel.add(addressText);
        commonPanel.add(button);
        return commonPanel;
    }

    private JPanel tokenConnInfo() {
        // 5行2列，横纵间隙为8的表格式布局
        GridLayout gridLayout = new GridLayout(4, 2, 16, 8);
        JPanel tokenConnPanel = new JPanel();
        tokenConnPanel.setLayout(gridLayout);
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
            System.out.println("Token Access 连接信息设置保存");
            Map<String,String> map = new HashMap<>();
            map.put("tokenAccessUrl", tokenUriText.getText());
            map.put("appId", appIdText.getText());
            map.put("appSecret", appSecretText.getText());
            SettingUtil.setValues(map);
        });
        tokenConnPanel.add(tokenUri);
        tokenConnPanel.add(tokenUri);
        tokenConnPanel.add(tokenUriText);
        tokenConnPanel.add(appId);
        tokenConnPanel.add(appIdText);
        tokenConnPanel.add(appSecret);
        tokenConnPanel.add(appSecretText);
        tokenConnPanel.add(saveButton);
        return tokenConnPanel;
    }

    private JPanel fileConnInfo() {
        GridLayout gridLayout = new GridLayout(4, 2, 16, 8);
        JPanel fileUploadPanel = new JPanel();
        fileUploadPanel.setLayout(gridLayout);
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
        });
        fileUploadPanel.add(fileUploadUri);
        fileUploadPanel.add(fileUploadText);
        fileUploadPanel.add(jLabel);
        fileUploadPanel.add(jTextField);
        fileUploadPanel.add(jLabel1);
        fileUploadPanel.add(jTextField1);
        fileUploadPanel.add(button);
        return fileUploadPanel;
    }

    public static void main(String[] args) {
        // 生成窗口
        JFrame windows1 = new JFrame("窗口1");
        ConnectSetting connectSetting = new ConnectSetting();
        windows1.setContentPane(connectSetting.init());
        // 获得这个窗口的内容面板
        windows1.setBounds(60, 100, 788, 480);
        // 设置位置大小
        windows1.setVisible(true);
        // 设置按x后的操作.[这个只是关闭那个窗口]
        windows1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
