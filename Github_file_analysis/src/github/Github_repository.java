package github;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Github_repository {
	public static void processRepository(String githubURL) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String[] urlParts = githubURL.split("/");
            String repositoryName = urlParts[urlParts.length - 1].replace(".git", ""); // Removing .git extension

            File directory = new File(repositoryName);
            if (!directory.exists()) {
                directory.mkdir();
            }

            Process process = Runtime.getRuntime().exec("git clone " + githubURL + " " + repositoryName);

            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("Repository cloned successfully.");
            // Perform any additional processing here
            java_find h = new java_find();
            h.findJavaFiles(repositoryName);

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

