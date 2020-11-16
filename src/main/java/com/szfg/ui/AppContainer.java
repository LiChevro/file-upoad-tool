package com.szfg.ui;

import com.formdev.flatlaf.*;
import com.szfg.ui.component.FileUploadView;
import com.szfg.ui.layout.MenuCustom;

import javax.swing.*;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  15:05 2020-07-31
 **/
public class AppContainer {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        final JFrame jf = new JFrame("电子证照文件上传");
        MenuCustom menuCustom = new MenuCustom();
        JMenuBar menuBar = menuCustom.init(jf);
        jf.setJMenuBar(menuBar);
        jf.setContentPane(new FileUploadView().init());
        jf.setVisible(true);
        jf.setSize(700, 710);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
