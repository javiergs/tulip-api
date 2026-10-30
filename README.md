# Getting Started with TULIP

[Contributing](CONTRIBUTING.md) · [Code of Conduct](CODE_OF_CONDUCT.md) · [Accessibility](ACCESSIBILITY.md)

![Java 21+](https://img.shields.io/badge/Java-21%2B-blue)
![Examples 8.0.0](https://img.shields.io/badge/version-8.0.0-green)
[![Java CI](https://github.com/jgs-lab/api-tulip/actions/workflows/build.yml/badge.svg)](https://github.com/jgs-lab/api-tulip/actions/workflows/build.yml)

**TULIP** is a Java utility library distributed through Maven Central. The published examples use **TULIP 8.0.0** from Maven Central. The library's **8.1.0** release is not yet available as `com.javiergs:tulip:8.1.0` on Maven Central (verified by CI); the examples will be upgraded after publication and compatibility testing. This repository helps developers **use the published library** through a single Maven project containing independent runnable examples, guides, and API documentation. The library implementation is not maintained here.

<img width="1536" height="1024" alt="F2A1C771-964E-4889-B15D-57423EA5EB00" src="https://github.com/user-attachments/assets/01117cfe-735b-44e0-9ed4-2bb47c6126ab" />


**New to TULIP?** Start with the [Student Quick Start](docs/student-quick-start.md): clone, run the GitHub example, explore read-only Taiga access, and develop an independent experiment.

## Requirements

- Java 21+ and Maven
- Example-specific configuration as described below

## Install
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

## Modules

- **GitHub:** URL parsing, remote file retrieval, and repository traversal. [Guide](docs/github-client.md).
- **Taiga:** Projects, sprints, user stories, tasks, and status operations. [Guide](docs/taiga-client.md).
- **RTDE migration:** Universal Robots RTDE support has moved out of TULIP into the independent [RTDE4J library](https://github.com/jgs-lab/api-rtde4j). See the [RTDE4J getting-started guide](https://github.com/javiergs/rtde4j-getting-started) for installation and JavaDoc.
- **Compiler:** Lexer, token, and automata helpers; see the [API reference](https://javiergs.github.io/tulip-api/javadoc/javiergs/tulip/compiler/package-summary.html).
- **Groq:** API client utilities; see the [API reference](https://javiergs.github.io/tulip-api/javadoc/javiergs/tulip/groq/package-summary.html).

## Configuration and credentials

- Use a local `tulip.properties` file for example-specific credentials, following `src/main/resources/tulip.properties.example`.
- **Do not commit actual credentials.** The `.gitignore` excludes local `tulip.properties` files.
- GitHub examples can use an optional `GITHUB_TOKEN` environment variable (preferred) or properties key; Taiga examples require `taiga.host`, `taiga.username`, and `taiga.password`.

## Documentation

- [GitHub client](docs/github-client.md)
- [Taiga client](docs/taiga-client.md)
- [JavaDoc](https://javiergs.github.io/tulip-api/javadoc/)

## Contributing

Improvements to examples, tutorials, and documentation are welcome via pull requests. See [CONTRIBUTING.md](CONTRIBUTING.md) for the fork-and-PR process, testing expectations, credential handling, and maintainer review.
