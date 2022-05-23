package com.rami3sam.crawler;

import java.util.ArrayList;
import java.util.HashSet;

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
        ArrayList<String> filteredLinks = new ArrayList<>();

        if (links != null) {
            links.removeAll(Main.crawledURLs);
            links.removeAll(Main.newURLs);

            for (String link : links) {
                if (Util.isOneSubdomainOfTheOther(link, Main.SEED_URL)) {

                    filteredLinks.add(link);
                }
            }

            HashSet<String> uniqueLinks = new HashSet<>();
            uniqueLinks.addAll(filteredLinks);
            for (String link : uniqueLinks) {
                output += link + "\n";
            }
            Main.newURLs.addAll(filteredLinks);
        }


        output += "---------------------\n";
        System.out.println(output);
    }
}
