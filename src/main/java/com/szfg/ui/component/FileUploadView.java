package com.szfg.ui.component;

import com.szfg.logic.AccessToken;
import com.szfg.logic.FileUploader;
import com.szfg.util.SettingUtil;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  16:06 2020-08-07
 **/
public class FileUploadView {

    private static int inSyncCount = 0;

    public JPanel init() {
        // 纵向盒类型布局
        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        //添加长度为10的垂直框架
        jPanel.add(fileComponent());
        jPanel.add(Box.createVerticalStrut(10));
        jPanel.add(new JScrollPane(outArea()));
        jPanel.add(Box.createVerticalStrut(10));
        return jPanel;
    }

    private JPanel fileComponent() {
        JFrame parentFrame = new JFrame();
        parentFrame.setBounds(100, 100, 500, 393);
        parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parentFrame.getContentPane().setLayout(null);

        JPanel contentPanel = new JPanel();
        GridBagLayout bagLayout = new GridBagLayout();
        contentPanel.setLayout(bagLayout);
        Button inputButton = new Button("input");
        JTextField inputTxt = new JTextField(25);

        // 事件触发
        FileUploader fileUploader = new FileUploader();
        inputButton.addActionListener(e -> {
            JFileChooser inputFileChooser = new JFileChooser();
            inputFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int i = inputFileChooser.showDialog(parentFrame, "选择");
            if (i == JFileChooser.APPROVE_OPTION) {
                String fileUploadPath = inputFileChooser.getSelectedFile().getAbsolutePath();
                inputTxt.setText(fileUploadPath);
                ConsoleTextArea.startWriter(fileUploadPath);
                fileUploader.setFileUploadPath(fileUploadPath);
            }
        });

        Button outputButton = new Button("output");
        JTextField outputTxt = new JTextField(25);
        outputButton.addActionListener(e -> {
            JFileChooser outFileChooser = new JFileChooser();
            outFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int i = outFileChooser.showDialog(parentFrame, "选择");
            if (i == JFileChooser.APPROVE_OPTION) {
                String fileOutputPath = outFileChooser.getSelectedFile().getAbsolutePath();
                outputTxt.setText(fileOutputPath);
                ConsoleTextArea.startWriter(fileOutputPath);
                fileUploader.setFileOutputPath(fileOutputPath);
            }
        });
        JCheckBox jCheckBox = new JCheckBox("inSync");
        Button button = new Button("upload");
        jCheckBox.addActionListener(e -> {
            inSyncCount++;
            if (inSyncCount % 2 == 0) {
                ConsoleTextArea.startWriter("取消inSync同步");
            } else {
                ConsoleTextArea.startWriter("选择inSync同步");
            }
            fileUploader.setInSync(1);
        });
        button.addActionListener(e -> {
            ConsoleTextArea.startWriter("开始上传......");
            fileUploader.setAuthorSecret(SettingUtil.getValues("authorSecret"));
            fileUploader.setFileUploadUrl(SettingUtil.getValues("fileUploadUrl"));
            // 获取token
            String accessToken = new AccessToken().get().getResultData().getAccessToken();
            ConsoleTextArea.startWriter("AccessToken == " + accessToken);
            // 写入properties
            Map<String, String> map = new HashMap<>();
            map.put("accessToken", accessToken);
            SettingUtil.setValues(map);
            // 上传
            fileUploader.doUp();
        });

        GridBagConstraints gbc = setGrid(GridBagConstraints.BOTH, 0, 0, 1, 1, 0.25, 0, new Insets(8, 16, 8, 16));
        contentPanel.add(inputButton, gbc);
        GridBagConstraints gbc1 = setGrid(GridBagConstraints.BOTH, 1, 0, 1, 1, 0.75, 0, new Insets(8, 8, 8, 8));
        contentPanel.add(inputTxt, gbc1);
        GridBagConstraints gbc2 = setGrid(GridBagConstraints.BOTH, 0, 1, 1, 1, 0.25, 0, new Insets(8, 16, 8, 16));
        contentPanel.add(outputButton, gbc2);
        GridBagConstraints gbc3 = setGrid(GridBagConstraints.BOTH, 1, 1, 1, 1, 0.75, 0, new Insets(8, 8, 8, 8));
        contentPanel.add(outputTxt, gbc3);
        GridBagConstraints gbc4 = setGrid(GridBagConstraints.WEST, 0, 2, 2, 1, 0.5, 0, new Insets(8, 0, 0, 8));
        contentPanel.add(jCheckBox, gbc4);
        GridBagConstraints gbc5 = setGrid(GridBagConstraints.EAST, 1, 2, 1, 1, 0.5, 0, new Insets(8, 0, 0, 8));
        contentPanel.add(button, gbc5);
        return contentPanel;
    }

    private JPanel outArea() {
        JPanel outAreaPanel = new JPanel();
        GridBagLayout bagLayout = new GridBagLayout();
        outAreaPanel.setLayout(bagLayout);
        JLabel label = new JLabel("Result:");
        ConsoleTextArea consoleTextArea = new ConsoleTextArea(15, 31);
        GridBagConstraints gbc = setGrid(GridBagConstraints.WEST, 0, 0, 1, 1, 0.25, 0, new Insets(8, 0, 0, 8));
        GridBagConstraints gbc1 = setGrid(GridBagConstraints.BOTH, 1, 0, 3, 2, 0.75, 0, new Insets(8, 0, 0, 8));
        outAreaPanel.add(label, gbc);
        outAreaPanel.add(consoleTextArea, gbc1);
        return outAreaPanel;
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
