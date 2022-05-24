/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.rami3sam.crawler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    static String SEED_URL = "https://monzo.com";

    static String DOMAIN = "opera.com";
    static int CRAWL_LIMIT = 100;
    static boolean isLimited = false;
    static volatile HashSet crawledURLs = new HashSet();
    static volatile ArrayList<String> newURLs = new ArrayList<>();
    static FileWriter outputFileWriter = null;
    public static void main(String[] args) throws IOException {
        newURLs.add(SEED_URL);

        try {
            outputFileWriter = new FileWriter("links.txt");
        } catch (IOException e) {
            System.err.println("Couldn't open links.txt for output writing");
            outputFileWriter.close();
            outputFileWriter = null;
        }

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        for (int i = 0; true; ) {
            // check CRAWL_LIMIT if the crawling is limited
            if (isLimited == true && (i > CRAWL_LIMIT)){
                break;
            }

            String currentURL;
            // check for the existence of a new url in the queue
            if (newURLs.size() > i) {
                currentURL = newURLs.get(i);

                //check to ensure you didn't crawl it before
                if (!crawledURLs.contains(currentURL)) {
                    Main.crawledURLs.add(currentURL);
                    CrawlerRunnable worker = new CrawlerRunnable(currentURL);
                    executor.execute(worker);//calling execute method of ExecutorService
                }
                i++;


            }



        }
        System.out.println("Closing ThreadPoolExecutor");
        executor.shutdown();

        while (!executor.isTerminated()) {

        }

        if (outputFileWriter != null) {
            outputFileWriter.close();
        }

        System.out.println("************\n");

    }
}
