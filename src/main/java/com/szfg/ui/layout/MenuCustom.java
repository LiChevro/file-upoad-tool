package com.szfg.ui.layout;

import com.szfg.ui.component.ConnectSetting;
import com.szfg.ui.component.FileUploadView;

import javax.swing.*;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  17:57 2020-08-05
 **/
public class MenuCustom {

    public JMenuBar init(JFrame frame) {
        // 创建菜单
        JMenuBar jMenuBar = new JMenuBar();
        // 一级菜单
        JMenu fileUploadMenu = new JMenu("文件");
        JMenu settingMenu = new JMenu("设置");
        jMenuBar.add(fileUploadMenu);
        jMenuBar.add(settingMenu);
        // 二级菜单
        JMenuItem fileUploadItem = new JMenuItem("文件上传");
        JMenuItem connectItem = new JMenuItem("连接信息");
        JMenuItem themeItem = new JMenuItem("主题");
        fileUploadMenu.add(fileUploadItem);
        settingMenu.add(connectItem);
        settingMenu.add(themeItem);
        // 设置事件监听
        fileUploadItem.addActionListener(e -> {
            System.out.println("文件上传");
            frame.remove(frame.getContentPane());
            JPanel panel = new FileUploadView().init();
            frame.setContentPane(panel);
            frame.setVisible(true);
        });
        connectItem.addActionListener(e -> {
            System.out.println("设置");
            frame.remove(frame.getContentPane());
            JPanel panel = new ConnectSetting().init();
            frame.setContentPane(panel);
            frame.setVisible(true);
        });
        themeItem.addActionListener(e -> System.out.println("主题"));
        return jMenuBar;
    }

    public static void main(String[] args) {
        final JFrame jf = new JFrame("测试窗口");
        jf.setSize(300, 300);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MenuCustom menuCustom = new MenuCustom();
        JMenuBar menuBar = menuCustom.init(jf);
        jf.setJMenuBar(menuBar);
        jf.setVisible(true);
    }
}
