
# About

This is the repository for Postal.io's challenges provided for my interview process.

Inside of the src/ folder contains three Java files. `App.java` is the entrypoint to run both challenges.
`Challenge1.java` and `Challenge2.java` contain the source code for both challenges. 
More details about the challenges are below.

## Challenge 1

### About

This program counts the number of occurences of a word from the Cincinnati wikipedia page.
The default word when running from main is `Cincinatti`, case sensitive. To provide a different word,
endpoint, or query string(s), you can instantiate the class object like so:

```
String endpoint = "https://en.wikipedia.org/w/api.php";
String[] queries = { "action=parse", "section=0", "prop=text", "format=json", "page=Cincinnati" };
String wordSearch = "<your word here>";

Challenge1 app = new Challenge1(endpoint, queries, wordSearch);
int wordCount = app.run();
```

This program's `run` method returns the word count as an integer.

### Challenge1 Output

When running this program with the default input, the following output is produced:

```
Running challenge 1.
Number of occurences found for word Cincinnati: 176
```

### Wikipedia API

This program only works for Wikipedia API queries. This is due to the assumption regarding the response format of the API.
For more information, read about the API [here](https://www.mediawiki.org/wiki/API:Main_page).

## Challenge2

For this challenge, three metrics are calculated from a large .csv file containing apache log information:
- Number of unique (masked) IP addresses
- Frequency of HTTP Status Codes
- Number of bytes transferred.

Once these metrics are calculated, the results are printed to standard out.

### CSV File

The location of the CSV file needs to be located in `src/main/resources`.
Please read the note on the bottom of this README about the CSV.
Adding more files to this location allows for more parsing, ***assuming these 
additional files also contain the `size`, `code`, and `ip` headers***.

A class object can be instantiated like so:

```
Challenge2 app2 = new Challenge2("filename.csv");
```

Simply provide the filename without any path due to Maven's build process.

### Challenge2 Output

This class's `run()` method will produce the following output for file "log20170630.csv",
located [at this site](www.sec.gov/dera/data/Public-EDGAR-log-file-data/2017/Qtr2/log20170630.zip):
```
Running challenge 2.
Number of unique masked IP addresses found: 78181
Total bytes transferred: 3821351877238.000000
Printing status codes and frequencies.
200.0: 16203776
400.0: 12909
429.0: 640877
404.0: 80767
403.0: 643613
206.0: 419055
0.0: 178290
301.0: 4899498
500.0: 446
502.0: 294
504.0: 286
503.0: 147948
304.0: 449732
Total Codes: 23677491
```

### Potential Issues?

Certain entries of the log file contained missing values for the `code` field.
When this occurs, the line is simply skipped.

# Building & Running

To limit the size of the repository, the CSV file for challenge two
***is not included in the repository.*** Please place log20170630.csv inside of
src/main/resources. The filename is already set in `App.java`'s main function.


This program uses maven to build a jar file and docker to containerize it. To build and run this program, type:

```
$ docker build -t challenges .
$ docker run -i challenges
```
