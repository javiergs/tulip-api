# Student Quick Start — TULIP 8.0.0

**Goal:** Learn to use the published TULIP Java library with external APIs, beginning with a public GitHub repository and progressing to authenticated, read-only Taiga access.

> [!IMPORTANT]
> The Taiga write example can **create, move, and delete** project data. Use a disposable test project only. Start with the read-only examples; never use production credentials or projects.

## 1. Prerequisites

Install Git, Java **21+**, and Maven. Check with:

```bash
git --version
java -version
mvn -version
```

## 2. Clone and compile

```bash
git clone https://github.com/javiergs/tulip-getting-started.git
cd tulip-getting-started
mvn clean verify
```

Maven downloads `com.javiergs:tulip:8.0.0` automatically from Maven Central. You do not need the TULIP implementation source.

## 3. First experiment: GitHub (no token required for public repositories)

From the repository root:

```bash
mvn exec:java -Dexec.mainClass=com.javiergs.examples.github.GitHubExample
```

The example traverses files in a public GitHub repository. If GitHub rate-limits your requests, copy `src/main/resources/tulip.properties.example` to `src/main/resources/tulip.properties` and configure an optional `GITHUB_TOKEN`. **Never commit the populated file.**

Inspect `src/main/java/com/javiergs/examples/github/GitHubExample.java` and the [GitHub client guide](github-client.md). Identify the target repository, returned file paths, and behavior when a repository or token is invalid.

## 4. Second experiment: Taiga read-only

From the repository root:

```bash
cp src/main/resources/tulip.properties.example src/main/resources/tulip.properties
```

Enter your test account's `taiga.host`, `taiga.username`, and `taiga.password`, then run:

```bash
mvn clean verify
mvn exec:java -Dexec.mainClass=com.javiergs.examples.taiga.TaigaReadExample
```

Record which projects you can access, how authentication failures are reported, and how the data differs from GitHub's file listing. See the [Taiga guide](taiga-client.md).

## 5. Optional: Taiga write operations (disposable project only)

Only after completing the read-only example, inspect `src/main/java/com/javiergs/examples/taiga/TaigaWriteExample.java` and its configuration. This example creates a sprint, user story, and task, moves the task, and attempts cleanup. **It changes remote data** and cleanup can fail.

Set `taiga.test.project.id` to a dedicated disposable test project ID and run `mvn exec:java -Dexec.mainClass=com.javiergs.examples.taiga.TaigaWriteExample -Dexec.args=--allow-write`. Do not use a production or shared course project.

## 6. Develop an independent experiment

Create a separate Maven project or a new example in your fork. Possible directions:

- Retrieve a particular file from a public GitHub repository and summarize its metadata.
- Compare top-level and recursive GitHub file listings.
- List Taiga user stories or tasks without modifying them.
- Propose a new example for another published TULIP module after reviewing its Javadoc.

Document the function's inputs, API calls, expected results, failure behavior, and exact run command.

## 7. Share results and improvements

Submit a brief report: what compiled, what ran against a live service, what failed, and what you learned. Suggest documentation fixes or additional examples via a pull request. See [CONTRIBUTING.md](../CONTRIBUTING.md).

**CI compiles examples; it does not authenticate against GitHub or Taiga or validate live service behavior.**
