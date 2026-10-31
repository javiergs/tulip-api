# Getting Started with TULIP

![Java 21+](https://img.shields.io/badge/Java-21%2B-blue)
![Version 8.0.0](https://img.shields.io/badge/version-8.0.0-green)
[![Java CI](https://github.com/javiergs/tulip-getting-started/actions/workflows/java-ci.yml/badge.svg)](https://github.com/javiergs/tulip-getting-started/actions/workflows/java-ci.yml)

**TULIP** is a Java utility library for working with GitHub, Taiga, and other developer tools. Use this repository to learn the published **TULIP 8.0.0** API through small, independent Java 21 and Maven examples.

<img width="1536" height="1024" alt="TULIP library overview illustration" src="https://github.com/user-attachments/assets/01117cfe-735b-44e0-9ed4-2bb47c6126ab" />

**New to TULIP?** Start with the [Student Quick Start](docs/student-quick-start.md) for guided examples and independent development.

## Requirements

- Java 21+ and Maven
- Example-specific configuration as described below

## Quick start

Start with the public GitHub example (no token required for basic public access):

```bash
mvn clean verify
mvn exec:java -Dexec.mainClass=com.javiergs.examples.github.GitHubExample
```

For read-only Taiga access, configure the credentials described in the [Taiga guide](docs/taiga-client.md) before running `TaigaReadExample`. Avoid running write examples against real projects.

## Install with Maven

Add the following dependency to your project's `pom.xml`:

```xml
<dependency>
    <groupId>com.javiergs</groupId>
    <artifactId>tulip</artifactId>
    <version>8.0.0</version>
</dependency>
```

- [Maven Central artifact page](https://central.sonatype.com/artifact/com.javiergs/tulip/8.0.0)
- [API reference (JavaDoc)](https://javiergs.github.io/tulip-api/javadoc/)

> GitHub Actions compiles the single Maven project against the published Maven Central dependency. Service authentication and live API behavior are not tested in CI.

## Examples

The repository is **one Maven project**. Each example has its own `main()` method under `src/main/java/com/javiergs/examples/`.

- **GitHub API** — List files in a public GitHub repository. Supports an optional access token for rate limits and private repositories. [Guide](docs/github-client.md).
- **Taiga API** — Authenticate and demonstrate creating, reading, moving, and cleaning up Taiga work items. **This example writes to a Taiga project**; use a disposable test project. [Guide](docs/taiga-client.md).
- **Taiga read-only** — Authenticate and list accessible projects without changing any Taiga data.

From the repository root, compile all examples:

```sh
mvn clean verify
```

Run one example explicitly:

```sh
mvn exec:java -Dexec.mainClass=com.javiergs.examples.github.GitHubExample
mvn exec:java -Dexec.mainClass=com.javiergs.examples.taiga.TaigaReadExample
```

To run the **destructive Taiga write** example, configure a disposable `taiga.test.project.id` and explicitly opt in:

```sh
mvn exec:java -Dexec.mainClass=com.javiergs.examples.taiga.TaigaWriteExample -Dexec.args=--allow-write
```

No external API calls occur during `mvn verify`.

## Library modules

- **GitHub:** URL parsing, remote file retrieval, and repository traversal. [Guide](docs/github-client.md).
- **Taiga:** Projects, sprints, user stories, tasks, and status operations. [Guide](docs/taiga-client.md).
- **RTDE migration:** Universal Robots RTDE support has moved out of TULIP into the independent RTDE4J library. See the [RTDE4J getting-started guide](https://github.com/javiergs/rtde4j-getting-started) for installation and JavaDoc.
- **Compiler:** Lexer, token, and automata helpers; see the [API reference](https://javiergs.github.io/tulip-api/javadoc/javiergs/tulip/compiler/package-summary.html).
- **Groq:** API client utilities; see the [API reference](https://javiergs.github.io/tulip-api/javadoc/javiergs/tulip/groq/package-summary.html).

## Credentials and service safety

- Use a local `tulip.properties` file for example-specific credentials, following `src/main/resources/tulip.properties.example`.
- **Do not commit actual credentials.** The `.gitignore` excludes local `tulip.properties` files.
- GitHub examples can use an optional `GITHUB_TOKEN` environment variable (preferred) or properties key; Taiga examples require `taiga.host`, `taiga.username`, and `taiga.password`.

## API documentation

- [TULIP JavaDoc](https://javiergs.github.io/tulip-api/javadoc/) — published API reference.
- [GitHub client guide](docs/github-client.md) and [Taiga client guide](docs/taiga-client.md) — examples and configuration.

## Troubleshooting

- **Maven cannot resolve TULIP:** Use the published `com.javiergs:tulip:8.0.0` dependency; a later version may not yet be available on Maven Central.
- **GitHub API errors:** Check connectivity and rate limits; use a scoped token only when needed.
- **Taiga authentication fails:** Check `taiga.host`, username, password, and service availability; never share real credentials in issues.
- **Build failure:** Confirm `mvn -version` reports Java 21.
