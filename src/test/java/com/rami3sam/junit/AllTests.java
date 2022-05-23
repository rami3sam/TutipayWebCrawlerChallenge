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
            pagePath = new URI("http://monzo.com/aaa");
        }catch (URISyntaxException e){

        }
    }

    @Test
    void test(){
        assertEquals("http://monzo.com/blog",Util.rebaseIfRelativeUrl(pagePath,"blog"));
        assertEquals("http://monzo.com/aaa/blog",Util.rebaseIfRelativeUrl(pagePath,"blog"));
    }
}
