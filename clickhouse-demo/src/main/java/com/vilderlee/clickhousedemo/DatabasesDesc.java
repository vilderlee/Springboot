package com.vilderlee.clickhousedemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * TableDesc
 *
 * @ClassName TableDesc
 * @Description
 * @Author VilderLee
 * @Date 2021/3/31 2:56 下午
 */

public class DatabasesDesc {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override public String toString() {
        return "TableDesc{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
         Future<String> future = (Future<String>) executorService.submit(() -> {
            try {
                throw new RuntimeException("ERROR!");
            } catch (Exception e) {
                DatabasesDesc databasesDesc = null;
                databasesDesc.getName();
            }

        });

        System.out.println(future.get());
        System.out.println("Continue...");
    }
}
