# Introduction

This is just another JasperReport Hello World, created to illustrate reporting capabilities for a friendly project which is printing order forms and invoice. These documents have the following form:

 - header containing company logo and company details, including client details
 - data table containing order information
 - footer containing 

# Prerequisites

To run this project you will need to install:

 - Java 17
 - Maven, version 3.9.4 or greater
 
In case you would like to modify reports, be sure to download JasperSoft community report editor. Can try this link https://community.jaspersoft.com/files/file/41-jaspersoft-community-edition/

# How to run the project

Many options are available from your IDE, while from console you can use this command:

>  mvn clean compile exec:java

On the first run it may take longer to download all dependencies... in the end it will create a few report files in project root:

 - helloCarReport.csv
 - helloCarReport.html
 - helloCarReport.pdf
 - helloCarReport.xlsx
 
# How to customize

All of the business logic is in the Main class, with a little bit in SimpleReportExporter and SimpleReportFilter. Just use what you know to:

 - Customize report template via JasperSoft
 - Customize report parameters
 - Customize report datasource
