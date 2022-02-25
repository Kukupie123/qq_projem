#APIs
AuthMC APIs
```
URL : auth/signin
Method : POST
Desc : Sign in for users
Request Payload : 
    email: The email of the user
    cred: The credential of the user
Response payload : JWT token that has to be used in AuthHeader
```
```
URL: jwt/getuserid
Method : POST
Desc : returns userID from JWT token or error message
Request Header : 
    Authorization : JWT token
Response payload : userID

```
```
URL : project/root
Method : POST
Request Payload : 
    title : The title of the project
    desc : The description of the project
    visibility : The visibility of the project
Request Header :
    Authorization : JWT token 

Response payload :
    id : UID of the project
    title : Title of the project
    description : Description of the project
    ancestry : Ancestry of the project
    timestamp : Time when the project was created
    iscomplete : boolean if the project was completed or not
    rulesid : The rules ID that was assigned to the project
    userid : The user who created the project
```