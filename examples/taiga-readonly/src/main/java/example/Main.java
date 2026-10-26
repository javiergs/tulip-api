package example;

import javiergs.tulip.taiga.TaigaClient;
import javiergs.tulip.taiga.TaigaProject;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = Main.class.getClassLoader().getResourceAsStream("tulip.properties")) {
            if (in == null) throw new IllegalStateException("Create src/main/resources/tulip.properties from tulip.properties.example");
            properties.load(in);
        }
        String host = require(properties, "taiga.host");
        TaigaClient client = new TaigaClient(host);
        client.login(require(properties, "taiga.username"), require(properties, "taiga.password"));
        for (TaigaProject project : client.getMyProjects()) {
            System.out.println(project.getId() + " — " + project.getName());
        }
    }
    private static String require(Properties properties, String key) {
        String value = properties.getProperty(key);
        if (value == null || value.isBlank() || value.startsWith("your_")) {
            throw new IllegalArgumentException("Configure " + key + " in tulip.properties");
        }
        return value.trim();
    }
}
