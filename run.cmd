@echo off

rem   In this example, any third-party library JARs are located in 'lib' and added to the classpath [-classpath option].
rem   Remove this "lib\*" entry from the classpath if you're not using any, which results in the following:
rem   java -classpath blackjack-1.0.jar com.games.blackjack.client.Main

rem   Note that your application JAR stays on the classpath, regardless of whether you're using additional libraries or not.
java -classpath bmcc-combat-1.1.jar;"lib\*";"lib\jackson-2.12.5\*" com.bmcc.client.Main