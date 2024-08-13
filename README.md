"# RetailMicroserviceCommunication" 

Setting Up an Angular Project
To setup an angular Project:

1.Download the Latest LTS version of Node.js and npm from the Node.js website.

2.Use the command prompt or terminal and install Angular CLI 16 globally by running:

npm install -g @angular/cli@16

3.create a new angular Project

ng new retailSystem

4.Move into the project directory

cd retailSystem

5.Start the Angular application with the following command:
 
ng serve or npm start

by default apllication runs on the port number 4200 and url will be http://localhost:4200/


At java Application, at controller class at the class level declaration write below annotation

@CrossOrigin(origins="http://localhost:4200")

and change the sterotype annotation to @RestController


