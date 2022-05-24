package com.rami3sam.crawler;

import java.util.ArrayList;
import java.util.LinkedList;

public class CrawlerRunnable implements Runnable {
    String crawlURL;

    public CrawlerRunnable(String url) {
        crawlURL = url;
    }

    @Override
    public void  run() {

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
                        filteredLinks.add(link);
                        Main.newURLs.add(link);
                    }
                }
            }
        }

        if (filteredLinks.size() == 0)
        {
            output += "No new links were found \n";
        }else{
            output += filteredLinks.size() + " new links were found \n";
            for (String link : filteredLinks){
                output += link + "\n";
            }
        }

        output += "---------------------\n";
        System.out.println(output);
    }
}
