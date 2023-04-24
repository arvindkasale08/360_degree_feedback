# 360 Degree Feedback - User Management Service

## Usecase

- You are building designing and building the 360-degree application shared with multiple
clients for collecting the feedback from peer, manager, business stakeholder. Feedback
can be visible to immediate manager.
- The 360-degree app has 3 actors:
1. Feedback Requester: Person looking for feedback
2. Manager: Can ask for feedback and review his direct employee feedback
3. Feedback Giver:- Someone who is giving the feedback on request

- System Targets
• There are 100K registered worker with almost 2K feedback request per day.
• 1k feedback are posted every day growing at a rate of 250 feedback per week.

## Technology Used
- Java 17
- SpringBoot
- Reactor Java
- MongoDB

## Introduction

- Simple user management repo for holding users.
- Right now only provides get apis for user retrieval
- New user creation is via db dump
- DB dump file is store [here](./application/src/main/resources/data_dump_users.json)

## APIs

### Get All Users

**URL** - `http://localhost:7070/api/account/v1/users/` <br/>
**Method** - `GET` <br/>
**Response**

```json
{
    "users": [
        {
            "id": "64449f2baa96b65445caf5a2",
            "externalId": "a267db4e-c5cc-4656-abda-03b130cb45c6",
            "username": "sanjay.arora",
            "managerId": "",
            "status": "ACTIVE",
            "profile": {
                "firstName": "Sanjay",
                "lastName": "Arora",
                "email": "sanjay.arora@gmail.com",
                "businessUnit": "Development Center India",
                "designation": "VP",
                "imageUrl": "../assets/images/users/4.jpg"
            }
        },
        {
            "id": "64449f2baa96b65445caf5a3",
            "externalId": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
            "username": "umesh.wadhwani",
            "managerId": "a267db4e-c5cc-4656-abda-03b130cb45c6",
            "status": "ACTIVE",
            "profile": {
                "firstName": "Umesh",
                "lastName": "Wadhwani",
                "email": "umesh.wadhwani@gmail.com",
                "businessUnit": "Core Engineering",
                "designation": "Senior Manager",
                "imageUrl": "../assets/images/users/1.jpg"
            }
        }
    ]
}

```


### Get User by ExternalID

**URL** - `http://localhost:7070/api/account/v1/users/{externalId}`<br/>
**Method** - `GET`<br/>
**Response** 

```json
{
    "id": "64449f2baa96b65445caf5ad",
    "externalId": "1ebd9a62-cf43-4f68-8957-7a9909112eee",
    "username": "majoj.dalvi",
    "managerId": "fc9d77e4-37a6-42e9-af0f-207884a2b1a7",
    "status": "ACTIVE",
    "profile": {
        "firstName": "Manoj",
        "lastName": "Dalvi",
        "email": "manoj.dalvi@gmail.com",
        "businessUnit": "Professional Service",
        "designation": "Software Testing Engineer",
        "imageUrl": "../assets/images/users/d2.jpg"
    }
}
```

### Get All Reportings for Manager

**URL** - `http://localhost:7070/api/account/v1/users/{externalId}/directReporting`<br/>
**Method** - `GET`<br/>
**Response**

