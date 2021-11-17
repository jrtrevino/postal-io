package com.postal;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
class AppTest {
    private String endpoint = "https://en.wikipedia.org/w/api.php";
    private String[] queries = {"action=parse", "section=0", "prop=text", "format=json", "page=Cincinnati"};

    /**
     * Rigorous Test.
     */
    @Test
    void test1() {
        String wordSearch = "Cincinnati";
        final int answer = 176;
        Challenge1 app = new Challenge1(endpoint, queries, wordSearch);
        assertEquals(answer, app.run());
    }

    @Test
    void test2() {
        String wordSearch = "Highway";
        Challenge1 app = new Challenge1(endpoint, queries, wordSearch);
        int answer = 2;
        assertEquals(answer, app.run());
    }

    @Test
    void test3() {
        String wordSearch = "Metro";
        Challenge1 app = new Challenge1(endpoint, queries, wordSearch);
        final int answer = 6;
        assertEquals(answer, app.run());
    }

    @Test
    void test4() {
        String wordSearch = "ohio";
        Challenge1 app = new Challenge1(endpoint, queries, wordSearch);
        int answer = 0;
        assertEquals(answer, app.run());
    }

    @Test
    void test5() {
        String wordSearch = "Incorporated";
        Challenge1 app = new Challenge1(endpoint, queries, wordSearch);
        int answer = 2;
        assertEquals(answer, app.run());
    }

    @Test
    void test6() {
        Challenge2 app = new Challenge2("log20170630.csv");
        Map<String, Integer> codeAnswers = new HashMap<String, Integer>();
        final int uniqueIP = 78181;
        final double bytesTransferred = 3821351877238.0;
        final int num0 = 178290;
        final int num200 = 16203776;
        final int num206 = 419055;
        final int num301 = 4899498;
        final int num304 = 449732;
        final int num400 = 12909;
        final int num403 = 643613;
        final int num404 = 80767;
        final int num429 = 640877;
        final int num500 = 446;
        final int num502 = 294;
        final int num503 = 147948;
        final int num504 = 286;

        // these answers were obtained using python pandas library.
        codeAnswers.put("200.0", num200);
        codeAnswers.put("301.0", num301);
        codeAnswers.put("403.0", num403);
        codeAnswers.put("429.0", num429);
        codeAnswers.put("304.0", num304);
        codeAnswers.put("206.0", num206);
        codeAnswers.put("0.0", num0);
        codeAnswers.put("503.0", num503);
        codeAnswers.put("404.0", num404);
        codeAnswers.put("400.0", num400);
        codeAnswers.put("500.0", num500);
        codeAnswers.put("502.0", num502);
        codeAnswers.put("504.0", num504);
        app.run();
        assertEquals(uniqueIP, app.getIPAddresses().size());
        assertEquals(bytesTransferred, app.getBytes());
        assertTrue(codeAnswers.equals(app.getStatusCodes()));
    }
}
