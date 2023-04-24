# 360 Degree Feedback - Feedback Management Service

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
- Kafka ? (may be if in time)

## Introduction

- Feedback management repo.
- Provides capabilities to request, view self, view reporting feedbacks

## APIs

### Initialize Feedback

**URL** - `http://localhost:8080/api/review/v1/feedback/initialize` <br/>
**Method** - `POST` <br/>
**Request**

```json
{
    "requestorId": "a267db4e-c5cc-4656-abda-03b130cb45c6",
    "subjectId": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
    "actorId": "ffed3dc5-e9df-407e-aebb-62e827153e6a"
}
```

**Response**

```json
{
    "id": "6445fc7ce2facb23fe7544cd",
    "requestor": {
        "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
        "email": "sanjay.arora@gmail.com",
        "firstName": "Sanjay",
        "lastName": "Arora"
    },
    "subject": {
        "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
        "email": "umesh.wadhwani@gmail.com",
        "firstName": "Umesh",
        "lastName": "Wadhwani"
    },
    "actor": {
        "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
        "email": "arvind.kasale@gmail.com",
        "firstName": "Arvind",
        "lastName": "Kasale"
    },
    "data": null,
    "validTill": "1682913019602",
    "status": "INITIALIZED"
}
```


### Finalize Feedback

**URL** - `http://localhost:8080/api/review/v1/feedback/{feedbackId}/finalize`<br/>
**Method** - `POST`<br/>
**Request** 

```json
{
    "data": "Some dummy data for the feedback"
}
```
**Response** 

```json
{
    "id": "6445fc7ce2facb23fe7544cd",
    "requestor": {
        "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
        "email": "sanjay.arora@gmail.com",
        "firstName": "Sanjay",
        "lastName": "Arora"
    },
    "subject": {
        "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
        "email": "umesh.wadhwani@gmail.com",
        "firstName": "Umesh",
        "lastName": "Wadhwani"
    },
    "actor": {
        "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
        "email": "arvind.kasale@gmail.com",
        "firstName": "Arvind",
        "lastName": "Kasale"
    },
    "data": "Some dummy data for the feedback",
    "validTill": "1682913019602",
    "status": "FINALIZED"
}
```

### Get Feedback By ID

**URL** - `http://localhost:8080/api/review/v1/feedback/{feedbackId}`<br/>
**Method** - `GET`<br/>
**Response**

```json
{
    "id": "644515c9193bd71be820bba4",
    "requestor": {
        "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
        "email": "sanjay.arora@gmail.com",
        "firstName": "Sanjay",
        "lastName": "Arora"
    },
    "subject": {
        "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
        "email": "umesh.wadhwani@gmail.com",
        "firstName": "Umesh",
        "lastName": "Wadhwani"
    },
    "actor": {
        "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
        "email": "arvind.kasale@gmail.com",
        "firstName": "Arvind",
        "lastName": "Kasale"
    },
    "data": "Some data from clinet",
    "validTill": "1682853961032",
    "status": "FINALIZED"
}
```

### Get All Assigned For Actor

**URL** - `http://localhost:8080/api/review/v1/feedback/assignedToActor/{externalId}`<br/>
**Method** - `GET`<br/>

**Response**
```json
{
    "feedbacks": [
        {
            "id": "6445fe4bb713b41d4aa79df1",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": null,
            "validTill": "1682913483517",
            "status": "INITIALIZED"
        }
    ],
    "paginationInfo": {
        "currentPage": 1,
        "totalPages": 1,
        "size": 13
    }
}
```

### My Feedbacks

**URL** - `http://localhost:8080/api/review/v1/feedback/me/{externalId}`<br/>
**Method** - `GET`<br/>

**Response**
```json
{
    "feedbacks": [
        {
            "id": "6445fc7ce2facb23fe7544cd",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "Some dummy data for the feedback",
            "validTill": "1682913019602",
            "status": "FINALIZED"
        },
        {
            "id": "644519d1c945887b8f68112d",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "Hi Arvind,\n\nThank you for providing me with more information about Ankit's role and responsibilities. Based on what you've shared, here are some areas where Ankit could focus on for improvement:\n\nTime Management: It seems that Ankit may need to work on his time management skills to be more effective in meeting deadlines and completing tasks on time. Encourage him to use tools like calendars, to-do lists, and project management software to plan his workday more efficiently.\n\nAttention to detail: While Ankit is doing well in terms of completing his work, he could work on paying more attention to detail, especially when it comes to documentation or data entry. Encourage him to review his work more carefully to ensure accuracy and completeness.\n\nCommunication: Effective communication is key to success in any role. Encourage Ankit to improve his communication skills by being more proactive in asking for feedback, providing regular updates on his progress, and seeking clarification when needed.\n\nProactivity: Encourage Ankit to be more proactive in taking on new responsibilities and seeking out opportunities for growth and development within the organization.\n\nOverall, Ankit has a lot of potential and with some focused attention on the above areas, he can continue to grow and excel in his role. I recommend setting clear goals and objectives with Ankit to help him work towards improving in these areas.\n\nI hope this feedback is helpful.\n\nBest regards,\nChatGPT\n\n\n\nStop generating",
            "validTill": "1682854993919",
            "status": "FINALIZED"
        },
        {
            "id": "644503e5a4ad827a0dc147bb",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "asdas dasd asd asd asd asd ",
            "validTill": "1682849380614",
            "status": "FINALIZED"
        },
        {
            "id": "6445112e7ed4bd5906c10435",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "Saasdasdas das asd asd asd as",
            "validTill": "1682852781816",
            "status": "FINALIZED"
        },
        {
            "id": "644511b979b81457dbea5e1a",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "Providing more feedback\n",
            "validTill": "1682852921267",
            "status": "FINALIZED"
        },
        {
            "id": "644511fba027ef346f38f7b8",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "Umesh feedback from arvind",
            "validTill": "1682852986732",
            "status": "FINALIZED"
        },
        {
            "id": "6445136f79d9a336cefc2770",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "sadsadsad asdas d as",
            "validTill": "1682853356410",
            "status": "FINALIZED"
        },
        {
            "id": "644513bade9cc4657df07266",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "Some more feedback for Umesh",
            "validTill": "1682853433644",
            "status": "FINALIZED"
        },
        {
            "id": "644513f7748f9578ab336b23",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "manual header insertion",
            "validTill": "1682853494862",
            "status": "FINALIZED"
        },
        {
            "id": "644515c9193bd71be820bba4",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "Some data from clinet",
            "validTill": "1682853961032",
            "status": "FINALIZED"
        }
    ],
    "paginationInfo": {
        "currentPage": 1,
        "totalPages": 2,
        "size": 12
    }
}
```

