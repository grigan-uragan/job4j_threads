package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private ExecutorService pool;

    public EmailNotification(ExecutorService pool) {
        this.pool = pool;
    }

    public void emailTo(User user) {
        pool.submit(() -> {
            String subject = String.format("subject = Notification %s to email %s",
                    user.getUsername(), user.getEmail());
            String body = String.format("body = Add new event to %s", user.getUsername());
            send(subject, body, user.getEmail());
        });
    }

    public void send(String subject, String body, String email) {
        System.out.println(email);
        System.out.println(subject);
        System.out.println(body);
    }

    void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        User user = new User("Grigory", "griggory@mail.ru");
        EmailNotification notification = new EmailNotification(Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        ));
        notification.emailTo(user);
        notification.close();
    }
}
