# File Search Console Application

## Overview

This application provides a console based search functionality
on text files indexed from a provided directory. For this 
implementation, only the first level of files with `.txt` extension
are considered for indexing.

## Features

This application has the following features - 

- Indexing a given local directory for all the text files 
  in the same level. It doesn't create recursive indexes, 
  however, it could be extended in the future.
  
- Provides an interactive console to the user to enter a
search keyword like - "This is a search keyword".
  
- Provides search result from the pre-indexed files along 
  with their ranks for top 10 matches.
  
- If there are no matches, then "no matches found" will be 
  displayed.
  

## Building Instructions

This is a maven project built using maven 3.6 and Java 11.

The following software needed to build this 
project from source -

- maven 3.6
- Java 11

To build the JAR file - 

```shell
mvn clean package

or 

make jar
```

To start the application - 

```shell
java -jar target/av-file-search-1.0.jar <pathToDirectoryContainingTextFiles>
```

## Implementation Details

The implementation of the application is distributed in the following
two sections. 

### Building Indexes

After initialization, the program scans through the directory and consider
only the files with `.txt` extension in the first level. It will 
ignore any directory present in the root directory.

These indexes are stored in a `Map` representation within the
`Directory` object along with the content of each file. In order
to build efficient indexing, parallel processing is utilized.

### Performing Search and Matching Rank

After scanning and indexing the files, the user is prompted for the
search keyword. The following search strategy is used, however, it 
could be enhanced/extended with more complex strategy as a future
enhancement - 

The text entered by the user is broken down to words using whitespace ` `
as delimiter. For example - 

`This is a search keyword` will be broken down to five words - 
`This`, `is`, `a`, `search`, and `keyword`. Now, each of these
broken words would be matched against the available indexed files.

The matching rank is determined based on the no. of match on the
list of words against all the words from the keyword. So for the
above example, if only `This` matches in a file then it will be
a `1/5` match. Expressed in decimal - `20%`


## Unit Testing

Unit tests are written using Junit5 Jupiter and Mockito.

