# 360 Degree Feedback

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
- React Redux Toolkit
- Kafka (may be if time permits)

## Implementation Details

Implemented the following services

### User Management

[RepoLink](./user-management/Readme.md)

Handles user management for the 360 degree feedback application

### Feedback Management

[RepoLink](./feedback-management/Readme.md)

Handles feedback management for the 360 degree feedback application

### Feedback Management UI

[RepoLink](./feedback-ui/Readme.md)

Handles UI for the 360 degree feedback application

## How to run locally

Refer individual repos