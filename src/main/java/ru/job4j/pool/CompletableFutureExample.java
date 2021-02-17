package ru.job4j.pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample {
    public void iWork() throws InterruptedException {
        int count = 0;
        while (count < 10) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("I'm working!");
            count++;
        }
    }

    public CompletableFuture<Void> goToTrash() {
        return CompletableFuture.runAsync(() -> {
            System.out.println("Meanwhile, my friend was going to take out garbage");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("He is back");
        });
    }

    public CompletableFuture<String> buyProduct(String product) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("My sister go to market");
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sister buy " + product);
            return product;
        });
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFutureExample example = new CompletableFutureExample();
        CompletableFuture<Void> goToTrash = example.goToTrash();
        goToTrash.thenRun(() -> {
            int count = 0;
            while (count < 3) {
                System.out.println("My friend wash hand");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            }
            System.out.println("Friend: my hands clean and smells like green apple");
        });
        CompletableFuture<String> product = example.buyProduct("Beer");
        CompletableFuture<Void> putFreezer =
                product.thenAccept((p) -> System.out.println("sister put " + p + " into freezer"));
        CompletableFuture<String> coldBeer = product.thenApply(p -> {
            System.out.println("what could be better than cold " + p);
            return p;
        });
        product.get();
        CompletableFuture<Void> result = coldBeer.thenCompose(a -> putFreezer);
        example.iWork();
        System.out.println("I like drink " + product.get());

    }
}
