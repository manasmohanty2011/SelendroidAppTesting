# Coding Assessment for selendroid app
## Functional test on Selendroid App

# Test Cases
## 1. New user registration and validating details on confirmation page
## 2. Verify EN button functionality
## 2. Verify Display Text View button functionality
## 2. Verify Show Progress Bar For A While button functionality

#Automation Architecture: 
## 1. Project build management tool - Maven
## 2. Automation Framework - TestNG
## 3. Object Repository - Page factory

# Installation Pre-requisites for executing on local system:
## Following tools must be installed to run the test case on local machine:
	Install JDK
	Install IntelliJ
	Install Android Studio
	Install Android SDK
	Install Appium for Windows
	
# Steps to execute the code on local system:
## Open the imported project in IntelliJ
## Start the Appium Server on the following address: Host:Port = localhost:4723
## this can start from code also, currently code commented
## Start android emulator and provide device name in config.properties file [Use adb devices command in command line]

##Open the terminal and type maven command mvn -DsuiteXml=AllTestSuite.xml resources:resources test

# Note: This test will execute all testcases mentioned under AllTestSuite.xml file
## Extent Report will be generated in the following folder: report folder
## testdata has been kept in testdata/testData.xlsx file

# Installation Pre-requisites for executing on jenkins system:
## Following tools must be installed Jenkins machine:
    Install Jenkins
	Install JDK
	Install Android Studio
	Install Android SDK
	Install Appium for Windows
	
# Steps to execute the code on jenkins:
## Open the Jenkins from browser and configure new item with freestyle project
## Define gitHub repository
## Select maven build and provide command -DsuiteXml=AllTestSuite.xml resources:resources test
## Start the Appium Server on the following address: Host:Port = localhost:4723
## this can start from code also, currently code commented
## Build / scheduled Jenkins jobs# SelendroidAppTest
