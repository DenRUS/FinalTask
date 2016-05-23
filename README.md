# FinalTask
The program performs simple operations with ordinary fractions.

Замечания по текущей версии:

+1. кодировка исходного кода должна быть UTF-8

+2. исключить все bin файлы и файлы, которые создает IDE из репозитория (ignore), добавить скрипт сборки pom.xml

+3. README.md не содержит никакой полезной информации - как собрать и пользоваться?

4. отсутствуют тесты, нет возможности проверить работоспособность

+5. класс Calc перегружен, исключительно статические операции, разделить на отдельные классы, сейчас это процедурный подход с множественным дублированием, такой код невозможно сопровождать

+6. разделить классы по пакетам

+7. в выходной файл добавить информацию об операции, соответствующей результату

+8. отсутствуют JavaDoc комментарии

?9. Вывод неинформативен

To build this project: (you need Maven)
1) At command line: "mvn install"
You will get a jar at ./tagret directory. 
2) Add "Main-Class: calculator.Calc" line in the META-INF/MANIFEST.MF at the jar.
You can run my program with "java -jar FracCalcArtifact-0.0.1-SNAPSHOT.jar <arguments>"
Where arguments could be:
1. Expressions with ordinary fractions to calculate(like 2/3+3/2;1/2-3/2) etc. You will get result at the command line.
2. Filename with expressions with ordinary fractions to calculate. You will get result at output.xml at directory of the jar.