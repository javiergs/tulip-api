# TULIP API

**TULIP** is a Java utility library distributed through Maven Central. This repository helps developers **use the published library** through independent Maven examples, guides, and API documentation. The library implementation is not maintained here.

## Install

Requires **Java 21+** and Maven. Add the following dependency to your project's `pom.xml`:

```xml
<dependency>
    <groupId>com.javiergs</groupId>
    <artifactId>tulip</artifactId>
    <version>8.0.0</version>
</dependency>
```

- [Maven Central artifact page](https://central.sonatype.com/artifact/com.javiergs/tulip/8.0.0)
- [API reference (JavaDoc)](https://javiergs.github.io/tulip-api/javadoc/)

> The examples target version **8.0.0**. Their compilation against Maven Central has not yet been independently verified in this repository.

## Examples

Each folder is an independent Maven project with its own `pom.xml`.

- **[GitHub API](examples/github-api/)** — List files in a public GitHub repository. Supports an optional access token for rate limits and private repositories. [Guide](docs/github-client.md).
- **[Taiga API](examples/taiga-api/)** — Authenticate and demonstrate creating, reading, moving, and cleaning up Taiga work items. **This example writes to a Taiga project**; use a disposable test project. [Guide](docs/taiga-client.md).
- **[Taiga read-only](examples/taiga-readonly/)** — Authenticate and list accessible projects without changing any Taiga data.

To compile an example:

```sh
cd examples/github-api
mvn compile
```

To run its `example.Main` class:

```sh
mvn exec:java -Dexec.mainClass=example.Main
```

## Modules

- **GitHub:** URL parsing, remote file retrieval, and repository traversal. [Guide](docs/github-client.md).
- **Taiga:** Projects, sprints, user stories, tasks, and status operations. [Guide](docs/taiga-client.md).
- **RTDE:** Universal Robots communication utilities; see the [API reference](https://javiergs.github.io/tulip-api/javadoc/javiergs/tulip/rtde/package-summary.html). Hardware-dependent examples are not included.
- **Compiler:** Lexer, token, and automata helpers; see the [API reference](https://javiergs.github.io/tulip-api/javadoc/javiergs/tulip/compiler/package-summary.html).
- **Groq:** API client utilities; see the [API reference](https://javiergs.github.io/tulip-api/javadoc/javiergs/tulip/groq/package-summary.html).

## Configuration and credentials

- Use a local `tulip.properties` file for example-specific credentials, following the included `tulip.properties.example` files.
- **Do not commit actual credentials.** The `.gitignore` excludes local `tulip.properties` files.
- GitHub examples can use an optional `GITHUB_TOKEN`; Taiga examples require `taiga.host`, `taiga.username`, and `taiga.password`.

## Documentation

- [GitHub client](docs/github-client.md)
- [Taiga client](docs/taiga-client.md)
- [JavaDoc](https://javiergs.github.io/tulip-api/javadoc/)
