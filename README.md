Web Automation Script for Himalayas.app JobFilter Functionality


How to Input Test Data
----------------------
Input Data in the File 'TestData/TestData.xlsx'

____________________________________________________________________________________
       Column Name            |
_________________________________________________________________________________
Include Test Case in run      | if value is 'N' , Testcase won't run in Execution
                              | 
TestCase ID                   | Inicates the Test Case ID(for Reference Only)
                              |
Job Title                     | Value used to search in Job Title field
                              |
Country or Time zone          | Value used to search in Country or Time zone field
                              |
Experience Level              | List of Fields to be selected in Experiance level filter
                              |
Currency                      | Currency to be selected in Salary Range
                              |
Salary Range Start            | Start value of the Salary Range
                              |
Salary Range End              | End value of the Salary Range
                              |
Include Jobs without salary   | if given 'FALSE' with untick, include jobs without salary fiels
                              |
Companies                     | List of Fields to be selected in Companies Filter
                              |
Job Type                      | Lsit of Fields to be selected in Job Type Filter
                              |
Employee Benefit              | List of Fields to be selected in Employee Benefit Filter
                              |
Market                        | List of Fields to be selected in Market Filter
                              |
Expected Search Output        | List of Expected Job Names to be filtered out
                              | 


How to Run the Scritpt
-----------------------

1) Run testng.xml as Testng Suit
2) From command line, use the command 'mvn test -DxmlFilePath=testng.xml'

How to view the Result Report
------------------------------
ExtentReport can be viewd in 'reports/index.html'

