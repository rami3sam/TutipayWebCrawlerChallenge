package com.rami3sam.junit;

import com.rami3sam.crawler.Util;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class AllTests {

    @Test
    void testProcessURLFunction() {

        URI URI1 = null;
        URI URI2 = null;

        try {
            URI1 = new URI("https://rami3sam.com/blog");
            URI2 = new URI("https://google.com/a/b/c/");
        } catch (URISyntaxException e) {
            fail("Failed to create URI Object for tests");
            return;
        }

        LinkedList<URLTest> testCases = new LinkedList<>();

        // test for a relative to root url
        testCases.add(new URLTest("https://rami3sam.com/about", URI1, "/about"));
        // test for empty url
        testCases.add(new URLTest("https://rami3sam.com/blog", URI1, ""));
        // test for scheme relative url
        testCases.add(new URLTest("https://google.com/blog", URI1, "//google.com/blog"));
        // test for full url
        testCases.add(new URLTest("https://google.com", URI1, "https://google.com/"));
        //test for only a query
        testCases.add(new URLTest("https://rami3sam.com/blog?p=123", URI1, "?p=123"));
        //test for fragment removal
        testCases.add(new URLTest("https://rami3sam.com/blog", URI1, "//rami3sam.com/blog#aboutus"));
        //testing for relative url and the current page ends with a slash
        testCases.add(new URLTest("https://google.com/a/b/c/d", URI2, "d"));
        // test for / meaning root of the website
        testCases.add(new URLTest("https://rami3sam.com", URI1, "/"));
        for (URLTest testCase : testCases) {
            assertEquals(testCase.getExpected(), Util.processURL(testCase.getPageURI(), testCase.getURL()), "Failed on case: " + testCase.expected);
        }

    }

    @Test
    void testGetPageLinksFunction() {
        ArrayList<String> links = Util.getPageLinks("https://monzo.com");

        String[] expectedLinks = {"https://monzo.com", "https://monzo.com/features/energy-switching", "https://monzo.com/i/savingwithmonzo", "https://monzo.com/isa", "https://monzo.com/i/monzo-premium"};
        if (links != null) {
            for (String expectedLink : expectedLinks) {
                assertTrue(links.contains(expectedLink), "Failed on link: " + expectedLink);
            }
        }else{
            fail("cannot connect to the test page");
        }

    }

    private class URLTest {
        private final String expected;
        private final String URL;
        private final URI pageURI;

        public String getExpected() {
            return expected;
        }

        public String getURL() {
            return URL;
        }

        public URI getPageURI() {
            return pageURI;
        }

        public URLTest(String expected, URI pageURI, String URL) {
            this.expected = expected;
            this.URL = URL;
            this.pageURI = pageURI;
        }
    }
}
