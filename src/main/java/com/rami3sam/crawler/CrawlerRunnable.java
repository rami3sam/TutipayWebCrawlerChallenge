package com.rami3sam.crawler;

import java.util.ArrayList;

public class CrawlerRunnable implements Runnable {
    String crawlURL;

    public CrawlerRunnable(String url) {
        crawlURL = url;
    }

    @Override
    public void run() {
        Main.crawledURLs.add(crawlURL);
        String output = "";
        output += "---------------------\n";
        output += "crawling url : " + crawlURL + "\n";

        ArrayList<String> links = Util.getPageLinks(crawlURL);
        if (links != null) {
            links.removeAll(Main.crawledURLs);
            links.removeAll(Main.newURLs);
            for (String link : links) {
                output += link + "\n";
            }


            Main.newURLs.addAll(links);
        }



        output += "---------------------\n";
        System.out.println(output);
    }
}
