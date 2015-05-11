##### Web/Browser-based Java Development Environment to develop/compile/execute Simple Java programs using Touchdevelop based touch input keypads

* Touchdevelop editor is developed by Microsoft which supports its native TouchDevelop language to develop programs based on touch or keyboard with features like  intelligent code completion, copy&paste, refactorings (rename, extract function, parametrize, simplify by variable extraction), automatic replication of scripts on all user’s devices etc. https://www.touchdevelop.com/

* The same concept of developing programs based on touch and keyboard has been employed for Java language in this project.

##### Steps to check out and configure project

1. Configure Eclipse IDE to run web applications.
2. Checkout TouchJDE project from GitHub repository (https://github.com/jaggi-sg/touchJDE) from eclipse or download the zip directly from github.
3. Navigate to touchjde.properties file and change the following properties
   - JAVA_HOME: to the java jdk folder in your machine
   - FILE_STORAGE_DIRECTORY: to the directory in which you desire to store the created java files
   - DATABASE_SERVER: The MySQL database server along with database name
   - DATABASE_USERNAME: Your MySQL username
   - DATABASE_PASSWORD: Your MySQL password
4. Run the web application from index.jsp, that will be your launching file.
