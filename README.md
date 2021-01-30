# socialgram

Problem Statement :
------------------------------
Design a simple Social Media application(Maven Project). 

Users should be able to: 

1.       create new posts

2.       follow/unfollow another user

3.       view the 20 most recent posts in the user's news feed.

Your design should support the following methods:

createPost(userId, postId, content): Compose a new post.

 

getNewsFeed(userId): Retrieve the 20 most recent post ids in the user's news feed. Each item in the news feed must be posted either by one of the user’s followees or by the user herself. Posts must be ordered by posting time starting from the most recent one.

 

follow(followerId, followeeId): Follower follows a followee.

 

unfollow(followerId, followeeId): Follower unfollows a followee.


Technologies used :
------------------------------
- Java
- Spring Boot
- Embedded MongoDB
- Maven
- Swagger 2
- Junit & Mockito

Build Requirements :
------------------------------
- Java 1.8
- mvn test


How to test manually?
---------------------------------
1) createPost :

Request Body-
curl --location --request POST 'http://localhost:8080/user/2/post/2' \
--header 'Content-Type: text/plain' \
--data-raw 'This is my second post for second user.'

2) getNewsFeed :

Request Body-
curl --location --request POST 'http://localhost:8080/user/1'

Response Body-
[
    {
        "userId": "2",
        "postId": "4",
        "content": "This is my Fourth post for second user."
    },
    {
        "userId": "2",
        "postId": "3",
        "content": "This is my Third post for second user."
    }
]

3) follow :

Request Body-
curl --location --request POST 'http://localhost:8080/1/follow/2'


Response Body-

{
    "userId": "1",
    "name": "Josephine",
    "followeeIds": [
        "2"
    ]
}

4) unfollow :

Request Body-
curl --location --request POST 'http://localhost:8080/1/unfollow/2'


Response Body-

{
    "userId": "1",
    "name": "Josephine",
    "followeeIds": []
}

Rest Documentation with Swagger :
---------------------------------

Default host: http://localhost:8080
This project is using Swagger2 documentation:
http://localhost:8080/v2/api-docs - JSON format of rest documentation http://localhost:8080/swagger-ui.html - UI representation of rest documentation

Achieves :
-----------------
1) Solution was created;
2) Unit tests and Integration tests were created for code coverage.

Suggestions for improvement :
-------------------------------------------
1) Dockerizing would be nice, it allows us to test our application across to multiple instances.
2) Distributed Cache(Redis) would need if we run application across to multiple instances.
3) Authentication will restrict unauthorizes access to this API.
4) Sonarlint connection with Sonar Dashboard
5) Setting up this project in github actions for CI/CD.