### Direct Reporting Feedback

**URL** - `http://localhost:8080/api/review/v1/feedback/directReporting/{externalId}`<br/>
**Method** - `GET`<br/>

**Response**
```json
{
    "feedbacks": [
        {
            "id": "6445fc7ce2facb23fe7544cd",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "Some dummy data for the feedback",
            "validTill": "1682913019602",
            "status": "FINALIZED"
        },
        {
            "id": "644519d1c945887b8f68112d",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "Hi Arvind,\n\nThank you for providing me with more information about Ankit's role and responsibilities. Based on what you've shared, here are some areas where Ankit could focus on for improvement:\n\nTime Management: It seems that Ankit may need to work on his time management skills to be more effective in meeting deadlines and completing tasks on time. Encourage him to use tools like calendars, to-do lists, and project management software to plan his workday more efficiently.\n\nAttention to detail: While Ankit is doing well in terms of completing his work, he could work on paying more attention to detail, especially when it comes to documentation or data entry. Encourage him to review his work more carefully to ensure accuracy and completeness.\n\nCommunication: Effective communication is key to success in any role. Encourage Ankit to improve his communication skills by being more proactive in asking for feedback, providing regular updates on his progress, and seeking clarification when needed.\n\nProactivity: Encourage Ankit to be more proactive in taking on new responsibilities and seeking out opportunities for growth and development within the organization.\n\nOverall, Ankit has a lot of potential and with some focused attention on the above areas, he can continue to grow and excel in his role. I recommend setting clear goals and objectives with Ankit to help him work towards improving in these areas.\n\nI hope this feedback is helpful.\n\nBest regards,\nChatGPT\n\n\n\nStop generating",
            "validTill": "1682854993919",
            "status": "FINALIZED"
        },
        {
            "id": "644503e5a4ad827a0dc147bb",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "asdas dasd asd asd asd asd ",
            "validTill": "1682849380614",
            "status": "FINALIZED"
        },
        {
            "id": "6445112e7ed4bd5906c10435",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "Saasdasdas das asd asd asd as",
            "validTill": "1682852781816",
            "status": "FINALIZED"
        },
        {
            "id": "644511b979b81457dbea5e1a",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "Providing more feedback\n",
            "validTill": "1682852921267",
            "status": "FINALIZED"
        },
        {
            "id": "644511fba027ef346f38f7b8",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "Umesh feedback from arvind",
            "validTill": "1682852986732",
            "status": "FINALIZED"
        },
        {
            "id": "6445136f79d9a336cefc2770",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "sadsadsad asdas d as",
            "validTill": "1682853356410",
            "status": "FINALIZED"
        },
        {
            "id": "644513bade9cc4657df07266",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "Some more feedback for Umesh",
            "validTill": "1682853433644",
            "status": "FINALIZED"
        },
        {
            "id": "644513f7748f9578ab336b23",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "manual header insertion",
            "validTill": "1682853494862",
            "status": "FINALIZED"
        },
        {
            "id": "644515c9193bd71be820bba4",
            "requestor": {
                "id": "a267db4e-c5cc-4656-abda-03b130cb45c6",
                "email": "sanjay.arora@gmail.com",
                "firstName": "Sanjay",
                "lastName": "Arora"
            },
            "subject": {
                "id": "fff6a5e1-d52a-4b79-9908-2e227f2a4261",
                "email": "umesh.wadhwani@gmail.com",
                "firstName": "Umesh",
                "lastName": "Wadhwani"
            },
            "actor": {
                "id": "ffed3dc5-e9df-407e-aebb-62e827153e6a",
                "email": "arvind.kasale@gmail.com",
                "firstName": "Arvind",
                "lastName": "Kasale"
            },
            "data": "Some data from clinet",
            "validTill": "1682853961032",
            "status": "FINALIZED"
        }
    ],
    "paginationInfo": {
        "currentPage": 1,
        "totalPages": 2,
        "size": 12
    }
}
```

## Steps to run locally

- Clone / Download the repository
- Run `./gradlew clean build` to build the source code.
- Pass `-vm options` as `--add-opens java.base/java.lang=ALL-UNNAMED`
- Run the application as `./gradlew bootrun` or run from intellij.
