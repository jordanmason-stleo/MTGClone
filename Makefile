JCC = javac

JFLAGS = -g

default:
	@echo \"make build\" to compile Java classes
	@echo \"make run\" to run Driver class
	@echo \"make jar\" to compile executable jar
	@echo \"make clean\" to clean up artifacts
build: 
	$(JCC) $(JFLAGS) Driver.java
run: 
	$(JCC) $(JFLAGS) Driver.java
	java Driver
jar:
	$(JCC) $(JFLAGS) Driver.java
	@echo "Manifest-Version: 1.0" > manifest.txt
	@echo "Class-Path: ." >> manifest.txt
	@echo "Main-Class: Driver" >> manifest.txt
	@echo "" >> manifest.txt
	jar -cmf manifest.txt MTGClone.jar *.class
	$(RM) manifest.txt
	$(RM) *.class
clean:
	$(RM) *.class
	$(RM) manifest.txt
	$(RM) MTGClone.jar
