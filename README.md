# SimpleJavaHTTP

From command line, navigate to folder where APIAccess.java is located and compile using the command: 

    javac -cp ".:./json-20180813.jar" APIAccess.java

There are 2 options for running...
First is a POST request to login. Run command:

    java -cp ".:./json-20180813.jar" APIAccess URL_FOR_LOGIN USERNAME PASSWORD post
    
Replace the three arguments with your own.

Second is a GET request to whatever valid endpoint you decide to use.
Run command: 

    java -cp ".:./json-20180813.jar" APIAccess URL_FOR_GET USERNAME PASSWORD get
    
Again, replace the 3 arguments with your own.  

If you'd like to access a different POST endpoint, refer to the API documentation and modify the code accordingly.
