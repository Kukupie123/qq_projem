# ACTION FLOWS CLIENT&SERVER
1. Signup :

Client will send email and password as the payload

Server will create a record in database
----------------------------------------------------------------------------------------------------------------------------------------------------------
2. Signin :

Client will send email and password as the payload

Gateway will then forward this to AuthMC

AuthMC will then generate JWT token and return it back to gateway who will return it to the client
----------------------------------------------------------------------------------------------------------------------------------------------------------
3. Creating Root project :

Client will need to pass a payload with the following details : {project details such as title, desc}

Client will need to pass in authorization header which is going to be used to extract the userID

Server will check if a rule exist which satisfy the rule of the project and return the ID of the rule

Server will then add a new project in record.

Server will add the the user who created as the leader of the project
----------------------------------------------------------------------------------------------------------------------------------------------------------
