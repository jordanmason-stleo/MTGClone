JCC = javac
MAIN_CLASS = Driver
OUT_JAR = MTGClone.jar
SQLITE_JDBC_VER = 3.32.3.2
SQLITE_JDBC_JAR = sqlite-jdbc-$(SQLITE_JDBC_VER).jar
SQLITE_JDBC = https://github.com/xerial/sqlite-jdbc/releases/download/$(SQLITE_JDBC_VER)/$(SQLITE_JDBC_JAR)
JFLAGS = -g -classpath ".:$(SQLITE_JDBC_JAR)"

default:
	@echo \"make build\" to compile Java classes
	@echo \"make run\" to run Driver class
	@echo \"make jar\" to compile executable jar
	@echo \"make clean\" to clean up artifacts

sqlite_jdbc:
	wget -N $(SQLITE_JDBC)

build: sqlite_jdbc
	$(JCC) $(JFLAGS) $(MAIN_CLASS).java

run: jar 
	java -jar $(OUT_JAR)

jar: build
	@echo "Manifest-Version: 1.0" > manifest.txt
	@echo "Class-Path: ./sqlite-jdbc-3.32.3.2.jar" >> manifest.txt
	@echo "Main-Class: $(MAIN_CLASS)" >> manifest.txt
	@echo "" >> manifest.txt
	jar -cmf manifest.txt $(OUT_JAR) *.class
	$(RM) manifest.txt
	$(RM) *.class

clean:
	$(RM) *.class
	$(RM) cards.db
	$(RM) manifest.txt
	$(RM) $(OUT_JAR)
	$(RM) $(SQLITE_JDBC_JAR)
