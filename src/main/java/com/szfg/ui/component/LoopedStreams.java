package com.szfg.ui.component;

import java.io.*;

/**
 * Author: Chevro.Lee <br>
 * Description: 用一个ByteArrayOutputStream提供和Java管道流类似的功能，避免出现死锁和IOException异常。
 * Date: Create in  10:38 2020-08-14
 **/
public class LoopedStreams {

    private PipedOutputStream pipedOS = new PipedOutputStream();

    private boolean keepRunning = true;

    private ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream() {
        public void close() {
            keepRunning = false;
            try {
                super.close();
                pipedOS.close();
            } catch (IOException e) {
                ConsoleTextArea.startWriter("输出流关闭异常");
                System.exit(1);
            }
        }
    };

    private PipedInputStream pipedIS = new PipedInputStream() {
        public void close() {
            keepRunning = false;
            try {
                super.close();
            } catch (IOException e) {
                ConsoleTextArea.startWriter("输入流关闭异常");
                System.exit(1);
            }
        }
    };

    public LoopedStreams() {
        try {
            pipedOS.connect(pipedIS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        startByteArrayReaderThread();
    }

    public InputStream getInputStream() {
        return pipedIS;
    }

    public OutputStream getOutputStream() {
        return byteArrayOS;
    }

    private void startByteArrayReaderThread() {
        new Thread(() -> {
            while (keepRunning) {
                // 检查流里面的字节数
                if (byteArrayOS.size() > 0) {
                    byte[] buffer = null;
                    synchronized (byteArrayOS) {
                        buffer = byteArrayOS.toByteArray();
                        // 清除缓冲区
                        byteArrayOS.reset();
                    }
                    try {
                        // 把提取到的数据发送给PipedOutputStream
                        pipedOS.write(buffer, 0, buffer.length);
                    } catch (IOException e) {
                        ConsoleTextArea.startWriter("管道流写入异常");
                        System.exit(1);
                    }
                } else {// 没有数据可用，线程进入睡眠状态
                    try {
                        // 每隔1秒查看ByteArrayOutputStream检查新数据
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
