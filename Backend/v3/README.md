# Architecture and structure of the backend

Used Spring for most of my microservice, the reason being that it is easy to have shared configuration
between several spring microservices(MC).
<h3>List of Microservices</h3>

1. Gateway Microservice: This is the microservice that the client is going to interact with. It has public endpoint that
   is going to be accessible by the client. Gateway in turn will communicate with Other Microservices if needed and
   return the client with the appropriate result.
   <br>
2. Auth Microservice: This Microservice is going to handle process like sign-in, sign-up, etc.
3. Project Microservice: This Microservice is going to handle creation of project, creation of subproject, deletion of  projects and other tasks related to project.
4. Rule Microservice: Handles tasks related to rules such as creating rule of a project, getting similar rules, etc.
5. Member Microservice: Used to add leaders and members to projects and subprojects as well as other tasks that fall under this section
6. Task Microservice: WIP(Handle Giving tasks, Completion of tasks, etc)

<h3>Configurations</h3>
Configurations of microservices are stored in git and distributed among microservices Some configurations are going to
be universal for every microservice, and some are going to be exclusive to a specific microservice.
<br>
<br>
Below are some Configuration properties from the git repo to further clarify the naming conventions used and how properties are stored across different files<br>

1. progem.yml : This configuration file has all the properties that are going to be needed by ALL microservice and hence
   will be used by every single Spring microservice
2. gateway_mc : This configuration file has the setting that are going to be used ONLY by gateway microservice such as
   port number. The word "mc" is used to denote that the config file is for a microservice
3. auth_api : This configuration file has attributes that are going to be related to Auth microservice and which
   may/may-not be needed to be used by other microservice. It contains attributes like :
   <br> base-url (api/v1/auth)
   <br> sign-in-url (/sign-in)
   This Config file will be used by Gateway Microservice to communicate with Auth Microservice through URLs
