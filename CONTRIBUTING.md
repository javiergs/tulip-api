# Contributing to TULIP Getting Started

Thank you for improving the public tutorials, examples, and documentation for **TULIP**.

This repository contains **student-facing examples** that use the published `com.javiergs:tulip:8.1.0` Maven dependency. The TULIP library implementation is maintained separately and is not part of this repository. You do not need access to its source code.

## Contributions welcome

- Clearer Java 21 and Maven setup instructions.
- Small, reproducible examples for GitHub, Taiga, compiler, and other published TULIP APIs.
- Bug fixes, tests, documentation, and troubleshooting guides.
- Improved explanations of authentication, API responses, error handling, and limitations.

If an example reveals a missing library capability, describe the desired API and use case in an issue or pull request. Do not copy private library implementation into this repository.

## Fork, test, and submit a PR

1. Fork `javiergs/tulip-getting-started` and clone your fork.
2. Create a descriptive branch (for example, `docs/github-token-setup` or `examples/taiga-readonly`).
3. Make a focused change in one example or documentation section.
4. Keep Maven Central dependency versions unchanged unless a maintainer requests an update.
5. From the affected example directory, run `mvn clean verify`. Record any additional manual checks and required credentials.
6. Push your branch and open a pull request against this repository's `main` branch.

## Pull request checklist

Include:
- **Purpose:** What is being improved?
- **Files:** Which example(s) or guides changed?
- **Reproduction:** Exact build/run commands and prerequisites.
- **Results:** Actual output or screenshots, with sensitive information removed.
- **API behavior:** Relevant endpoint/resource types and whether the example reads or modifies remote data.
- **Limitations:** What CI verifies versus what requires live service access.

Use Java 21+, readable code, and clear exception messages. Do not commit API tokens, passwords, personal data, private project information, or generated `target/` files. Local `tulip.properties` files are ignored by Git, but always inspect your changes before pushing.

## External service safety

Start with public GitHub repositories or Taiga's read-only example. The `taiga-api` example can **create, update, and delete** work items: use only a disposable test project, and review the confirmation and cleanup behavior before running it. Never use production projects or credentials for student experiments.

## Review and issues

All contributions should be submitted as pull requests for instructor/maintainer review. Do not push directly to `main` or merge without approval.

When reporting a bug, include your OS, Java/Maven versions, TULIP version, reproduction steps, expected/actual results, and sanitized logs.
