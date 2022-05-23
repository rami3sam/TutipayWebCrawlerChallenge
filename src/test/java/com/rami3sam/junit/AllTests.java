package com.rami3sam.junit;

import com.rami3sam.crawler.Util;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;


public class AllTests {
    URI pagePath;
    @BeforeEach
    void setUp(){
        pagePath = new URI("http://monzo.com");
    }

    @Test
    void test(){
        assertTrue(true);
    }
}
