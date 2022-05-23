package com.rami3sam.junit;

import com.rami3sam.crawler.Util;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;

public class AllTests {

    @Test
    void test(){

        URI URI1 = null;
        URI URI2 = null;

        try {
             URI1 = new URI("https://rami3sam.com/blog");
             URI2 = new URI("https://google.com/a/b/c/");
        }catch (URISyntaxException e){
            System.err.println("Failed to create URI Object for tests");
            return ;
        }

        LinkedList<URLTest> testCases = new LinkedList<>();

        // test for a relative to root url
        testCases.add(new URLTest("https://rami3sam.com/about", URI1,"/about"));
        // test for empty url
        testCases.add(new URLTest("https://rami3sam.com/blog", URI1,""));
        // test for scheme relative url
        testCases.add(new URLTest("https://google.com/blog", URI1,"//google.com/blog"));
        // test for full url
        testCases.add(new URLTest("https://google.com", URI1,"https://google.com/"));
        //test for only a query
        testCases.add(new URLTest("https://rami3sam.com/blog?p=123", URI1,"?p=123"));
        //test for fragment removal
        testCases.add(new URLTest("https://rami3sam.com/blog", URI1,"//rami3sam.com/blog#aboutus"));
        //testing for relative url and the current page ends with a slash
        testCases.add(new URLTest("https://google.com/a/b/c/d", URI2,"d"));

        for (URLTest testCase : testCases){
            assertEquals(testCase.expected,Util.processURL(testCase.pageURI,testCase.URL));
        }

    }

    private class URLTest{
        String expected;
        String URL;
        URI pageURI;

        public URLTest(String expected,URI pageURI, String URL) {
            this.expected = expected;
            this.URL = URL;
            this.pageURI = pageURI;
        }
    };
}
