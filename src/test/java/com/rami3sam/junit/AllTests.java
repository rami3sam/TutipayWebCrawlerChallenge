package com.rami3sam.junit;

import com.rami3sam.crawler.Util;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import com.rami3sam.crawler.Util;

import java.net.URI;
import java.net.URISyntaxException;

public class AllTests {
    URI pagePath;
    @BeforeEach
    void setUp(){
        try {
            pagePath = new URI("https://rami3sam.com/blog");
        }catch (URISyntaxException e){

        }
    }

    @Test
    void test(){
        // test for a relative to root url
        assertEquals("https://rami3sam.com/about",Util.processURL(pagePath,"/about"));
        // test for empty url
        assertEquals("https://rami3sam.com/blog",Util.processURL(pagePath,""));
        // test for scheme relative url
        assertEquals("https://rami3sam.com/blog",Util.processURL(pagePath,"//rami3sam.com/blog"));
        // test for full url
        assertEquals("https://google.com",Util.processURL(pagePath,"https://google.com/"));
        //test for only a query
        assertEquals("https://rami3sam.com/blog?p=123",Util.processURL(pagePath,"?p=123"));
        //test for fragment removal
        assertEquals("https://rami3sam.com/blog",Util.processURL(pagePath,"//rami3sam.com/blog#aboutus"));
    }
}
