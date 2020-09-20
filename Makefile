JCC = javac
MAIN_CLASS = Driver
OUT_JAR = MTGClone.jar

JFLAGS = -g

default:
	@echo \"make build\" to compile Java classes
	@echo \"make run\" to run Driver class
	@echo \"make jar\" to compile executable jar
	@echo \"make clean\" to clean up artifacts

build: 
	$(JCC) $(JFLAGS) $(MAIN_CLASS).java

run: jar 
	java -jar $(OUT_JAR)

jar: build
	@echo "Manifest-Version: 1.0" > manifest.txt
	@echo "Class-Path: ." >> manifest.txt
	@echo "Main-Class: $(MAIN_CLASS)" >> manifest.txt
	@echo "" >> manifest.txt
	jar -cmf manifest.txt $(OUT_JAR) *.class
	$(RM) manifest.txt
	$(RM) *.class

clean:
	$(RM) *.class
	$(RM) manifest.txt
	$(RM) $(OUT_JAR)
