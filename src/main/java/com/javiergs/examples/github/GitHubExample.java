package com.javiergs.examples.github;

import javiergs.tulip.github.GitHubHandler;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class GitHubExample {

    public static void main(String[] args) throws Exception {

        Properties p = new Properties();

        try (InputStream in =
                 GitHubExample.class.getClassLoader()
                     .getResourceAsStream("tulip.properties")) {

            if (in != null) {
                p.load(in);
            }
        }
        String token = System.getenv("GITHUB_TOKEN");
        if (token == null || token.isBlank()) token = p.getProperty("GITHUB_TOKEN");
        if (token != null && (token.isBlank() || token.startsWith("your_"))) token = null;
        GitHubHandler gh = new GitHubHandler(token);

        List<String> files =
            gh.listFilesRecursive(
                "https://github.com/javiergs/ADASIM"
            );

        for (String file : files) {
            System.out.println(file);
        }
    }
}
