# Taiga read-only example

This example logs in to Taiga and prints projects accessible to the authenticated account. It does **not** create, update, or delete data.

1. Copy `src/main/resources/tulip.properties.example` to `src/main/resources/tulip.properties`.
2. Enter the Taiga host and credentials in the local file. Never commit it.
3. Run `mvn compile exec:java -Dexec.mainClass=example.Main`.

Requires Java 21+, Maven, and the published `com.javiergs:tulip:8.0.0` dependency.
