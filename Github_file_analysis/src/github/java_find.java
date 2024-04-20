package github;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class java_find {
	public static void findJavaFiles(String directoryPath) {
	    File directory = new File(directoryPath);
	    if (!directory.exists()) {
	        System.out.println("Directory not found: " + directoryPath);
	        return;
	    }
	    File[] files = directory.listFiles();
	    if (files != null) {
	        for (File file : files) {
	            if (file.isDirectory()) {
	                
	                findJavaFiles(file.getAbsolutePath());
	            } else if (file.getName().endsWith(".java")) {
	                
	                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	                    String line;
	                    boolean isClassFile = false;
	                    while ((line = reader.readLine()) != null) {
	                        if (line.contains("class ")) {
	                            isClassFile = true;
	                            break;
	                        }
	                    }
	                    
	                    if (isClassFile) {
	                        try {
	                            java_file_analiz.analyzeJavaFile(file);
	                        } catch (IOException e) {
	                            System.out.println("Error analyzing file: " + e.getMessage());
	                        }
	                    } else {
	                        System.out.println("File does not contain any classes: " + file.getName());
	                    }
	                    
	                } catch (IOException e) {
	                    System.out.println("Error reading file: " + e.getMessage());
	                }
	            }
	        }
	    }
	}
}

