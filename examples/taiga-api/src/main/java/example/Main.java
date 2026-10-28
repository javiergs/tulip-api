package example;

import javiergs.tulip.taiga.*;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws Exception {

        if (!Boolean.getBoolean("taiga.allowWrites")) {
            throw new IllegalStateException("Writes disabled. For a disposable Taiga project only, pass -Dtaiga.allowWrites=true and -Dtaiga.testProjectId=ID using MAVEN_OPTS.");
        }
        String projectIdText = System.getProperty("taiga.testProjectId");
        if (projectIdText == null || !projectIdText.matches("[0-9]+")) {
            throw new IllegalArgumentException("Explicit test project ID required: -Dtaiga.testProjectId=ID (via MAVEN_OPTS)");
        }
        long selectedProjectId = Long.parseLong(projectIdText);

        Properties cfg = loadProperties("tulip.properties");

        TaigaClient taiga = new TaigaClient(
                require(cfg, "taiga.host")
        );

        taiga.login(
                require(cfg, "taiga.username"),
                require(cfg, "taiga.password")
        );

        List<TaigaProject> projects = taiga.getMyProjects();

        TaigaProject project = projects.stream()
                .filter(p -> p.getId() == selectedProjectId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Test project ID " + selectedProjectId + " not accessible; no changes made."));

        System.out.println("Using project: " + project.getName());

        String suffix = String.valueOf(System.currentTimeMillis());
        LocalDate sprintStart = LocalDate.now().plusDays(1);
        LocalDate sprintEnd = sprintStart.plusDays(13);

        TaigaSprint sprint = null;
        TaigaUserStory story = null;
        TaigaTask task = null;

        try {
            sprint = taiga.createSprint(
                    project.getId(),
                    "Example Sprint " + suffix,
                    sprintStart.toString(),
                    sprintEnd.toString()
            );

            System.out.println("Created sprint: " + sprint.getName());

            story = taiga.createUserStory(
                    project.getId(),
                    "Example Story " + suffix,
                    "Created from the TULIP Taiga example.",
                    sprint.getId()
            );

            System.out.println("Created story: " + story.getSubject());

            task = taiga.createTask(
                    project.getId(),
                    story.getId(),
                    "Example Task " + suffix,
                    null
            );

            System.out.println("Created task: " + task.getSubject());

            TaigaTask fetchedTask = taiga.getTask(task.getId());

            System.out.println(
                    "Fetched task: " + fetchedTask.getId()
                            + " / story id: " + fetchedTask.getUserStoryId()
            );

            List<TaigaStatus> statuses =
                    taiga.getTaskStatuses(project.getId());

            if (statuses.size() >= 2) {
                long currentStatusId = task.getStatusId();

                long newStatusId = statuses.stream()
                        .map(TaigaStatus::getId)
                        .filter(id -> id != currentStatusId)
                        .findFirst()
                        .orElseThrow();

                TaigaTask movedTask =
                        taiga.moveTaskToStatus(task.getId(), newStatusId);

                System.out.println(
                        "Moved task to status id: "
                                + movedTask.getStatusId()
                );
            } else {
                System.out.println(
                        "Project does not have enough task statuses to test movement."
                );
            }

        } finally {
            if (task != null) {
                try {
                    taiga.deleteTask(task.getId());
                    System.out.println("Deleted task.");
                } catch (Exception ex) {
                    System.err.println("WARNING: Could not delete test task " + task.getId() + ": " + ex.getMessage());
                }
            }

            if (story != null) {
                try {
                    taiga.deleteUserStory(story.getId());
                    System.out.println("Deleted story.");
                } catch (Exception ex) {
                    System.err.println("WARNING: Could not delete test story " + story.getId() + ": " + ex.getMessage());
                }
            }

            if (sprint != null) {
                try {
                    taiga.deleteSprint(sprint.getId());
                    System.out.println("Deleted sprint.");
                } catch (Exception ex) {
                    System.err.println("WARNING: Could not delete test sprint " + sprint.getId() + ": " + ex.getMessage());
                }
            }
        }
    }

    private static Properties loadProperties(String resourceName) throws Exception {
        Properties p = new Properties();

        try (InputStream in =
                     Main.class.getClassLoader()
                             .getResourceAsStream(resourceName)) {

            if (in == null) {
                throw new RuntimeException(
                        resourceName + " not found in src/main/resources"
                );
            }

            p.load(in);
        }

        return p;
    }

    private static String require(Properties p, String key) {
        String value = p.getProperty(key);

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing property: " + key);
        }

        return value.trim();
    }
}
