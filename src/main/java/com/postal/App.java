package com.postal;

/**
 * Runs the output for the two challanges provided by Postal.io.
 */
final class App {

    private App() {
        throw new AssertionError("Instantiating utility class...");
    }

    /**
     * Wrapper for both challanges..
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        // Challenge 1 variables
        System.out.println("Running challenge 1.");
        String endpoint = "https://en.wikipedia.org/w/api.php";
        String[] queries = {"action=parse", "section=0", "prop=text", "format=json", "page=Cincinnati"};
        String wordSearch = "Cincinnati";
        Challenge1 app = new Challenge1(endpoint, queries, wordSearch);
        app.run();
        System.out.println("Running challenge 2.");
        Challenge2 app2 = new Challenge2("log20170630.csv");
        app2.run();

    }
}
