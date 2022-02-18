# Project Rules

1. Visibility of project . If Children allowed, Can they have independent visibility or will inherit from parent ?
2. Can Project have children ? . Does Creating a child need to be approved by the root first ?
3. Can Project leader give task to member . The Type of visibility allowed for the task (Private, public,
   Public-with-Contribution)
   . Does the task need to be allowed by the root first? . Does All task need to be "marked as complete" by root to be
   considered complete?
4. Can project leader give task to children project . Same as 3
5. Can project leader give task to foreign project (project of other node)
   . Same as 3

SQL command

```CREATE TABLE "projectrules" (
"id" int NOT NULL,
"visibility" VARCHAR(255) NOT NULL,
"child_visibilities_allowed" VARCHAR(255) NOT NULL,
"have_children" BOOLEAN NOT NULL,
"have_children_needs_permission" BOOLEAN NOT NULL,
"can_leader_task_member" BOOLEAN NOT NULL,
"task_member_visibilities_allowed" VARCHAR(255) NOT NULL,
"task_member_needs_permission" BOOLEAN NOT NULL,
"task_member_complete_permission" BOOLEAN NOT NULL,
"can_leader_task_children" BOOLEAN NOT NULL,
"task_child_visibilities_allowed" VARCHAR(255) NOT NULL,
"task_child_needs_permission" BOOLEAN NOT NULL,
"task_child_complete_permission" BOOLEAN NOT NULL,
"can_leader_task_foreign" BOOLEAN NOT NULL,
"task_foreign_visibilities_allowed" VARCHAR(255) NOT NULL,
"task_foreign_needs_permission" BOOLEAN NOT NULL,
"task_foreign_complete_permission" BOOLEAN NOT NULL,
CONSTRAINT "projectrules_pk" PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);```

Root project ROW example
id                                : 69 //The Id of the rule
visibility                        : private //the scope of the rule
child_visibilities_allowed        : {private,public,public_contribution} //The scopes allowed for children of the project
have_children                     : true //Allowed to have children or not
have_children_needs_permission    : false //Does creating children need to be approved by Root
can_leader_task_member            : true //Can leader of the group give task to Member
task_member_visibilities_allowed  : {private,public,public_contribution} //The scope allowed for tasks
task_member_needs_permission      : false //Does Tasking member need permission from Root
task_member_complete_permission   : false //Do Tasks need to be marked as complete by the root + leader to be marked as complete 
can_leader_task_children          : true //Can leader of the group give task to it's children
task_child_visibilities_allowed   : {private,public,public_contribution} 
task_child_needs_permission       : false
task_child_complete_permission    : false
can_leader_task_foreign           : true
task_foreign_visibilities_allowed : {private,public,public_contribution} 
task_foreign_needs_permission     : false
task_foreign_complete_permission  : false