```json
{
    "users": [
        {
            "id": "64449f2baa96b65445caf5aa",
            "externalId": "28306a93-2951-4457-94a9-2cc51235e0f6",
            "username": "ayushi.pathak",
            "managerId": "fc9d77e4-37a6-42e9-af0f-207884a2b1a7",
            "status": "ACTIVE",
            "profile": {
                "firstName": "Ayushi",
                "lastName": "Pathak",
                "email": "ayushi.pathak@gmail.com",
                "businessUnit": "Professional Service",
                "designation": "Associate Software Developer",
                "imageUrl": "../assets/images/users/agent.jpg"
            }
        },
        {
            "id": "64449f2baa96b65445caf5ab",
            "externalId": "592ef90c-ba76-4e79-aa69-2561b80a5b0a",
            "username": "arun.bhatt",
            "managerId": "fc9d77e4-37a6-42e9-af0f-207884a2b1a7",
            "status": "ACTIVE",
            "profile": {
                "firstName": "Arun",
                "lastName": "Bhatt",
                "email": "arun.bhatt@gmail.com",
                "businessUnit": "Professional Service",
                "designation": "Software Developer",
                "imageUrl": "../assets/images/users/agent2.jpg"
            }
        },
        {
            "id": "64449f2baa96b65445caf5ac",
            "externalId": "34a5b494-7bdd-411b-a369-c98993b3c1cc",
            "username": "tejal.katore",
            "managerId": "fc9d77e4-37a6-42e9-af0f-207884a2b1a7",
            "status": "ACTIVE",
            "profile": {
                "firstName": "Tejal",
                "lastName": "Katore",
                "email": "tejal.katore@gmail.com",
                "businessUnit": "Professional Service",
                "designation": "Software Testing Engineer",
                "imageUrl": "../assets/images/users/d1.jpg"
            }
        },
        {
            "id": "64449f2baa96b65445caf5ad",
            "externalId": "1ebd9a62-cf43-4f68-8957-7a9909112eee",
            "username": "majoj.dalvi",
            "managerId": "fc9d77e4-37a6-42e9-af0f-207884a2b1a7",
            "status": "ACTIVE",
            "profile": {
                "firstName": "Manoj",
                "lastName": "Dalvi",
                "email": "manoj.dalvi@gmail.com",
                "businessUnit": "Professional Service",
                "designation": "Software Testing Engineer",
                "imageUrl": "../assets/images/users/d2.jpg"
            }
        }
    ]
}
```

### Search User

**URL** - `http://localhost:7070/api/account/v1/users/search?searchText=Ar`<br/>
**Method** - `GET`<br/>

**Response**
```json
{
    "users": [
        {
            "id": "64449f2baa96b65445caf5a5",
            "externalId": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
            "username": "arvind.kasale",
            "managerId": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
            "status": "ACTIVE",
            "profile": {
                "firstName": "Arvind",
                "lastName": "Kasale",
                "email": "arvind.kasale@gmail.com",
                "businessUnit": "Core Engineering",
                "designation": "Staff Software Developer",
                "imageUrl": "../assets/images/users/3.jpg"
            }
        },
        {
            "id": "64449f2baa96b65445caf5a7",
            "externalId": "08a43610-3044-4b0c-a194-4608493a0242",
            "username": "arpit.jain",
            "managerId": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
            "status": "ACTIVE",
            "profile": {
                "firstName": "Arpit",
                "lastName": "Jain",
                "email": "arpit.jain@gmail.com",
                "businessUnit": "Core Engineering",
                "designation": "Software Developer",
                "imageUrl": "../assets/images/users/6.jpg"
            }
        },
        {
            "id": "64449f2baa96b65445caf5a8",
            "externalId": "3a300e46-5b09-4d10-af02-96beec9ba586",
            "username": "ankit.bhatt",
            "managerId": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
            "status": "ACTIVE",
            "profile": {
                "firstName": "Ankit",
                "lastName": "Bhatt",
                "email": "arpit.jain@gmail.com",
                "businessUnit": "Core Engineering",
                "designation": "Staff Software Developer",
                "imageUrl": "../assets/images/users/7.jpg"
            }
        },
        {
            "id": "64449f2baa96b65445caf5ab",
            "externalId": "592ef90c-ba76-4e79-aa69-2561b80a5b0a",
            "username": "arun.bhatt",
            "managerId": "fc9d77e4-37a6-42e9-af0f-207884a2b1a7",
            "status": "ACTIVE",
            "profile": {
                "firstName": "Arun",
                "lastName": "Bhatt",
                "email": "arun.bhatt@gmail.com",
                "businessUnit": "Professional Service",
                "designation": "Software Developer",
                "imageUrl": "../assets/images/users/agent2.jpg"
            }
        }
    ]
}
```

## Steps to run locally

- Clone / Download the repository
- Import the dump file present at [location](./application/src/main/resources/data_dump_users.json) into MongoDB Compass.
- Run `./gradlew clean build` to build the source code.
- Pass `-vm options` as `--add-opens java.base/java.lang=ALL-UNNAMED`
- Run the application as `./gradlew bootrun` or run from intellij.
