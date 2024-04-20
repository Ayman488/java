package github;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import github.Github_repository;

public class Main {
	public static void main(String[] args) {
    	try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter the URL of the GitHub repository: ");
            String githubURL = reader.readLine();

            Github_repository.processRepository(githubURL);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}