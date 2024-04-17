# CookingWithLove-Testing

**README**

PHP Project Testing
CIS 565 Project - Winter 2024

_Downloading Project Files from Github_
The project files are available at:
https://github.com/Maisa-allala/CookingWithLove-Testing.git
Please download the zip file and extract it into your downloads folder. 

_Setting up web application_
Install XAMPP at https://www.apachefriends.org/download.html
Move the included php.ini and my.ini files to <xampp_install_directory>\php and <xampp_install_directory>\mysql\data respectively
Using localhost/phpmyadmin, MySQLWorkbench, or similar applications, create the database and tables using the .sql files in the CookingWithLove-Testing-main\Database folder
Place the whole folder titled “CookingWithLove” into <xampp_install_directory>\htdocs
Start the apache & MySQL services in XAMPP
Open a web browser and navigate to localhost\CookingWithLove (note: If your ports are not set to use 80 & 443 for Apache or 3306 for MySQL the project will not work correctly)
Web application should load successfully

_Setting up Eclipse_
After unzipping the downloaded files from GitHub import the folder “CookingWIthLoveTestCode” into Eclipse. 
TestNG tests can be found in the src directory under the packages Database Testing and UI Testing. 
To run the TestNG Suite, select the testNG.xml file and select “Run as -> TestNG suite”
