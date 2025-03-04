# emp-salary-report-generator
Project is responsible for generating employee salary report 
from the csv input file.

CSV file should contain the data in the below format </br>
Note : CEO's managerId will be empty

| Id  | firstName | lastName | salary | managerId |
| --- | --------- | -------- | ------ | --------- |
| 123 | Joe       | Doe      | 60000  |           |
| 124 | Martin    | Chekov   | 45000  | 123       |
| 125 | Bod       | Ronstad  | 47000  | 123       |
| 300 | Alice     | Hasacat  | 52000  | 124       |
| 305 | Brett     | Hardleaf | 34000  | 300       |

Generates the following scenarios
1. which manager earns less than they should, and by how much
2. which manager earns more than they should, and by how much
3. which employees have a reporting line which is too long, and by how much

# requirement
1. java 8
2. maven 3.x.x
3. git cli 2.x.x

# Repository clone
1. clone the git repository in to your local
````
git clone <ssh/https link>
````
# Running the application
Follow any one of the approach to run the application

# Approach 1
````
mvn clean install 
java -jar <Path to /target/emp-salary-report-generator.jar>
enter the complete CSV file path when prompted
enter reporting line threshold when prompted (ex: 4)
````
# Approach 2
````
mvn clean install
java -jar <Path to .jar file>  <csv file path> <reporting line threshold>
ex :  java -jar .\target\emp-salary-report-generator.jar C:\Users\Documents\employee.csv 2
````

# Developer
Name  : Veera Venkata Sai Santhosh Kalla <br/>
Email : veeravenkatasai87@gmail.com