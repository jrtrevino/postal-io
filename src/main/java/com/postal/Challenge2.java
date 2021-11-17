package com.postal;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Challenge two Object. Instantiate with a filename string and
 * call .run() method to print metrics.
 */
public class Challenge2 {
    private String dataFileName;
    private double bytes;
    private Set<String> ipAddresses;
    private Map<String, Integer> statusCodes; // key: statusCode, val: frequency
    private List<String> csvHeaders;

    /**
     * @param filename -> String representing a CSV filename. File must be in main/resources.
     */
    public Challenge2(String filename) {
        dataFileName = filename;
        bytes = 0;
        ipAddresses = new HashSet<String>();
        statusCodes = new HashMap<String, Integer>();
    }

    /**
     * Parses the file with name dataFileName.
     * Calculates the number of unique IP addresses, status code frequencies,
     * and the number of bytes transferred.
     */
    private void parseFile() {
        try {
            // read csv file from maven resource folder.
            InputStream dataIn = Challenge2.class.getClassLoader().getResourceAsStream(dataFileName);
            CSVReader csvReader = new CSVReader(new InputStreamReader(dataIn));
            String[] lines; // will temporarily hold individual csv lines.
            int lineCounter = 0;
            while ((lines = csvReader.readNext()) != null) {
                if (lineCounter == 0) {
                    // assign csv headers for indexing into csv row.
                    csvHeaders = Arrays.asList(lines);
                    lineCounter += 1;
                    continue;
                }
                // Hash IP
                ipAddresses.add(lines[csvHeaders.indexOf("ip")]);
                // Add bytes
                bytes += Double.parseDouble(lines[csvHeaders.indexOf("size")]);
                // Add or increment status code
                String codeKey = lines[csvHeaders.indexOf("code")];
                if (codeKey.length() != 0) {
                    Integer statusCodeCount = statusCodes.get(codeKey);
                    if (statusCodeCount == null) {
                        statusCodeCount = 1; // statusCode not in hashmap
                    } else {
                        statusCodeCount += 1;
                    }
                    statusCodes.put(codeKey, statusCodeCount);
                }
                // we can add additional line parsing below.

            }
        } catch (IOException e) {
            System.out.println("Could not create input stream.");
            System.out.println(e);
        }
    }

    /**
     * Runs the challenge. Prints out statistics required by challenge doc.
     */
    public void run() {
        parseFile();
        System.out.println(String.format("Number of unique masked IP addresses found: %d", ipAddresses.size()));
        System.out.println(String.format("Total bytes transferred: %f", bytes));
        System.out.println("Printing status codes and frequencies.");
        int totalCodes = 0;
        for (String key : statusCodes.keySet()) {
            int val = statusCodes.get(key);
            totalCodes += val;
            System.out.println(key + ": " + val);
        }
        System.out.println(String.format("Total Codes: %d", totalCodes));

    }
}
