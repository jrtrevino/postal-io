package com.postal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

/**
 * Challenge1 class. Run by invoking the run() method. See constructor for
 * details on object creation.
 */
public final class Challenge1 {
    private String endpoint;
    private String[] queries;
    private String search;
    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private final int statusOK = 200;

    /**
     * @param url          -> String containing an http(s) URI.
     * @param queryStrings -> An array of strings of the format query=string
     * @param searchWord   -> the word we will search for in our HTTP response.
     */
    public Challenge1(String url, String[] queryStrings, String searchWord) {
        endpoint = url + "?"; // append query string operaetor
        queries = queryStrings;
        search = searchWord;
    }

    /**
     * Triggers an endpoint and returns the response body if successful.
     *
     * @param endpoint -> a String containing the URI endpoint (without
     *                 querystrings) to request
     * @param queries  -> An array of strings of the form queryString=value.
     * @return -> String containing the response body, or null.
     */
    private String getHTTPResponse(String url, String[] queryStringArray) {
        String uri = url + String.join("&", queryStringArray); // create our final URI with endpoint & query strings
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(uri)).setHeader("User-Agent", "Java Bot")
                .build();
        HttpResponse<String> response;
        try {
            // perform synchronous request
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == statusOK) {
                return response.body();
            }
            System.out.println("Error fetching resource!");
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Parses a response from getHTTPResponse and returns the frequency of a
     * provided word.
     *
     * @param response     -> A string returned from getHTTPResponse()
     * @param wordToSearch -> A string containing the word to search for
     * @return -> an integer detailing the frequency of the word.
     */
    private int parseHTTPResponse(String response, String wordToSearch) {
        // unwrap our json object: parse contains an attribute named 'text', which
        String wikiResponse = new JSONObject(response).getJSONObject("parse").getJSONObject("text").getString("*");
        return StringUtils.countMatches(wikiResponse, wordToSearch);

    }

    /**
     * Wrapper for our program. Call this method to begin the program.
     */
    public int run() {
        int wordCount = 0;
        if (endpoint != null && queries != null) {
            String response = getHTTPResponse(endpoint, queries);
            if (response != null) {
                wordCount = parseHTTPResponse(response, search);
                String output = String.format("Number of occurences found for word %s: %d", search, wordCount);
                System.out.println(output);
                return wordCount;
            } else {
                System.out.println("Could not find occurence of provided word due to malformed HTTP response.");
            }
        } else {
            System.out.println("Error injecting dependencies.");
        }
        return -1;

    }
}
