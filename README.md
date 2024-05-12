# Tasks

## Choose Your Tasks

Start with easy tasks and then move on to others. Try to complete as many tasks as possible.

1. **Understand the Project Structure (Onboarding)**. ✔
2. **Remove Social Networks**: VK, Yandex. *(Easy task)*
3. **Move Sensitive Information to a Separate Property File**:
  - Login
  - Database Password
  - OAuth Registration/Authorization Identifiers
  - Mail Configuration
    *(Values of these properties should be read at server startup from machine environment variables. Easy task)*
4. **Refactor Tests to Use In-Memory Database (H2) instead of PostgreSQL**:
  - Define 2 beans, and the selection of which one to use should be determined by the active Spring profile.
    *(H2 does not support all the features of PostgreSQL, so you'll need to simplify test data scripts a bit.)*
5. **Write Tests for all Public Methods of the ProfileRestController Controller**:
  - Although there are only 2 methods, there should be more test methods because you need to check both success and failure paths.
6. **Refactor the com.javarush.jira.bugtracking.attachment.FileUtil#upload Method**:
  - Modify it to use a modern approach for working with the file system. *(Easy task)*
7. **Add New Functionality: Task Tagging**:
  - REST API + Implementation on the service side.
  - Frontend development is optional. The task_tag table has already been created.
8. **Add Time Calculation**:
  - Calculate how long tasks were in progress and under testing.
  - Write 2 service-level methods that take tasks as parameters and return the elapsed time:
    - How long the task was in progress (ready_for_review minus in_progress).
    - How long the task was under testing (done minus ready_for_review).
      *(To complete this task, you need to add 3 records to the ACTIVITY table in the database initialization script changelog.sql. With statuses: start time of work on the task - in_progress, end time of development - ready_for_review, end time of testing - done.)*
9. **Write a Dockerfile for the Main Server**.
10. **Write a docker-compose file to launch the server container along with the database and nginx**:
  - Use the config/nginx.conf configuration file for nginx. You can edit the config file if necessary. *(Hard task)*
11. **Add Localization in at least two languages for email templates (mails) and the index.html homepage**.
12. **Redo the mechanism for distinguishing "self-foreign" between frontend and backend from JSESSIONID to JWT**. *(Extra-hard task)*

When submitting the project, write in README.md which task items you completed.



## [REST API](http://localhost:8080/doc)

## Концепция:

- Spring Modulith
  - [Spring Modulith: достигли ли мы зрелости модульности](https://habr.com/ru/post/701984/)
  - [Introducing Spring Modulith](https://spring.io/blog/2022/10/21/introducing-spring-modulith)
  - [Spring Modulith - Reference documentation](https://docs.spring.io/spring-modulith/docs/current-SNAPSHOT/reference/html/)

```
  url: jdbc:postgresql://localhost:5432/jira
  username: jira
  password: JiraRush
```

- Есть 2 общие таблицы, на которых не fk
  - _Reference_ - справочник. Связь делаем по _code_ (по id нельзя, тк id привязано к окружению-конкретной базе)
  - _UserBelong_ - привязка юзеров с типом (owner, lead, ...) к объекту (таска, проект, спринт, ...). FK вручную будем
    проверять

## Аналоги

- https://java-source.net/open-source/issue-trackers

## Тестирование

- https://habr.com/ru/articles/259055/

Список выполненных задач:
...