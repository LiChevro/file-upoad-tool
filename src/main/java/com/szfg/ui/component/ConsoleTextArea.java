package com.szfg.ui.component;

import javax.swing.*;
import javax.swing.text.Document;
import java.io.*;
import java.time.LocalDateTime;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  11:05 2020-08-14
 **/
public class ConsoleTextArea extends JTextArea {


    public ConsoleTextArea(InputStream[] inStreams) {
        for(int i = 0; i < inStreams.length; ++i) {
            startConsoleReaderThread(inStreams[i]);
        }
    }

    public ConsoleTextArea(int rows, int columns) {
        super(rows, columns);
        final LoopedStreams ls = new LoopedStreams();

        // 重定向System.out和System.err
        PrintStream ps = new PrintStream(ls.getOutputStream());
        System.setOut(ps);
        System.setErr(ps);

        startConsoleReaderThread(ls.getInputStream());
    }

    private void startConsoleReaderThread(InputStream inStream) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(inStream));
        new Thread(() -> {
            StringBuffer sb = new StringBuffer();
            try {
                String s;
                Document doc = getDocument();
                while((s = br.readLine()) != null) {
                    boolean caretAtEnd = false;
                    caretAtEnd = getCaretPosition() == doc.getLength();
                    sb.setLength(0);
                    append(sb.append(s).append('\n').toString());
                    if(caretAtEnd) {
                        setCaretPosition(doc.getLength());
                    }
                }
            } catch(IOException e) {
                JOptionPane.showMessageDialog(null, "从BufferedReader读取错误：" + e);
                System.exit(1);
            }
        }).start();
    }

    public void clear() {
        super.setText(null);
    }

    public static void startWriter (String msg) {
        LocalDateTime dateTime = LocalDateTime.now();
        PrintStream ps = System.out;
        new Thread(() -> {
            ps.println(dateTime.toString() + "  " + msg);
        }).start();
    }

    private static void startWriterTestThread(final String name, final PrintStream ps, final int delay, final int count) {
        new Thread(() -> {
            for(int i = 1; i <= count; ++i) {
                ps.println("***" + name + ", hello !, i=" + i);
                try {
                    Thread.sleep(delay);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
