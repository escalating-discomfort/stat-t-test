# stat-t-test
Runs a statistical T-test on a provided pattern/sample. More info: [Student's T-test](https://en.wikipedia.org/wiki/Student%27s_t-test)
## Example
```
statTtest.java 150 95 ..\resources\pattern.txt
```
Supplied CLI in order: *μ0, significance level, path to a text file containing the sample*

Numbers in the file can be delimited by commas, semicolons, or spaces; and additionally grouped into any amount of lines as newlines are omitted.
## Built with
[**Maven**](https://maven.apache.org/) - Dependency Management
