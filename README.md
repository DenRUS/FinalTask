# FinalTask
The program performs simple operations with ordinary fractions.

To build this project: (you need Maven)
1) At command line: "mvn install"
You will get a jar at ./tagret directory. 
2) Add "Main-Class: calculator.Calc" line in the META-INF/MANIFEST.MF at the jar.
You can run my program with "java -jar FracCalcArtifact-0.0.1-SNAPSHOT.jar <arguments>"
Where arguments could be:
1. Expressions with ordinary fractions to calculate(like 2/3+3/2;1/2-3/2) etc. You will get result at the command line.
2. Name of the file with expressions with ordinary fractions to calculate. You will get result at output.xml at directory of the jar.

Note: for system reasons expressions like "3/2 * 2/3" could be incorrect. Try "3/2*2/3"
