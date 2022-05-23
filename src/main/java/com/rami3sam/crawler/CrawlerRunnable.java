package com.rami3sam.crawler;

import java.util.ArrayList;

public class CrawlerRunnable implements Runnable {
    String crawlURL;

    public CrawlerRunnable(String url) {
        crawlURL = url;
    }

    @Override
    public void  run() {
        Main.crawledURLs.add(crawlURL);
        String output = "";
        output += "---------------------\n";
        output += "crawling url : " + crawlURL + "\n";

        ArrayList<String> links = Util.getPageLinks(crawlURL);
        ArrayList<String> filteredLinks = new ArrayList<>();

        if (links != null) {
            for (String link : links) {
                boolean cond = Util.isOneSubdomainOfTheOther(link, Main.SEED_URL);
                if (cond) {
                    if (!(Main.newURLs.contains(link) || Main.crawledURLs.contains(link))) {
                        output += link + "\n";
                        Main.newURLs.add(link);
                    }
                }
            }

        }

        output += "---------------------\n";
        System.out.println(output);
    }
}
