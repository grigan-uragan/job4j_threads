package ru.job4j.concurrent;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public String content(Predicate<Integer> predicate) {
        StringBuilder result = new StringBuilder();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = in.read()) != -1) {
                if (predicate.test(data)) {
                    result.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public synchronized String getContentWithoutUnicode() {
        return content(integer -> integer < 0x80);
    }

    public synchronized String getContent() {
        return content(integer -> true);
    }

    public synchronized void saveContent(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
