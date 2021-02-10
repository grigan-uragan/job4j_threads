package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class DownloadControl implements Runnable {
    private final String url;
    private final int speed;

    public DownloadControl(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
        FileOutputStream out = new FileOutputStream("test.txt")) {
            byte[] dataBuffer  = new byte[1024];
            int bytesRead;
            while (true) {
                long start = new Date().getTime();
                bytesRead = in.read(dataBuffer, 0, 1024);
                long finish = new Date().getTime();
                long result = (int) (finish - start);
                if (result > speed) {
                    System.out.println("was delayed");
                    Thread.sleep(result - speed);
                }
                if (bytesRead == -1) {
                    break;
                }
                out.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        String url = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        int speed = 1;
        Thread thread = new Thread(new DownloadControl(url, speed));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
