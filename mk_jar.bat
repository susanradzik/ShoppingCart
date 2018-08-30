echo mk_jar ClassWithMain
call javaPath
javac *.java
echo Main-Class: %1 > manifest.txt
jar cvfm %1.jar manifest.txt *.class
rem hit enter to run it
rem prompt 
java -jar %1.jar
