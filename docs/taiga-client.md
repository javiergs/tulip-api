# Taiga client guide

> [!IMPORTANT]
> Start with the **read-only** example in `examples/taiga-readonly`. The `examples/taiga-api` example **creates, moves, and deletes** Taiga work items. Run it only against a dedicated, disposable test project; cleanup is best-effort and may leave resources behind.

## Run the read-only example

From `examples/taiga-readonly`, copy `src/main/resources/tulip.properties.example` to `src/main/resources/tulip.properties`, fill in your test credentials, then run:

```bash
mvn clean verify
mvn exec:java -Dexec.mainClass=example.Main
```

## Run the write example (test project only)

In `examples/taiga-api`, configure the same local `tulip.properties`. Find the numeric ID of a **disposable** Taiga project you own. The write example refuses to run unless both the explicit opt-in and project ID are provided:

```bash
mvn clean verify
MAVEN_OPTS="-Dtaiga.allowWrites=true -Dtaiga.testProjectId=12345" mvn exec:java -Dexec.mainClass=example.Main
```

Replace `12345` with your **test** project ID. The program verifies that the selected project is accessible, creates a temporary sprint/story/task, attempts a status change, and then attempts cleanup. Check the test project afterward; remote failures can leave artifacts. Never run against a production or shared course project.

The `TaigaClient` module provides a lightweight Java wrapper around the Taiga REST API.

It supports:

- Authentication
- Project retrieval
- Sprint retrieval
- User story retrieval
- Task retrieval
- Creation/update/deletion operations
- Task status movement
- Automatically handles paginated Taiga API endpoints internally.
- PATCH operations to automatically retrieves and injects the proper version field before updates.

> [!NOTE]
> The Taiga public API applies throttling/rate limits.
> Some heavy traversal integration tests are intentionally disabled to avoid excessive API requests.

## Authentication

Taiga integration requires a local configuration file.

Create:

```text
src/main/resources/tulip.properties
```

Add:

```properties
taiga.host=https://api.taiga.io
taiga.username=your_username
taiga.password=your_password
```

## Basic Authentication Example

```java
Properties p = new Properties();

try (InputStream in =
         getClass().getClassLoader()
             .getResourceAsStream("tulip.properties")) {

    p.load(in);
}

TaigaClient taiga = new TaigaClient(
    p.getProperty("taiga.host")
);

taiga.login(
    p.getProperty("taiga.username"),
    p.getProperty("taiga.password")
);
```


## Retrieve Projects

```java
List<TaigaProject> projects =
    taiga.getMyProjects();

for (TaigaProject p : projects) {
    System.out.println(p.getName());
}
```


## Retrieve Sprints

```java
List<TaigaSprint> sprints =
    taiga.getSprints(projectId);
```


## Retrieve User Stories

```java
List<TaigaUserStory> stories =
    taiga.getStories(projectId);
```


## Retrieve Tasks

```java
List<TaigaTask> tasks =
    taiga.getTasks(projectId);
```


## Create Sprint

```java
TaigaSprint sprint = taiga.createSprint(
    projectId,
    "Sprint 1",
    "2026-06-01",
    "2026-06-14"
);
```


## Create User Story

```java
TaigaUserStory story =
    taiga.createUserStory(
        projectId,
        "Implement authentication",
        "User login support",
        sprint.getId()
    );
```


## Create Task

```java
TaigaTask task =
    taiga.createTask(
        projectId,
        story.getId(),
        "Implement REST client",
        null
    );
```


## Update Task

```java
TaigaTask updated =
    taiga.updateTask(
        task.getId(),
        "Updated task title",
        null,
        null
    );
```


## Move Task to Another Status

```java
TaigaTask moved =
    taiga.moveTaskToStatus(
        task.getId(),
        newStatusId
    );
```


## Delete Task

```java
taiga.deleteTask(task.getId());
```


