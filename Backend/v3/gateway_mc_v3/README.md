# About Gateway Microservice

This MC will be the public endpoint that is going to be used by clients to send requests to the backend


[comment]: <> (API DOC SECTION)
<details>
<summary>
<h2 style="margin: 0rem ">API Documentation</h2>
</summary>

[comment]: <> (API DOC SECTION -> SIGN UP)
<details>

<summary>
<h3 style="margin: 0rem">Sign-Up</h3>
</summary>

Request Payload : email [text], cred [text] <br/>
Response Payload: data [boolean], message [string] (stating the reason when data is false) <br/>
<h3>FLOW</h3>
<list>
<p>1. Gateway_MC WILL CALL Auth_MC.</p>
<p>2. Auth_MC will return {boolean, message} to Gateway_MC.</p>
<p>3. Gateway_MC will return {boolean,message} to Client.</p>

<list/>


</details>

[comment]: <> (API DOC SECTION -> SIGN IN)
<details>

<summary>
<h3 style="margin: 0rem">Sign-In</h3>
</summary>

Request Payload : email [text], cred [text] <br/>
Response Payload: data [text] (JWT token that client is going to use for Authorization), message [string] (stating the reason when status code is NOT OK) <br/>
<h3>FLOW</h3>
<list>
<p>1. Gateway_MC WILL CALL Auth_MC.</p>
<p>2. Auth_MC will return {text, message} to Gateway_MC.</p>
<p>3. Gateway_MC will return {text,message} to Client.</p>

<list/>


</details>



</details>

