package ru.job4j.concurrent;

import java.io.*;

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized String getContent() {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                result.append(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public synchronized String getContentWithoutUnicode() {
        StringBuilder result = new StringBuilder();
        int data;
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            while ((data = in.read()) != -1) {
                if (data < 0x80) {
                    result.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public synchronized void saveContent(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
