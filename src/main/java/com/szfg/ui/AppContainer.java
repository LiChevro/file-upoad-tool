package com.szfg.ui;

import com.szfg.ui.component.FileUploadView;
import com.szfg.ui.layout.MenuCustom;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  15:05 2020-07-31
 **/
public class AppContainer {

    public static void main(String[] args) {
        final JFrame jf = new JFrame("电子证照文件上传");
        MenuCustom menuCustom = new MenuCustom();
        JMenuBar menuBar = menuCustom.init(jf);
        jf.setJMenuBar(menuBar);
        jf.setContentPane(new FileUploadView().init());
        jf.setVisible(true);
        jf.setSize(600, 480);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
